package com.global.globalonline.activities.RMB;

import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.base.StaticBase;
import com.global.globalonline.tools.GetSelectBouncedUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_withdrawal_rmb)
public class WithdrawalRMBActivity extends BaseActivity {
    @ViewById
    TextView tv_banktype;


    @AfterViews
    void init(){

        GetSelectBouncedUtil.get(this,tv_banktype, StaticBase.BANK,"0");
    }

}
