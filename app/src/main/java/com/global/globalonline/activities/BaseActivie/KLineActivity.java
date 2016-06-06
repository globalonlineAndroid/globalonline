package com.global.globalonline.activities.BaseActivie;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.global.globalonline.R;
import com.global.globalonline.base.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_kline)
public class KLineActivity extends BaseActivity {
    @ViewById
    CandleStickChart kLine;



    @AfterViews
    void init(){
       // kLine.setVisibleXRange(100,100);

    }

}
