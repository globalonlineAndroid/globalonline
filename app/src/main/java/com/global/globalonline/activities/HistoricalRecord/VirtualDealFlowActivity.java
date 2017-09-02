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
import com.global.globalonline.base.StaticBase;
import com.global.globalonline.bean.xuNiBi.CoinsTradeRecordBean;
import com.global.globalonline.bean.xuNiBi.CoinsTradeRecordItemBean;
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
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

@EActivity(R.layout.activity_virtual_deal_flow)
public class VirtualDealFlowActivity extends BaseActivity {

    @ViewById
    AutoSwipeRefreshLayout srl_vdf;
    @ViewById
    ListView lv_vdf;
    @ViewById
    TextView tv_tab1,tv_tab2,tv_tab3,tv_tab4;

    @ViewById
    View view_tv_tab1,view_tv_tab2,view_tv_tab3,view_tv_tab4;

    @ViewById
    TextView tv_mairu_tab,tv_maichu_tab;

    VirtualDealFlowAdapter maAdapter;
    List<CoinsTradeRecordItemBean> list = new ArrayList<CoinsTradeRecordItemBean>();
    VirtualService virtualService;
    String symbol = "";
    String types = "1";
    String next_id = "0";
    String type = "0";
    boolean b = true;

    public static void  toActivity(Activity activity,String symbol){

        Intent intent = new Intent(activity, VirtualDealFlowActivity_.class);
        intent.putExtra("symbol",symbol);
        activity.startActivity(intent);
    }



    @AfterViews
    void init(){
        lv_vdf.setEmptyView(this.findViewById(R.id.layout_empty));
        symbol = getIntent().getStringExtra("symbol");

        srl_vdf.setColorSchemeResources(StaticBase.colorResIds);
        lv_vdf.setOnScrollListener(new AbsListView.OnScrollListener() {
            int firstVisibleItem; // 当前第一个可见Item的位置
            int totalItemCount;
            int lastVisibleItem;
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(totalItemCount==lastVisibleItem && scrollState == SCROLL_STATE_IDLE){
                    Log.e("log", "滑到底部");

                    if( next_id.equals("0")){
                        GetToastUtil.getToads(VirtualDealFlowActivity.this,getResources().getString(R.string.act_base_nodata));
                    }else {
                        initView(false);
                    }

                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                this.firstVisibleItem = firstVisibleItem;
                this.totalItemCount=totalItemCount;
                this.lastVisibleItem=firstVisibleItem+visibleItemCount;

                if(firstVisibleItem==0){
                    srl_vdf.setEnabled(true);
                }else {
                    srl_vdf.setEnabled(false);
                }
            }
        });



        srl_vdf.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initView(true);
                next_id = "0";
                srl_vdf.setRefreshing(false);
            }
        });

        tv_tab1.performClick();


    }

    @Click({R.id.tv_tab1,R.id.tv_tab2,R.id.tv_tab3,R.id.tv_tab4})
    void click(View view) {
        switch (view.getId()) {
            case R.id.tv_tab1:
                setTextBackgroud(tv_tab1,view_tv_tab1);
                 types = "1";
                 next_id = "0";
                 type = getResources().getString(R.string.act_historicalRecord_virtualdealflow_tab1);
                 list.clear();
                srl_vdf.autoRefresh();
               // initView();
                break;
            case R.id.tv_tab2:
                setTextBackgroud(tv_tab2,view_tv_tab2);
                 types = "2";
                 next_id = "0";
                type = getResources().getString(R.string.act_historicalRecord_virtualdealflow_tab2);
                list.clear();
                srl_vdf.autoRefresh();
                // initView();
                break;
            case R.id.tv_tab3:
                setTextBackgroud(tv_tab3,view_tv_tab3);
                types = "3";
                next_id = "0";
                type = getResources().getString(R.string.act_historicalRecord_virtualdealflow_tab3);
                list.clear();
                srl_vdf.autoRefresh();
                // initView();
                break;
            case R.id.tv_tab4:
                setTextBackgroud(tv_tab4,view_tv_tab4);
                types = "4";
                next_id = "0";
                type = getResources().getString(R.string.act_historicalRecord_virtualdealflow_tab4);
                list.clear();
                srl_vdf.autoRefresh();
                // initView();
                break;
        }


    }


    void setTextBackgroud(TextView tv,View view){

        tv_tab1.setTextColor(getResources().getColor(R.color.ac_base_fenGeXian));
        tv_tab2.setTextColor(getResources().getColor(R.color.ac_base_fenGeXian));
        tv_tab3.setTextColor(getResources().getColor(R.color.ac_base_fenGeXian));
        tv_tab4.setTextColor(getResources().getColor(R.color.ac_base_fenGeXian));

        view_tv_tab1.setBackgroundResource(R.color.ac_base_fenGeXian);
        view_tv_tab2.setBackgroundResource(R.color.ac_base_fenGeXian);
        view_tv_tab3.setBackgroundResource(R.color.ac_base_fenGeXian);
        view_tv_tab4.setBackgroundResource(R.color.ac_base_fenGeXian);


        tv.setTextColor(getResources().getColor(R.color.FFA200));
        view.setBackgroundResource(R.color.FFA200);

    }

    public void initView(final boolean  c) {

        RecordService baseService = GetRetrofitService.getRestClient(RecordService.class);

        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("symbol",symbol);
        stringMap.put("types",types);
        stringMap.put("next_id",next_id);

        stringMap = MapToParams.getParsMap(stringMap);

        Call<CoinsTradeRecordBean> call = baseService.coins_trade_record(stringMap);
        RestService restService = new RestServiceImpl();
        restService.get(null,"",call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                CoinsTradeRecordBean baseBean =   ((CoinsTradeRecordBean)response.body());
                next_id = baseBean.getNext_id();
                if(baseBean.getErrorCode().equals("0")) {

                    if(baseBean.getRecord_list() != null && baseBean.getRecord_list().size()>0) {
                        next_id = baseBean.getNext_id();
                        list.addAll(baseBean.getRecord_list());
                    }else {
                        GetToastUtil.getToads(VirtualDealFlowActivity.this,getResources().getString(R.string.act_base_nodata));
                    }
                    if (b && c == true){
                        maAdapter = new VirtualDealFlowAdapter(VirtualDealFlowActivity.this,baseBean.getRecord_list(),type);
                        lv_vdf.setAdapter(maAdapter);
                    }else {
                        maAdapter.notifyDataSetChanged();
                    }

                }else {
                    GetToastUtil.getToads(getApplication(), baseBean.getMessage());

                }


            }
            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {

                GetToastUtil.getToads(getApplication(), t.getMessage());
            }
        });
    }



}
