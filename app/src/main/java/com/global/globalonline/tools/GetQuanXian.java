package com.global.globalonline.tools;

import android.app.Activity;

import com.global.globalonline.R;
import com.global.globalonline.activities.user.CertificationActivity_;
import com.global.globalonline.activities.user.LoginActivity_;
import com.global.globalonline.base.MyApplication;
import com.global.globalonline.dao.TishiResDao;

/**
 * Created by lijl on 16/6/19.
 */
public class GetQuanXian {

public static  boolean getIsQuanXian(final Activity activity){

    boolean b = false;
    String str = "";
        if(MyApplication.userBean != null) {
           b=true;
        }else {


            GetDialogUtil.tishi(activity, null, activity.getResources().getString(R.string.act_base_nologin), new TishiResDao() {
                @Override
                public void getTiShi(String args) {
                    LoginActivity_.intent(activity).start();
                }
            });
            b = false;
            return b;
        }

        if (MyApplication.userBean.getIdentity_info() != null
                && MyApplication.userBean.getIdentity_info().getIdentityno() != null
                && !MyApplication.userBean.getIdentity_info().getIdentityno().equals("")
                ) {
            b=true;

        }else {
            GetDialogUtil.tishi(activity, null, activity.getResources().getString(R.string.act_base_norenzhu),
                            activity.getResources().getString(R.string.act_base_gorenzheng),
                            activity.getResources().getString(R.string.act_base_yirenzheng),
                            new TishiResDao() {
                                @Override
                                public void getTiShi(String args) {
                                    CertificationActivity_.intent(activity).start();
                                }
                            },
                            new TishiResDao() {
                                @Override
                                public void getTiShi(String args) {
                                    LoginActivity_.intent(activity).start();
                                }
                            });

            b = false;
            return b;

        }

    return b;
    }
}
