package com.global.globalonline.activities.virtualCurrency;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.global.globalonline.R;
import com.global.globalonline.adapter.VirtualTrade.MandatoryAdministrationAdapter;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.bean.DelegateBean;
import com.global.globalonline.service.virtualTrading.VirtualService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_mandatory_administration)
public class MandatoryAdministrationActivity extends BaseActivity {
    @ViewById
    SwipeRefreshLayout srl_ma_tradingFloor;
    @ViewById
    ListView lv_ma_tradingFloor;




    MandatoryAdministrationAdapter maAdapter;
    List<DelegateBean>  delegateList = new ArrayList<DelegateBean>();
    VirtualService virtualService;
    @AfterViews
    void init(){

        for (int i = 0; i < 20; i++) {
            DelegateBean d = new DelegateBean();
            d.setId(i+"");
            delegateList.add(d);
        }
        maAdapter = new MandatoryAdministrationAdapter(MandatoryAdministrationActivity.this,delegateList);
        lv_ma_tradingFloor.setAdapter(maAdapter);

        srl_ma_tradingFloor.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl_ma_tradingFloor.setRefreshing(false);
            }
        });


    }


}
