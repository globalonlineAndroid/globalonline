package com.global.globalonline.activities.virtualCurrency;

import android.view.View;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.activities.HistoricalRecord.ZhuanChuVirtualFlowActivity_;
import com.global.globalonline.base.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.act_archived)
public class ArchivedActivity extends BaseActivity {


    @ViewById
    TextView operation;

    @AfterViews
    void init(){

    }
    @Click({R.id.operation})
    void click(View view){
        switch (view.getId())
        {
            case R.id.operation:
                ZhuanChuVirtualFlowActivity_.intent(ArchivedActivity.this).start();
                break;
        }
    }

}
