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
    LinearLayout details;


    KLineService kLineService;
    String symbol = "";

    public static  void toActivity(Activity activity,String symbol){

        Intent intent  = new Intent(activity, KLineActivity_.class);
        intent.putExtra("symbol",symbol);
        activity.startActivity(intent);

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

        initView();

    }


    public void onEvent(CombinedChartEntity empty) {
        // 填充数据

    }



    public void initView(){
        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("symbol",symbol);
        stringMap.put("limit","50");
        stringMap.put("step","5");
        stringMap = MapToParams.getParsMap(stringMap);
        Call<CombinedChartEntity> call = kLineService.kline(stringMap);

          RestService restService = new RestServiceImpl();

        restService.get(KLineActivity.this, "loading...", call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                CombinedChartEntity combinedChartEntity =(CombinedChartEntity) response.body();
                //JSONObject combinedChartEntity =(JSONObject) response.body();
                if(combinedChartEntity.getErrorCode() == 0){




                    combinedChart.setData(combinedChartEntity);
                    barChart.setData(combinedChartEntity);

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
