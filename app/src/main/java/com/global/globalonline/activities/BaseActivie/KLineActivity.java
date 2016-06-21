package com.global.globalonline.activities.BaseActivie;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.Chart;
import com.global.globalonline.R;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.bean.CombinedChartEntity;
import com.global.globalonline.listener.CoupleChartGestureListener;
import com.global.globalonline.listener.OnValueSelectedListener;
import com.global.globalonline.service.CallBackService;
import com.global.globalonline.service.GetRetrofitService;
import com.global.globalonline.service.KLine.KLineService;
import com.global.globalonline.service.RestService;
import com.global.globalonline.service.serviceImpl.RestServiceImpl;
import com.global.globalonline.tools.GetToastUtil;
import com.global.globalonline.tools.MapToParams;
import com.global.globalonline.view.MyBarChart;
import com.global.globalonline.view.MyCombinedChart;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

@EActivity(R.layout.activity_kline)
public class KLineActivity extends BaseActivity implements OnValueSelectedListener {
    @ViewById
    MyCombinedChart combinedChart;
    @ViewById
    MyBarChart barChart;
    @ViewById
    TextView open,close,high,low;
    @ViewById
    TextView tv_xiaoshi_tab,tv_tian_tab,tv_zhou_tab,tv_yue_tab;

    @ViewById
    LinearLayout details;


    KLineService kLineService;
    String symbol = "";
    String limit = "50";
    String step = "5";

    public static  void toActivity(Activity activity,String symbol){

        Intent intent  = new Intent(activity, KLineActivity_.class);
        intent.putExtra("symbol",symbol);
        activity.startActivity(intent);


    }


    @Click({R.id.tv_xiaoshi_tab,R.id.tv_tian_tab,R.id.tv_zhou_tab,R.id.tv_yue_tab})
    void click(View view) {
        switch (view.getId()) {
            case R.id.tv_xiaoshi_tab:
                setTextBackgroud(tv_xiaoshi_tab);
                limit = "50";
                step = "60";
                initView();
                break;
            case R.id.tv_tian_tab:
                setTextBackgroud(tv_tian_tab);
                limit = "50";
                step = String.valueOf(60*24);
                initView();
                break;
            case R.id.tv_zhou_tab:
                setTextBackgroud(tv_zhou_tab);
                limit = "50";
                step = String.valueOf(60*24*7);
                initView();
                break;
            case R.id.tv_yue_tab:
                setTextBackgroud(tv_yue_tab);
                limit = "50";
                step = String.valueOf(60*24*30);
                initView();
                break;
        }


    }


    void setTextBackgroud(TextView tv){


        tv_xiaoshi_tab.setBackgroundResource(R.color.ac_virtual_chunk);
        tv_tian_tab.setBackgroundResource(R.color.ac_virtual_chunk);
        tv_zhou_tab.setBackgroundResource(R.color.ac_virtual_chunk);
        tv_yue_tab.setBackgroundResource(R.color.ac_virtual_chunk);
        tv.setBackgroundResource(R.color.ac_base_tab);


    }

    @AfterViews
    void init(){
        symbol = getIntent().getStringExtra("symbol");

       // kLine.setVisibleXRange(100,100);
        kLineService = GetRetrofitService.getRestClient(KLineService.class);
        // 将K线控的滑动事件传递给交易量控件
        combinedChart.setOnChartGestureListener(new CoupleChartGestureListener(combinedChart, new Chart[]{barChart}));
        // 将交易量控件的滑动事件传递给K线控件
        barChart.setOnChartGestureListener(new CoupleChartGestureListener(barChart, new Chart[]{combinedChart}));

        combinedChart.setOnValueSelectedListener(this);
        tv_xiaoshi_tab.performClick();
       // initView();


    }


    public void onEvent(CombinedChartEntity empty) {
        // 填充数据

    }



    public void initView(){
        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("symbol",symbol);
        stringMap.put("limit",limit);
        stringMap.put("step",step);
        stringMap = MapToParams.getParsMap(stringMap);
        Call<CombinedChartEntity> call = kLineService.kline(stringMap);

          RestService restService = new RestServiceImpl();

        restService.get(KLineActivity.this, "loading...", call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                CombinedChartEntity combinedChartEntity =(CombinedChartEntity) response.body();
                //JSONObject combinedChartEntity =(JSONObject) response.body();
                if(combinedChartEntity.getErrorCode() == 0){


                   /* List<List<String>> list = new ArrayList<List<String>>();
                    for (int i = 0; i < combinedChartEntity.getK().size(); i++) {
                        List<String> a = new ArrayList<String>();

                        float count = Float.parseFloat(combinedChartEntity.getK().get(i).get(5));
                        if(count > 0) {
                            a.addAll(combinedChartEntity.getK().get(i));
                            list.add(a);
                        }

                    }

                    combinedChartEntity.setK(list) ;*/




                   // if (list.size()>0) {
                        combinedChart.setData(combinedChartEntity);
                        barChart.setData(combinedChartEntity);
                   // }

                }else {
                   // GetToastUtil.getToads(getApplicationContext(),combinedChartEntity.getMessage());
                }
            }

            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {
                GetToastUtil.getToads(getApplicationContext(),t.getMessage());
            }
        });
    }

    @Override
    public void start() {

        details.setVisibility(View.VISIBLE);
    }

    @Override
    public void data(double open, double close, double high, double low) {
        details.setVisibility(View.VISIBLE);
        this.open.setText(String.valueOf(open));
        this.close.setText(String.valueOf(close));
        this.high.setText(String.valueOf(high));
        this.low.setText(String.valueOf(low));
    }

    @Override
    public void end()
    {
        details.setVisibility(View.GONE);
    }
}
