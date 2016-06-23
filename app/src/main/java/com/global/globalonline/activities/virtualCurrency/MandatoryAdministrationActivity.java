package com.global.globalonline.activities.virtualCurrency;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.activities.HistoricalRecord.HisWeiTuoActivity;
import com.global.globalonline.adapter.VirtualTrade.MandatoryAdministrationAdapter;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.base.StaticBase;
import com.global.globalonline.bean.BaseBean;
import com.global.globalonline.bean.CoinsEntrustRecordBena;
import com.global.globalonline.bean.RecordListBean;
import com.global.globalonline.dao.WeiTuoMangerDao;
import com.global.globalonline.service.CallBackService;
import com.global.globalonline.service.GetRetrofitService;
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

@EActivity(R.layout.activity_mandatory_administration)
public class MandatoryAdministrationActivity extends BaseActivity {
    @ViewById
    AutoSwipeRefreshLayout srl_ma_tradingFloor;
    @ViewById
    ListView lv_ma_tradingFloor;
    @ViewById
    TextView operation;

    MandatoryAdministrationAdapter maAdapter;
    List<RecordListBean>  delegateList = new ArrayList<RecordListBean>();
    VirtualService virtualService;

    String symbol = "";
    String next_id = "0";
    boolean b = true;



    int firstVisibleItem; // 当前第一个可见Item的位置
   
    int totalItemCount;
    int lastVisibleItem;

    public static void  toActivity(Activity activity, String symbol){

        Intent intent = new Intent(activity, MandatoryAdministrationActivity_.class);
        intent.putExtra("symbol",symbol);
        activity.startActivity(intent);
    }


    @AfterViews
    void init(){

        srl_ma_tradingFloor.autoRefresh();
        srl_ma_tradingFloor.setColorSchemeResources(StaticBase.colorResIds);
        virtualService = GetRetrofitService.getRestClient(VirtualService.class);

        symbol = getIntent().getStringExtra("symbol");
        //initView();
            lv_ma_tradingFloor.setOnScrollListener(new AbsListView.OnScrollListener() {
                int firstVisibleItem; // 当前第一个可见Item的位置
                int totalItemCount;
                int lastVisibleItem;

                @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                if(totalItemCount==lastVisibleItem && scrollState == SCROLL_STATE_IDLE){
                    Log.e("log", "滑到底部");

                    if( next_id.equals("0")){
                        GetToastUtil.getToads(MandatoryAdministrationActivity.this,getResources().getString(R.string.act_base_nodata));
                        return ;
                    }else {
                        initView();
                    }
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                this.firstVisibleItem = firstVisibleItem;
                this.totalItemCount=totalItemCount;
                this.lastVisibleItem=firstVisibleItem+visibleItemCount;

                if(firstVisibleItem==0){
                    srl_ma_tradingFloor.setEnabled(true);
                }else {
                    srl_ma_tradingFloor.setEnabled(false);
                }

              /*  if(visibleItemCount != totalItemCount && visibleItemCount+firstVisibleItem==totalItemCount){

                }*/
            }
        });




        srl_ma_tradingFloor.setColorSchemeResources(StaticBase.colorResIds);
        srl_ma_tradingFloor.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                delegateList.clear();
                next_id = "0";
                initView();

            }
        });


    }

    @Click({R.id.operation})
    void click(View view){
        switch ( view.getId()){
            case R.id.operation:
                HisWeiTuoActivity.toActivity(MandatoryAdministrationActivity.this,symbol);
                break;
        }
    }



    public void initView(){
        String types = "2";
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

                    if(coinsDetailBean.getRecord_list() != null && coinsDetailBean.getRecord_list().size()>0) {
                            delegateList.addAll(coinsDetailBean.getRecord_list());
                    } else {
                        GetToastUtil.getToads(MandatoryAdministrationActivity.this,getResources().getString(R.string.act_base_nodata));
                    }
                }else {
                    GetToastUtil.getToads(MandatoryAdministrationActivity.this,coinsDetailBean.getMessage());
                }
                if(b) {
                    maAdapter = new MandatoryAdministrationAdapter(MandatoryAdministrationActivity.this, delegateList,
                            new WeiTuoMangerDao() {
                                @Override
                                public void chexiao(int index) {
                                    chexiaoweituo(index);
                                }
                            }
                    );
                    lv_ma_tradingFloor.setAdapter(maAdapter);
                    b = false;
                }else {
                    maAdapter.notifyDataSetChanged();
                }
                srl_ma_tradingFloor.setRefreshing(false);
            }

            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {
                GetToastUtil.getToads(MandatoryAdministrationActivity.this,t.getMessage());
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

        restService.get(MandatoryAdministrationActivity.this, "loading...", call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                BaseBean baseBean =(BaseBean) response.body();
                if(baseBean.getErrorCode().equals("0")){
                    delegateList.remove(index);
                    maAdapter.notifyDataSetChanged();
                }else {
                    GetToastUtil.getToads(MandatoryAdministrationActivity.this,baseBean.getMessage());
                }

            }

            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {
                GetToastUtil.getToads(MandatoryAdministrationActivity.this,t.getMessage());
            }
        });

    }

}
