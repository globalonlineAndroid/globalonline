package com.global.globalonline.activities.HistoricalRecord;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.adapter.HistoricalRecord.TiXianFlowAdapter;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.base.StaticBase;
import com.global.globalonline.bean.DelegateBean;
import com.global.globalonline.bean.RMB.tixian.RmbExtractRecordBean;
import com.global.globalonline.service.CallBackService;
import com.global.globalonline.service.GetRetrofitService;
import com.global.globalonline.service.HistoricalRecord.RecordService;
import com.global.globalonline.service.RestService;
import com.global.globalonline.service.serviceImpl.RestServiceImpl;
import com.global.globalonline.service.virtualTrading.VirtualService;
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

@EActivity(R.layout.activity_tixian_flow)
public class TiXianFlowActivity extends BaseActivity {

    @ViewById
    AutoSwipeRefreshLayout srl_vdf;
    @ViewById
    ListView lv_vdf;

    @ViewById
    TextView tv_mairu_tab,tv_maichu_tab;

    TiXianFlowAdapter maAdapter;
    List<DelegateBean> delegateList = new ArrayList<DelegateBean>();
    VirtualService virtualService;


    public static void  toActivity(Activity activity,String xuNiId){

        Intent intent = new Intent(activity, VirtualDealFlowActivity_.class);
        intent.putExtra("xuNiId",xuNiId);
        activity.startActivity(intent);
    }

    @AfterViews
    void init(){
        lv_vdf.setEmptyView(this.findViewById(R.id.layout_empty));
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

        Call<RmbExtractRecordBean> call = baseService.rmb_extract_record(stringMap);
        RestService restService = new RestServiceImpl();
        restService.get(null,"",call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                RmbExtractRecordBean baseBean =   ((RmbExtractRecordBean)response.body());
                if(baseBean.getErrorCode().equals("0")) {

                    if(baseBean.getRecord_list() !=null && baseBean.getRecord_list().size()>0) {
                        maAdapter = new TiXianFlowAdapter(TiXianFlowActivity.this, baseBean.getRecord_list());
                        lv_vdf.setAdapter(maAdapter);
                    }else {
                        GetToastUtil.getToads(TiXianFlowActivity.this,getResources().getString(R.string.act_base_nodata));
                    }
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
