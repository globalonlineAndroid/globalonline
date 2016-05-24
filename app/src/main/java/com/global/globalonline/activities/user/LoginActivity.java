package com.global.globalonline.activities.user;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.activities.mainTab.MainActivity_;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.base.UrlApi;
import com.global.globalonline.bean.UserBean;
import com.global.globalonline.service.user.LoginService;
import com.global.globalonline.tools.MapToParams;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * 登陆
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @ViewById
    TextView operation;
    @ViewById
    Button bt_loging;


    @AfterViews
     void init() {

    }
    @Click({R.id.operation,R.id.bt_loging})
    void click(View view){
        switch (view.getId()){
            case R.id.operation:
                Intent  intent  = new Intent(getApplicationContext(),RegisteredActivity_.class);
                startActivity(intent);
                break;
            case R.id.bt_loging:
                loging();
                break;
        }
    }


    public void loging(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlApi.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        LoginService loginService = retrofit.create(LoginService.class);

        Map<String,String>  stringStringMap  = new HashMap<String,String>();
        stringStringMap.put("mobile","13811111111");
        stringStringMap.put("pwd","lixuefei");
        stringStringMap.put("timestamp",(new Date()).getTime() + "");

        Call<UserBean> call = loginService.loging("13811111111","lixuefei",(new Date()).getTime() + "",MapToParams.getToken(stringStringMap));
        call.enqueue(new Callback<UserBean>() {
            @Override
            public void onResponse(Response<UserBean> response, Retrofit retrofit) {
                String a = "1";

                Intent i = new Intent(getApplicationContext(), MainActivity_.class);
                startActivity(i);
                finish();

            }

            @Override
            public void onFailure(Throwable t) {
                String a = "2";

            }
        });

    }

}
