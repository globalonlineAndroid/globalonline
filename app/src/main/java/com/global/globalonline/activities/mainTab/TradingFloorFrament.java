package com.global.globalonline.activities.mainTab;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.global.globalonline.R;
import com.global.globalonline.activities.virtualCurrency.VirtualTradeActivity;
import com.global.globalonline.adapter.mainTab.TradingFloorAdapter;
import com.global.globalonline.base.StaticBase;
import com.global.globalonline.bean.VirtualListItemBean;
import com.global.globalonline.bean.VirtualTradingBean;
import com.global.globalonline.service.CallBackService;
import com.global.globalonline.service.GetRetrofitService;
import com.global.globalonline.service.RestService;
import com.global.globalonline.service.serviceImpl.RestServiceImpl;
import com.global.globalonline.service.virtualTrading.VirtualService;
import com.global.globalonline.tools.GetQuanXian;
import com.global.globalonline.tools.GetToastUtil;
import com.global.globalonline.tools.MapToParams;
import com.global.globalonline.view.AutoSwipeRefreshLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;


@EFragment(R.layout.activity_trading_floor)
public class TradingFloorFrament extends Fragment implements SwipeRefreshLayout.OnRefreshListener   {


    @ViewById
    AutoSwipeRefreshLayout srl_tradingFloor;
    @ViewById
    ListView lv_tradingFloor;



    TradingFloorAdapter tradingFloorAdapter;


    List<VirtualTradingBean> list = null;
    VirtualService virtualService;

    @AfterViews()
    void init(){
        virtualService = GetRetrofitService.getRestClient(VirtualService.class);
       // initlist();
        srl_tradingFloor.autoRefresh();

        srl_tradingFloor.setColorSchemeResources(StaticBase.colorResIds);
        srl_tradingFloor.setOnRefreshListener(this);

        lv_tradingFloor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(GetQuanXian.getIsQuanXian(getActivity())) {
                    VirtualTradingBean virtualTradingBean = (VirtualTradingBean) parent.getItemAtPosition(position);
                    VirtualTradeActivity.toActiviy(getActivity(), virtualTradingBean.getSymbol());
                }
            }
        });

    }


    @Override
    public void onRefresh() {

        initlist();

    }

    public void initlist(){

        if(list != null){
            list.clear();
        }

        Map<String, String> stringMap = new HashMap<String, String>();

        stringMap = MapToParams.getParsMap(stringMap);

        Call<VirtualListItemBean> call = virtualService.index(stringMap);
        RestService restService = new RestServiceImpl();

        restService.get(null, "", call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {
                srl_tradingFloor.setRefreshing(false);
                VirtualListItemBean virtualListItemBean =(VirtualListItemBean) response.body();
                if(virtualListItemBean.getErrorCode().equals("0")){

                    list = new ArrayList<VirtualTradingBean>();
                    for (int i = 0; i < virtualListItemBean.getItems().size(); i++) {
                        VirtualTradingBean v = virtualListItemBean.getItems().get(i);
                        list.add(v);
                    }

                    tradingFloorAdapter = new TradingFloorAdapter(getActivity(),list);
                    lv_tradingFloor.setAdapter(tradingFloorAdapter);

                }else {
                    GetToastUtil.getToads(getActivity(),virtualListItemBean.getMessage());
                }
            }

            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {
                srl_tradingFloor.setRefreshing(false);
            }
        });
    }



}
