package com.global.globalonline.activities.HistoricalRecord;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

import com.global.globalonline.R;
import com.global.globalonline.adapter.HistoricalRecord.HisWeiTuoFlowAdapter;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.base.StaticBase;
import com.global.globalonline.bean.BaseBean;
import com.global.globalonline.bean.CoinsEntrustRecordBena;
import com.global.globalonline.bean.RecordListBean;
import com.global.globalonline.service.CallBackService;
import com.global.globalonline.service.GetRetrofitService;
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

@EActivity(R.layout.act_his_weituo_flow)
public class HisWeiTuoActivity extends BaseActivity {
    @ViewById
    AutoSwipeRefreshLayout srl_ma_tradingFloor;
    @ViewById
    ListView lv_ma_tradingFloor;

    HisWeiTuoFlowAdapter maAdapter;
    List<RecordListBean>  delegateList = new ArrayList<RecordListBean>();
    VirtualService virtualService;

    String symbol = "";
    String next_id = "0";
    boolean b = true;

    public static void  toActivity(Activity activity, String symbol){

        Intent intent = new Intent(activity, HisWeiTuoActivity_.class);
        intent.putExtra("symbol",symbol);
        activity.startActivity(intent);
    }


    @AfterViews
    void init(){

        srl_ma_tradingFloor.autoRefresh();
        srl_ma_tradingFloor.setColorSchemeResources(StaticBase.colorResIds);
        virtualService = GetRetrofitService.getRestClient(VirtualService.class);

        symbol = getIntent().getStringExtra("symbol");
        initView();
            lv_ma_tradingFloor.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem==0){
                    srl_ma_tradingFloor.setEnabled(true);
                }else {
                    srl_ma_tradingFloor.setEnabled(false);
                }

                if(visibleItemCount != totalItemCount && visibleItemCount+firstVisibleItem==totalItemCount){
                    Log.e("log", "滑到底部");
                    initView();
                }
            }
        });

        srl_ma_tradingFloor.setColorSchemeResources(StaticBase.colorResIds);
        srl_ma_tradingFloor.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                delegateList.clear();
                initView();

            }
        });


    }



    public void initView(){
        String types = "1";
        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("symbol",symbol);
        stringMap.put("types",types);
        stringMap.put("next_id",next_id);

        stringMap = MapToParams.getParsMap(stringMap);


        Call<CoinsEntrustRecordBena> call = virtualService.coins_entrust_record(stringMap);
        RestService restService = new RestServiceImpl();

        restService.get(null, "loading...", call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                CoinsEntrustRecordBena coinsDetailBean =(CoinsEntrustRecordBena) response.body();
                if(coinsDetailBean.getErrorCode().equals("0")){
                    if(coinsDetailBean.getRecord_list() !=null && coinsDetailBean.getRecord_list().size()>0) {
                        delegateList.addAll(coinsDetailBean.getRecord_list());
                    }else {
                        GetToastUtil.getToads(HisWeiTuoActivity.this,getResources().getString(R.string.act_base_nodata));
                    }
                }else {
                    GetToastUtil.getToads(HisWeiTuoActivity.this,coinsDetailBean.getMessage());
                }
                if(b) {
                    maAdapter = new HisWeiTuoFlowAdapter(HisWeiTuoActivity.this, delegateList);
                    lv_ma_tradingFloor.setAdapter(maAdapter);
                    b = false;
                }else {
                    maAdapter.notifyDataSetChanged();
                }
                srl_ma_tradingFloor.setRefreshing(false);
            }

            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {
                GetToastUtil.getToads(HisWeiTuoActivity.this,t.getMessage());
                srl_ma_tradingFloor.setRefreshing(false);
            }
        });
    }



    public void chexiaoweituo(final int index){

        RecordListBean recordListBean = delegateList.get(index);
        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("id",recordListBean.getId());


        stringMap = MapToParams.getParsMap(stringMap);


        Call<BaseBean> call = virtualService.cancel_entrust(stringMap);
        RestService restService = new RestServiceImpl();

        restService.get(HisWeiTuoActivity.this, "loading...", call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                BaseBean baseBean =(BaseBean) response.body();
                if(baseBean.getErrorCode().equals("0")){
                    delegateList.remove(index);
                    maAdapter.notifyDataSetChanged();
                }else {
                    GetToastUtil.getToads(HisWeiTuoActivity.this,baseBean.getMessage());
                }

            }

            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {
                GetToastUtil.getToads(HisWeiTuoActivity.this,t.getMessage());
            }
        });

    }

}
