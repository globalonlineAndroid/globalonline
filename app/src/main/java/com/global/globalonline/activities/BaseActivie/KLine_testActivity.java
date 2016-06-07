package com.global.globalonline.activities.BaseActivie;

import android.app.Activity;
import android.content.Intent;

import com.global.globalonline.R;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.service.GetRetrofitService;
import com.global.globalonline.service.KLine.KLineService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_login)
public class KLine_testActivity extends BaseActivity {



    KLineService kLineService;
    String symbol = "";

    public static  void toActivity(Activity activity,String symbol){

        Intent intent  = new Intent(activity, KLine_testActivity_.class);
        intent.putExtra("symbol",symbol);
        activity.startActivity(intent);

    }

    @AfterViews
    void init(){
        symbol = getIntent().getStringExtra("symbol");

       // kLine.setVisibleXRange(100,100);
        kLineService = GetRetrofitService.getRestClient(KLineService.class);


      //  initView();
    }




   /* public void initView(){
        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("symbol","6");
        stringMap.put("limit","100");
        stringMap.put("step","3600");


        stringMap = MapToParams.getParsMap(stringMap);

        Call<CombinedChartTestEntity> call = kLineService.kline(stringMap);
        RestService restService = new RestServiceImpl();

        restService.get(KLine_testActivity.this, "loading...", call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                CombinedChartTestEntity combinedChartEntity =(CombinedChartTestEntity) response.body();
                if(combinedChartEntity.getErrorCode() == 0){

                   // combinedChart.setData(combinedChartEntity);
                 //   barChart.setData(combinedChartEntity);

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
*/

}
