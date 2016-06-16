package com.global.globalonline.activities.HistoricalRecord;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.adapter.HistoricalRecord.VirtualDealFlowAdapter;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.bean.DelegateBean;
import com.global.globalonline.service.virtualTrading.VirtualService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_virtual_deal_flow)
public class VirtualDealFlowActivity extends BaseActivity {

    @ViewById
    SwipeRefreshLayout srl_vdf;
    @ViewById
    ListView lv_vdf;
    @ViewById
    TextView tv_tab1,tv_tab2,tv_tab3,tv_tab4;

    @ViewById
    TextView tv_mairu_tab,tv_maichu_tab;

    VirtualDealFlowAdapter maAdapter;
    List<DelegateBean> delegateList = new ArrayList<DelegateBean>();
    VirtualService virtualService;


    public static void  toActivity(Activity activity,String xuNiId){

        Intent intent = new Intent(activity, VirtualDealFlowActivity_.class);
        intent.putExtra("xuNiId",xuNiId);
        activity.startActivity(intent);
    }



    @AfterViews
    void init(){


       /* maAdapter = new VirtualDealFlowAdapter(VirtualDealFlowActivity.this,delegateList);
        lv_vdf.setAdapter(maAdapter);
*/
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

/*tv_tab1,tv_tab2,tv_tab3,tv_tab4*/
    @Click({R.id.tv_tab1,R.id.tv_tab2,R.id.tv_tab3,R.id.tv_tab4})
    void click(View view) {
        switch (view.getId()) {
            case R.id.tv_tab1:
                setTextBackgroud(tv_tab1);

                //initView();
                break;
            case R.id.tv_tab2:
                setTextBackgroud(tv_tab2);

               // initView();
                break;
            case R.id.tv_tab3:
                setTextBackgroud(tv_tab3);

             //   initView();
                break;
            case R.id.tv_tab4:
                setTextBackgroud(tv_tab4);

             //   initView();
                break;
        }


    }


    void setTextBackgroud(TextView tv){


        tv_tab1.setBackgroundResource(R.color.ac_virtual_chunk);
        tv_tab2.setBackgroundResource(R.color.ac_virtual_chunk);
        tv_tab3.setBackgroundResource(R.color.ac_virtual_chunk);
        tv_tab4.setBackgroundResource(R.color.ac_virtual_chunk);
        tv.setBackgroundResource(R.color.ac_base_tab);


    }



}
