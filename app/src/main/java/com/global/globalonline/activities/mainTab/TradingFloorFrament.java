package com.global.globalonline.activities.mainTab;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.global.globalonline.R;
import com.global.globalonline.activities.virtualCurrency.VirtualTradeActivity_;
import com.global.globalonline.adapter.mainTab.TradingFloorAdapter;
import com.global.globalonline.bean.VirtualCurrencyBean;
import com.global.globalonline.bean.VirtualListItemBean;
import com.global.globalonline.service.CallBackService;
import com.global.globalonline.service.GetRetrofitService;
import com.global.globalonline.service.RestService;
import com.global.globalonline.service.serviceImpl.RestServiceImpl;
import com.global.globalonline.service.virtualTrading.VirtualService;
import com.global.globalonline.tools.GetToastUtil;
import com.global.globalonline.tools.MapToParams;

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
    SwipeRefreshLayout srl_tradingFloor;
    @ViewById
    ListView lv_tradingFloor;

    TradingFloorAdapter tradingFloorAdapter;


    List<VirtualCurrencyBean>  list = null;
    VirtualService virtualService;

    @AfterViews()
    void init(){
        virtualService = GetRetrofitService.getRestClient(VirtualService.class);

        list = new ArrayList<VirtualCurrencyBean>();
        for (int i = 0; i < 10; i++) {
            VirtualCurrencyBean v = new VirtualCurrencyBean();
            v.setCount(String.valueOf(10+i));
            list.add(v);

        }

        srl_tradingFloor.setColorSchemeResources(R.color.springgreen, R.color.forestgreen, R.color.goldenrod,
                R.color.indianred);
        srl_tradingFloor.setOnRefreshListener(this);
        tradingFloorAdapter = new TradingFloorAdapter(getActivity(),list);
        lv_tradingFloor.setAdapter(tradingFloorAdapter);


        lv_tradingFloor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VirtualTradeActivity_.intent(getActivity()).start();
            }
        });

    }


    @Override
    public void onRefresh() {

        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("next_id", "0");
        stringMap.put("perpage", "20");
        stringMap.put("orderby", "1");

        stringMap = MapToParams.getParsMap(stringMap);

        Call<VirtualListItemBean> call = virtualService.index(stringMap);
        RestService restService = new RestServiceImpl();

        restService.get(null, "", call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {
                srl_tradingFloor.setRefreshing(false);
                VirtualListItemBean virtualListItemBean =(VirtualListItemBean) response.body();
                if(virtualListItemBean.getErrorCode().equals("0")){

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
