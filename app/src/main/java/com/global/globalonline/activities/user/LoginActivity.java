package com.global.globalonline.activities.user;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.activities.mainTab.MainActivity_;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.base.MyApplication;
import com.global.globalonline.base.StaticBase;
import com.global.globalonline.bean.UserBean;
import com.global.globalonline.dao.DBHelper;
import com.global.globalonline.service.CallBackService;
import com.global.globalonline.service.GetRetrofitService;
import com.global.globalonline.service.RestService;
import com.global.globalonline.service.serviceImpl.RestServiceImpl;
import com.global.globalonline.service.user.UserService;
import com.global.globalonline.tools.GetCheckoutET;
import com.global.globalonline.tools.GetSelectBouncedUtil;
import com.global.globalonline.tools.GetToastUtil;
import com.global.globalonline.tools.MapToParams;
import com.global.globalonline.tools.SharedPreferencesUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * 登陆
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @ViewById
    TextView operation, tv_wangJiPWD,tv_guojia;
    @ViewById
    Button bt_loging;
   @ViewById
    EditText et_phone,et_pwd;


    DBHelper dbHelper = null;

    @AfterViews
    void init() {

        GetSelectBouncedUtil.get(LoginActivity.this,tv_guojia, StaticBase.COUTRY,"39");
        //initDate();
    }

    @Click({R.id.operation, R.id.bt_loging, R.id.tv_wangJiPWD})
    void click(View view) {
        switch (view.getId()) {
            case R.id.operation:
                RegisteredActivity_.intent(LoginActivity.this).start();
                break;
            case R.id.bt_loging:
               /* Intent i = new Intent(getApplicationContext(), MainActivity_.class);
                startActivity(i);
                finish();*/
                loging();
                break;
            case R.id.tv_wangJiPWD:
                ForgotaPsswordActivity_.intent(LoginActivity.this).start();
                break;
        }
    }


    public void loging() {

        boolean b =  GetCheckoutET.checkout(getApplicationContext(),et_phone,et_pwd);

        if(!b){
            return;
        }

        String tel = et_phone.getText().toString();
        String pwd = et_pwd.getText().toString();
        String country = tv_guojia.getTag().toString();

        UserService userService = GetRetrofitService.getRestClient(UserService.class);

        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("mobile", tel);
        stringMap.put("pwd", pwd);
        stringMap.put("country", country);

        stringMap = MapToParams.getParsMap(stringMap);

        Call<UserBean> call = userService.loging(stringMap);
        RestService  restService = new RestServiceImpl();
        restService.get(LoginActivity.this,"",call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                UserBean userBean =   ((UserBean)response.body());

                if(userBean.getErrorCode().equals("0")) {

                    MyApplication.userBean = userBean;
                    SharedPreferencesUtil.setUserInfo(LoginActivity.this,userBean);
                    Intent i = new Intent(getApplicationContext(), MainActivity_.class);
                    startActivity(i);
                    finish();
                }else {
                    GetToastUtil.getToads(getApplication(), userBean.getMessage());

                }
               // dialog.cancel();
            }
            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {
               // dialog.cancel();
                GetToastUtil.getToads(getApplication(), t.getMessage());
            }
        });
    }





}
