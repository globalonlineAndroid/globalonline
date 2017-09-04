package com.global.globalonline.activities.mainTab;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.activities.virtualCurrency.VirtualTradeActivity;
import com.global.globalonline.adapter.mainTab.TradingFloorAdapter;
import com.global.globalonline.bean.VirtualListItemBean;
import com.global.globalonline.bean.VirtualTradingBean;
import com.global.globalonline.dao.PullRefreshDao;
import com.global.globalonline.service.CallBackService;
import com.global.globalonline.service.GetRetrofitService;
import com.global.globalonline.service.RestService;
import com.global.globalonline.service.serviceImpl.RestServiceImpl;
import com.global.globalonline.service.virtualTrading.VirtualService;
import com.global.globalonline.tools.GetQuanXian;
import com.global.globalonline.tools.GetToastUtil;
import com.global.globalonline.tools.MapToParams;
import com.global.globalonline.view.PullRefreshView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;


@EFragment(R.layout.activity_trading_floor)
public class TradingFloorFrament extends Fragment  {


    @ViewById
    PullRefreshView plf_tradingFloor_b;
    @ViewById
    TextView tv_jiaoyi,tv_shiyan;
    @ViewById
    View view_tv_jiaoyi,view_tv_shiyan;

    private String coin_block = "1"; //1交易区 2实验区


    TradingFloorAdapter tradingFloorAdapter;


    List<VirtualTradingBean> list = new ArrayList<VirtualTradingBean>();;
    VirtualService virtualService;

    @AfterViews()
    void init(){
        virtualService = GetRetrofitService.getRestClient(VirtualService.class);

        plf_tradingFloor_b.audoRefresh();

        plf_tradingFloor_b.pullRefresh(new PullRefreshDao() {
            @Override
            public void DownRefresh() {
                tradingFloorAdapter = null;
                initlist();
            }

            @Override
            public void UpLoading() {

            }
        });

        plf_tradingFloor_b.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(GetQuanXian.getIsQuanXian(getActivity())) {
                    VirtualTradingBean virtualTradingBean = (VirtualTradingBean) parent.getItemAtPosition(position);
                    VirtualTradeActivity.toActiviy(getActivity(), virtualTradingBean.getSymbol());
                }
            }
        });

    }

    @Click({R.id.tv_jiaoyi,R.id.tv_shiyan})
    void click(View view){
        switch (view.getId()){
            case R.id.tv_jiaoyi:
                coin_block = "1";
                tabSelect(tv_jiaoyi,view_tv_jiaoyi);
                initlist();
                break;
            case R.id.tv_shiyan:
                coin_block = "2";
                tabSelect(tv_shiyan,view_tv_shiyan);
                initlist();
                break;
        }
    }




    public void initlist(){


        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("coin_block",coin_block);
        stringMap = MapToParams.getParsMap(stringMap);

        Call<VirtualListItemBean> call = virtualService.index(stringMap);
        RestService restService = new RestServiceImpl();

        restService.get(null, "", call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {
                VirtualListItemBean virtualListItemBean =(VirtualListItemBean) response.body();
                if(virtualListItemBean.getErrorCode().equals("0")){

                    list = new ArrayList<VirtualTradingBean>();
                    for (int i = 0; i < virtualListItemBean.getItems().size(); i++) {
                        VirtualTradingBean v = virtualListItemBean.getItems().get(i);
                        list.add(v);
                    }
                    if(tradingFloorAdapter == null && list != null && list.size() != 0) {
                        tradingFloorAdapter = new TradingFloorAdapter(getActivity(), list);
                        plf_tradingFloor_b.getListView().setAdapter(tradingFloorAdapter);
                    }else {
                        tradingFloorAdapter.notifyDataSetChanged();
                    }

                }else {
                    GetToastUtil.getToads(getActivity(),virtualListItemBean.getMessage());
                }
            }

            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {
            }
        });
    }


    void tabSelect(TextView textView,View view){
        tv_jiaoyi.setTextColor(getResources().getColor(R.color.ac_base_ziti_hui));
        view_tv_jiaoyi.setBackgroundResource(R.color.ac_base_ziti_hui);
        tv_shiyan.setTextColor(getResources().getColor(R.color.ac_base_ziti_hui));
        view_tv_shiyan.setBackgroundResource(R.color.ac_base_ziti_hui);

        textView.setTextColor(getResources().getColor(R.color.F58703));
        view.setBackgroundResource(R.color.F58703);

    }



}
