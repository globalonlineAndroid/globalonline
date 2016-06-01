package com.global.globalonline.activities.mainTab;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.activities.user.CertificationActivity_;
import com.global.globalonline.activities.user.ChangeLoginPasswordActivity_;
import com.global.globalonline.activities.user.ChangeTradePasswordActivity_;
import com.global.globalonline.base.MyApplication;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.activity_my)
public class MyFrament extends Fragment {

    @ViewById
    TextView tv_loginName,tv_ename,tv_isRenZheng;

    @ViewById
    LinearLayout ll_RMBPay,ll_RMBTiXian,ll_XuNiPay,ll_XuNiTiXian,ll_weiTuoManager,ll_jiaoYiLiuShui,ll_shouKuan,
            ll_fuKuan,ll_renZheng,ll_updateLoginPWD,ll_updateJiaoYiPWD, ll_message,ll_woMen,ll_exit;



    @AfterViews
    void init (){

        tv_loginName.setText(MyApplication.userBean.getMobile());
        tv_ename.setText(MyApplication.userBean.getUsername());

        if(MyApplication.userBean.getIdentity_info() != null){
            tv_isRenZheng.setText("已认证");
        }

    }

    @Click({R.id.ll_RMBPay,R.id.ll_RMBTiXian,R.id.ll_XuNiPay,R.id.ll_XuNiTiXian,R.id.ll_weiTuoManager,R.id.ll_jiaoYiLiuShui,R.id.ll_shouKuan
            ,R.id.ll_fuKuan,R.id.ll_renZheng,R.id.ll_updateLoginPWD,R.id.ll_updateJiaoYiPWD,R.id.ll_message,R.id.ll_woMen,R.id.ll_exit})
    void click(View view){

        switch (view.getId()){
            case R.id.ll_RMBPay:
                break;
            case R.id.ll_RMBTiXian:
                break;
            case R.id.ll_XuNiPay:
                break;
            case R.id.ll_XuNiTiXian:
                break;
            case R.id.ll_weiTuoManager:
                break;
            case R.id.ll_jiaoYiLiuShui:
                break;
            case R.id.ll_shouKuan:
                break;
            case R.id.ll_fuKuan:
                break;
            case R.id.ll_renZheng:
                CertificationActivity_.intent(getActivity()).start();
                break;
            case R.id.ll_updateLoginPWD:
                ChangeLoginPasswordActivity_.intent(getActivity()).start();
                break;
            case R.id.ll_updateJiaoYiPWD:
                ChangeTradePasswordActivity_.intent(getActivity()).start();
                break;
            case R.id.ll_message:
                break;
            case R.id.ll_woMen:
                break;
            case R.id.ll_exit:
                break;

        }

    }


}
