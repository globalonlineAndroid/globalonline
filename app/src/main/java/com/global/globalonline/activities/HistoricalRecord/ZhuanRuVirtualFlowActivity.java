package com.global.globalonline.activities.HistoricalRecord;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

import com.global.globalonline.R;
import com.global.globalonline.adapter.HistoricalRecord.ZhuanChuVirtualFlowAdapter;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.bean.DelegateBean;
import com.global.globalonline.service.virtualTrading.VirtualService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_zhuanru_virtual_flow)
public class ZhuanRuVirtualFlowActivity extends BaseActivity {

    @ViewById
    SwipeRefreshLayout srl_vdf;
    @ViewById
    ListView lv_vdf;



    ZhuanChuVirtualFlowAdapter maAdapter;
    List<DelegateBean> delegateList = new ArrayList<DelegateBean>();
    VirtualService virtualService;


    public static void  toActivity(Activity activity,String xuNiId){

        Intent intent = new Intent(activity, VirtualDealFlowActivity_.class);
        intent.putExtra("xuNiId",xuNiId);
        activity.startActivity(intent);
    }

    @AfterViews
    void init(){

        for (int i = 0; i < 20; i++) {
            DelegateBean d = new DelegateBean();
            d.setId(i+"");
            delegateList.add(d);
        }
        maAdapter = new ZhuanChuVirtualFlowAdapter(ZhuanRuVirtualFlowActivity.this,delegateList);
        lv_vdf.setAdapter(maAdapter);

        lv_vdf.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem==0){
                    srl_vdf.setEnabled(true);
                }else {
                    srl_vdf.setEnabled(false);
                }

                if(visibleItemCount+firstVisibleItem==totalItemCount){
                    Log.e("log", "滑到底部");
                }
            }
        });


        srl_vdf.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl_vdf.setRefreshing(false);
            }
        });


    }



}
