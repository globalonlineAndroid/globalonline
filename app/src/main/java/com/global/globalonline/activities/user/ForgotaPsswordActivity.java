package com.global.globalonline.activities.user;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.global.globalonline.R;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.bean.CodeBean;
import com.global.globalonline.bean.UserBean;
import com.global.globalonline.dao.TimeCount;
import com.global.globalonline.service.CallBackService;
import com.global.globalonline.service.GetRetrofitService;
import com.global.globalonline.service.RestService;
import com.global.globalonline.service.serviceImpl.RestServiceImpl;
import com.global.globalonline.service.user.UserService;
import com.global.globalonline.tools.GetCheckoutET;
import com.global.globalonline.tools.GetToastUtil;
import com.global.globalonline.tools.MapToParams;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EActivity(R.layout.activity_forgota_pssword)
public class ForgotaPsswordActivity extends BaseActivity {

    @ViewById
    EditText et_phone,et_yanzhengma,et_pwd,et_rePwd;
    @ViewById
    Button btn_send_code,btn_tijiao;

    UserService userService;
    Map<String, String> stringMap;
    TimeCount time;
    CodeBean codeBean;
    @AfterViews
    void init(){
        userService = GetRetrofitService.getRestClient(UserService.class);

    }

    @Click({R.id.btn_send_code,R.id.btn_tijiao})
    void click(View view){
        switch (view.getId()){
            case R.id.btn_send_code:
                time = new TimeCount(60000, 1000,btn_send_code);
                send_code();
                break;
            case R.id.btn_tijiao:
                tijiao();
                break;
        }
    }

    public void  send_code() {

        String phone = et_phone.getText().toString();
        String stype = "2";
        stringMap = new HashMap<String, String>();
        stringMap.put("mobile", phone);
        stringMap.put("stype", stype);

        Map<String,String> parsMap = MapToParams.getParsMap(stringMap);

        Call<CodeBean> call = userService.send_authcode(parsMap);
        call.enqueue(new Callback<CodeBean>() {
            @Override
            public void onResponse(Call<CodeBean> call, Response<CodeBean> response) {
                codeBean = response.body();
                if (codeBean.getErrorCode().equals(0)) {
                    String a = "";
                    time.start();
                }else {

                }
            }

            @Override
            public void onFailure(Call<CodeBean> call, Throwable t) {
                String a = "";
            }
        });

    }
    public void tijiao(){

        boolean b =  GetCheckoutET.checkout(ForgotaPsswordActivity.this,et_phone,et_yanzhengma,et_pwd,et_rePwd);

        if(!b){
            return;
        }

        if( codeBean == null){
            GetToastUtil.getToads(getApplicationContext(),"请申请验证码");
            return;
        }
        String mobile= et_phone.getText().toString();
        String codetype=codeBean.getCodetype();
        String code=et_yanzhengma.getText().toString();
        String newpwd=et_pwd.getText().toString();
        String new2pwd=et_rePwd.getText().toString();
        String pwdtype="1";//1是密码，3是交易密码



        stringMap = new HashMap<String, String>();
        stringMap.put("mobile",mobile);
        stringMap.put("code",code);
        stringMap.put("codetype",codetype);
        stringMap.put("newpwd",newpwd);
        stringMap.put("new2pwd",new2pwd);
        stringMap.put("pwdtype",pwdtype);




        Map<String,String> parsMap = MapToParams.getParsMap(stringMap);
        Call<UserBean> call = userService.find_pwd(parsMap);

        RestService restService = new RestServiceImpl();
        restService.get(ForgotaPsswordActivity.this,"",call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {
                UserBean userBean = (UserBean) response.body();
                if(userBean.getErrorCode().equals("0")){
                    Toast.makeText(getApplicationContext(),"修改成功",Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),codeBean.getMessage(),Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {
                String a  = "";
            }
        });

    }
}
