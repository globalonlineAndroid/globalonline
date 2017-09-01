package com.global.globalonline.activities.setting;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.activities.BaseActivie.CertificationResultsActivity_;
import com.global.globalonline.activities.BaseActivie.WebViewActivity;
import com.global.globalonline.activities.user.CertificationActivity_;
import com.global.globalonline.activities.user.ChangeLoginPasswordActivity_;
import com.global.globalonline.activities.user.ChangeTradePasswordActivity_;
import com.global.globalonline.activities.user.LoginActivity_;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.base.MyApplication;
import com.global.globalonline.dao.TishiResDao;
import com.global.globalonline.tools.GetDialogUtil;
import com.global.globalonline.tools.GetQuanXian;
import com.zbar.lib.CaptureActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_setting)
public class SettingActivity extends BaseActivity {

    @ViewById
    LinearLayout ll_renZheng,ll_updateLoginPWD,ll_updateJiaoYiPWD, ll_message,ll_woMen,ll_exit;
    @ViewById
    TextView tv_isRenZheng;




    @AfterViews
    void init (){

        try {

            if(GetQuanXian.getIsQuanXian(this)) {


                tv_isRenZheng.setText(getResources().getString(R.string.act_base_yirenzheng));

            }

        }catch (Exception e){
            LoginActivity_.intent(this).start();
        }
        //initView();

    }





    @Click({R.id.ll_renZheng,R.id.ll_updateLoginPWD,R.id.ll_updateJiaoYiPWD,R.id.ll_message,R.id.ll_woMen,R.id.ll_exit})
    void click(View view){

        switch (view.getId()){

            case R.id.ll_fuKuan:
                Intent intentFuKuan = new Intent(this, CaptureActivity.class);
                startActivity(intentFuKuan);

                break;
            case R.id.ll_renZheng:
                if(MyApplication.userBean.getIdentity_info() != null){
                    CertificationResultsActivity_.intent(this).start();
                }else {
                    CertificationActivity_.intent(this).start();
                }
                break;
            case R.id.ll_updateLoginPWD:
                ChangeLoginPasswordActivity_.intent(this).start();
                break;
            case R.id.ll_updateJiaoYiPWD:
                ChangeTradePasswordActivity_.intent(this).start();
                break;
            case R.id.ll_message:
                break;
            case R.id.ll_woMen:
                //AboutUsActivity_.intent(this).start();
                WebViewActivity.toActivity(this,"guanyu");
                break;
            case R.id.ll_exit:

                GetDialogUtil.tishi(this, getResources().getString(R.string.act_base_tishi),
                        getResources().getString(R.string.act_base_istuichu), new TishiResDao() {
                            @Override
                            public void getTiShi(String args) {
                                MyApplication.userBean = null;
                                LoginActivity_.intent(SettingActivity.this).start();
                            }
                        });

                break;

        }

    }
}
