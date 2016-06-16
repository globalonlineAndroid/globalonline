package com.global.globalonline.activities.BaseActivie;

import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.base.MyApplication;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.act_certification_results)
public class CertificationResultsActivity extends BaseActivity {
        @ViewById
        TextView tv_name,tv_cardtype,tv_cardnumber;


    @AfterViews
    void init(){
        tv_name.setText(MyApplication.userBean.getIdentity_info().getName());
        tv_cardtype.setText(MyApplication.userBean.getIdentity_info().getIdentitytype());
        tv_cardnumber.setText(MyApplication.userBean.getIdentity_info().getIdentityno());

    }
}
