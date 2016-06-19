package com.global.globalonline.activities.BaseActivie;

import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.base.MyApplication;
import com.global.globalonline.base.StaticBase;
import com.global.globalonline.tools.GetSelectBouncedUtil;

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
        String name = GetSelectBouncedUtil.getBankName(CertificationResultsActivity.this, StaticBase.CARTYPE,MyApplication.userBean.getIdentity_info().getIdentitytype());
        tv_cardtype.setText(name);
        tv_cardnumber.setText(MyApplication.userBean.getIdentity_info().getIdentityno());

    }
}
