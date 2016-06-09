package com.global.globalonline.activities.user;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.global.globalonline.R;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.base.MyApplication;
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
import retrofit2.Response;

/**
 * 修改交易密码
 */
@EActivity(R.layout.activity_change_trade_password)
public class ChangeTradePasswordActivity extends BaseActivity {

    @ViewById
    EditText et_trade_pwd, et_trade_repwd,et_yanzhengma;
    @ViewById
    Button btn_send_code,btn_tijiao;
    @ViewById
    TextView tv_phone;
    CodeBean codeBean;

    Map<String, String> stringMap;
    UserService userService;
    TimeCount time;
    @AfterViews
    void init(){
         tv_phone.setText(MyApplication.userBean.getMobile());
         userService = GetRetrofitService.getRestClient(UserService.class);
    }


    @Click({R.id.btn_send_code,R.id.btn_tijiao})
    void click(View view){
        switch (view.getId()){
            case R.id.btn_send_code:

                send_code();
                break;
            case R.id.btn_tijiao:
                tijiao();
                break;
        }
    }

    public void send_code() {
        stringMap = new HashMap<String, String>();
        String phone = tv_phone.getText().toString();

        stringMap = new HashMap<String, String>();
        stringMap.put("mobile", phone);
        stringMap.put("stype", "3");

        Map<String,String>  map= MapToParams.getParsMap(stringMap,"auth_key");

        RestService  restService = new RestServiceImpl();


        Call<CodeBean> call = userService.send_authcode(map);
        restService.get(null, "", call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {
                codeBean = (CodeBean) response.body();
                if(codeBean.getErrorCode().equals("0")){
                    time = new TimeCount(30000, 1000,btn_send_code);
                    time.start();
                    Toast.makeText(getApplicationContext(),"验证码发送成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),codeBean.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {

            }
        });



    }
    public void tijiao(){

        boolean b =  GetCheckoutET.checkout(getApplicationContext(),et_trade_pwd,et_trade_repwd,
                et_yanzhengma);

        if(!b){
            return;
        }
        String trade_pwd = et_trade_pwd.getText().toString();
        String trade_repwd = et_trade_repwd.getText().toString();
        String code = et_yanzhengma.getText().toString();

        stringMap = new HashMap<String, String>();
        stringMap.put("code",code);
        stringMap.put("codetype",codeBean.getCodetype());
        stringMap.put("new_pwd1",trade_pwd);
        stringMap.put("new_pwd1",trade_repwd);


        Map<String,String> parsMap = MapToParams.getParsMap(stringMap);

        Call<UserBean> call = userService.upt_trade_pwd(parsMap);

        RestService restService = new RestServiceImpl();
        restService.get(ChangeTradePasswordActivity.this,"",call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {
                UserBean userBean = (UserBean) response.body();
                if(userBean.getErrorCode().equals("0")){
                    GetToastUtil.getToads(getApplicationContext(),"修改成功");
                }else {

                    GetToastUtil.getToads(getApplicationContext(),userBean.getMessage());
                }
            }

            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {
                GetToastUtil.getToads(getApplicationContext(),t.getMessage());
            }
        });
    }


}
