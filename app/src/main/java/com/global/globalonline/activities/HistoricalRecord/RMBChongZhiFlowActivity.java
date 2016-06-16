package com.global.globalonline.activities.HistoricalRecord;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

import com.global.globalonline.R;
import com.global.globalonline.adapter.HistoricalRecord.RMBChongZhiFlowAdapter;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.base.StaticBase;
import com.global.globalonline.bean.DelegateBean;
import com.global.globalonline.bean.RMB.chongzhi.ChongZhiBean;
import com.global.globalonline.service.CallBackService;
import com.global.globalonline.service.GetRetrofitService;
import com.global.globalonline.service.HistoricalRecord.RecordService;
import com.global.globalonline.service.RestService;
import com.global.globalonline.service.serviceImpl.RestServiceImpl;
import com.global.globalonline.tools.GetToastUtil;
import com.global.globalonline.tools.MapToParams;
import com.global.globalonline.view.AutoSwipeRefreshLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

@EActivity(R.layout.activity_rmb_chongzhi_flow)
public class RMBChongZhiFlowActivity extends BaseActivity {

    @ViewById
    AutoSwipeRefreshLayout srl_vdf;
    @ViewById
    ListView lv_vdf;



    RMBChongZhiFlowAdapter maAdapter;
    List<DelegateBean> delegateList = new ArrayList<DelegateBean>();


    public static void  toActivity(Activity activity,String xuNiId){


    }

    @AfterViews
    void init(){


       srl_vdf.autoRefresh();
        srl_vdf.setColorSchemeResources(StaticBase.colorResIds);

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
                initView();
                srl_vdf.setRefreshing(false);
            }
        });


    }

    public void initView() {

        RecordService baseService = GetRetrofitService.getRestClient(RecordService.class);

        Map<String, String> stringMap = new HashMap<String, String>();

        stringMap = MapToParams.getParsMap(stringMap);

        Call<ChongZhiBean> call = baseService.rmb_paycheck_record(stringMap);
        RestService restService = new RestServiceImpl();
        restService.get(null,"",call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                ChongZhiBean baseBean =   ((ChongZhiBean)response.body());
                if(baseBean.getErrorCode().equals("0")) {

                    maAdapter = new RMBChongZhiFlowAdapter(RMBChongZhiFlowActivity.this,baseBean.getRecord_list());
                        lv_vdf.setAdapter(maAdapter);

                }else {
                    GetToastUtil.getToads(getApplication(), baseBean.getMessage());

                }
                // dialog.cancel();
            }
            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {
                // dialog.cancel();
                GetToastUtil.getToads(getApplication(), t.getMessage());
            }
        });
    }



}
