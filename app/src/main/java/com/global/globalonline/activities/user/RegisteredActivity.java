package com.global.globalonline.activities.user;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.global.globalonline.R;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.base.StaticBase;
import com.global.globalonline.bean.CodeBean;
import com.global.globalonline.bean.UserBean;
import com.global.globalonline.dao.TimeCount;
import com.global.globalonline.service.CallBackService;
import com.global.globalonline.service.GetRetrofitService;
import com.global.globalonline.service.RestService;
import com.global.globalonline.service.serviceImpl.RestServiceImpl;
import com.global.globalonline.service.user.UserService;
import com.global.globalonline.tools.GetSelectBouncedUtil;
import com.global.globalonline.tools.GetToastUtil;
import com.global.globalonline.tools.MapToParams;
import com.global.globalonline.tools.StringUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 注册
 */
@EActivity(R.layout.activity_registered)
public class RegisteredActivity extends BaseActivity {



    @ViewById
    EditText  et_phone,et_yanzhengma,et_pwd,et_rePwd,et_tuijianren;

    @ViewById
    Button btn_send_code,btn_tijiao;
    @ViewById
    TextView tv_guojia;

    UserService userService;
    Map<String, String> stringMap;
    TimeCount time;
    CodeBean codeBean;

    @AfterViews
    void init(){
        userService = GetRetrofitService.getRestClient(UserService.class);
        GetSelectBouncedUtil.get(this,tv_guojia, StaticBase.COUTRY,"0");
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

    String phone = et_phone.getText().toString();
    String stype = "1";



    stringMap = new HashMap<String, String>();
    stringMap.put("mobile", phone);
    stringMap.put("stype", stype);


    Map<String,String> parsMapBean = MapToParams.getParsMap(stringMap);

    Call<CodeBean> call = userService.send_authcode(parsMapBean);
    call.enqueue(new Callback<CodeBean>() {
        @Override
        public void onResponse(Call<CodeBean> call, Response<CodeBean> response) {
             codeBean = response.body();
            if(codeBean.getErrorCode().equals("0")){
                time = new TimeCount(30000, 1000,btn_send_code);
                time.start();
                Toast.makeText(getApplicationContext(),"验证码发送成功",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(),codeBean.getMessage(),Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onFailure(Call<CodeBean> call, Throwable t) {

            String a = "";
        }
    });

}
    public void tijiao(){

        String recommend = et_tuijianren.getText() == null ?"0":et_tuijianren.getText().toString();
        if(StringUtil.isBlank(recommend)){
            recommend = "0";
        }

        String mobile = et_phone.getText().toString();
        String pwd = et_pwd.getText().toString();
        String country = tv_guojia.getTag().toString();
        String code = et_yanzhengma.getText().toString();
        String codetype = codeBean.getCodetype();


         stringMap = new HashMap<String, String>();
         stringMap.put("mobile",mobile);
         stringMap.put("pwd",pwd);
         stringMap.put("country",country);

         stringMap.put("code",code);
         stringMap.put("codetype",codetype);
         stringMap.put("recommend",recommend);

        Map<String,String> parsMap = MapToParams.getParsMap(stringMap);

        Call<UserBean> call = userService.register(parsMap);

        RestService restService = new RestServiceImpl();
        restService.get(RegisteredActivity.this,"",call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {
                UserBean userBean = (UserBean) response.body();
                if(userBean.getErrorCode().equals("0")){
                    GetToastUtil.getToads(getApplicationContext(),"注册成功");
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