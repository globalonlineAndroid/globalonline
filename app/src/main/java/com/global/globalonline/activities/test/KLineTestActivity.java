package com.global.globalonline.activities.test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.global.globalonline.R;
import com.global.globalonline.bean.CombinedChartEntity;
import com.global.globalonline.service.CallBackService;
import com.global.globalonline.service.GetRetrofitService;
import com.global.globalonline.service.KLine.KLineService;
import com.global.globalonline.service.RestService;
import com.global.globalonline.service.serviceImpl.RestServiceImpl;
import com.global.globalonline.tools.GetToastUtil;
import com.global.globalonline.tools.MapToParams;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

@EActivity(R.layout.activity_kline_test)
public class KLineTestActivity extends AppCompatActivity {

    @ViewById
    CandleStickChart cc_k;

    KLineService kLineService;
    String symbol = "";

    public static  void toActivity(Activity activity, String symbol){

        Intent intent  = new Intent(activity, KLineTestActivity_.class);
        intent.putExtra("symbol",symbol);
        activity.startActivity(intent);

    }
    @AfterViews
    void init(){
        symbol = getIntent().getStringExtra("symbol");
        kLineService = GetRetrofitService.getRestClient(KLineService.class);
        cc_k.setBackgroundColor(Color.WHITE);

        cc_k.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        cc_k.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        cc_k.setPinchZoom(false);

        cc_k.setDrawGridBackground(false);


        cc_k.setDrawGridBackground(false);

        XAxis xAxis = cc_k.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setSpaceBetweenLabels(2);
        xAxis.setDrawGridLines(false);

        YAxis leftAxis = cc_k.getAxisLeft();
//        leftAxis.setEnabled(false);
        leftAxis.setLabelCount(7, false);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawAxisLine(false);

        YAxis rightAxis = cc_k.getAxisRight();
        rightAxis.setEnabled(false);

        cc_k.getLegend().setEnabled(false);

        initView();

    }


    public void initView(){
        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("symbol","6");
        stringMap.put("limit","50");
        stringMap.put("step","5");
        stringMap = MapToParams.getParsMap(stringMap);
        Call<CombinedChartEntity> call = kLineService.kline(stringMap);

        RestService restService = new RestServiceImpl();

        restService.get(KLineTestActivity.this, "loading...", call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                CombinedChartEntity combinedChartEntity =(CombinedChartEntity) response.body();
                //JSONObject combinedChartEntity =(JSONObject) response.body();



                int prog = combinedChartEntity.getK().size();

               // tvX.setText("" + prog);
               // tvY.setText("" + (mSeekBarY.getProgress()));

                cc_k.resetTracking();

                ArrayList<CandleEntry> yVals1 = new ArrayList<CandleEntry>();

                for (int i = 0; i < prog; i++) {
                    /*float mult = (mSeekBarY.getProgress() + 1);
                    float val = (float) (Math.random() * 40) + mult;
                    float open = (float) (Math.random() * 6) + 1f;
                    float high = (float) (Math.random() * 9) + 8f;
                    float low = (float) (Math.random() * 9) + 8f;
                    float close = (float) (Math.random() * 6) + 1f;*/


                    float open = Float.parseFloat(combinedChartEntity.getK().get(i).get(1) );
                    float high =Float.parseFloat(combinedChartEntity.getK().get(i).get(2) );
                    float low = Float.parseFloat(combinedChartEntity.getK().get(i).get(3) );
                    float close = Float.parseFloat(combinedChartEntity.getK().get(i).get(4) );

                    boolean even = i % 2 == 0;

                  /*  yVals1.add(new CandleEntry(i, val + high, val - low, even ? val + open : val - open,
                            even ? val - close : val + close));*/
                    yVals1.add(new CandleEntry(i, high,  low,  open ,
                             close));
                   /* yVals1.add(new CandleEntry(i, open,  high,  low ,
                            close));*/

                }

                ArrayList<String> xVals = new ArrayList<String>();
                for (int i = 0; i < prog; i++) {
                    xVals.add("" + (1990 + i));
                }

                CandleDataSet set1 = new CandleDataSet(yVals1, "Data Set");
                set1.setAxisDependency(YAxis.AxisDependency.LEFT);
//        set1.setColor(Color.rgb(80, 80, 80));
                set1.setShadowColor(Color.DKGRAY);
                set1.setShadowWidth(0.7f);
                set1.setDecreasingColor(Color.RED);
                set1.setDecreasingPaintStyle(Paint.Style.FILL);
                set1.setIncreasingColor(Color.rgb(122, 242, 84));
                set1.setIncreasingPaintStyle(Paint.Style.STROKE);
                set1.setNeutralColor(Color.BLUE);
                //set1.setHighlightLineWidth(1f);

                CandleData data = new CandleData(xVals, set1);

                cc_k.setData(data);
                cc_k.invalidate();







               /* ArrayList<String> timeY = new ArrayList<>();
                for (int i = 0; i < combinedChartEntity.getK().size(); i++) {
                    timeY.add(combinedChartEntity.getK().get(i).get(0) + "");
                }
               // CombinedData data = new CombinedData(timeY);


                List<ICandleDataSet> iCandleDataSet = new ArrayList<ICandleDataSet>() ;

                    ArrayList<CandleEntry> entries = new ArrayList<>();
                    for (int index = 0; index < combinedChartEntity.getK().size(); index++) {
                        float a = Float.parseFloat(combinedChartEntity.getK().get(index).get(1) );
                        float b = Float.parseFloat(combinedChartEntity.getK().get(index).get(2)) ;
                        float c = Float.parseFloat(combinedChartEntity.getK().get(index).get(3) );
                        float dd = Float.parseFloat(combinedChartEntity.getK().get(index).get(4) );
                        entries.add(new CandleEntry(index, a, b, c, dd));

                    }

                    CandleDataSet set = new CandleDataSet(entries, "K线");
                    // 不显示横向高亮线
                    set.setDrawHorizontalHighlightIndicator(false);
                    set.setColor(Color.rgb(80, 80, 80));
                    set.setValueTextSize(10f);
                    set.setDrawValues(false);

                    CandleData d = new CandleData(timeY);
                    cc_k.setData(d);
*/

            }

            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {
                GetToastUtil.getToads(getApplicationContext(),t.getMessage());
            }
        });
    }
}
