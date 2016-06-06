package com.global.globalonline.activities.user;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.global.globalonline.R;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.bean.UserBean;
import com.global.globalonline.service.CallBackService;
import com.global.globalonline.service.GetRetrofitService;
import com.global.globalonline.service.RestService;
import com.global.globalonline.service.serviceImpl.RestServiceImpl;
import com.global.globalonline.service.user.UserService;
import com.global.globalonline.tools.GetCheckoutET;
import com.global.globalonline.tools.GetToastUtil;
import com.global.globalonline.tools.MapToParams;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * 修改登陆密码
 */
@EActivity(R.layout.activity_change_login_password)
public class ChangeLoginPasswordActivity extends BaseActivity {
        @ViewById
    EditText et_oldpwd,et_pwd,et_repwd;
    @ViewById
    Button btn_tijiao;


    @Click({R.id.btn_tijiao})
    void click(View view) {
        switch (view.getId()) {
            case R.id.btn_tijiao:
                tijiao();
                break;

        }
    }
    public void tijiao() {

        boolean b =  GetCheckoutET.checkout(getApplicationContext(),et_oldpwd,et_pwd,
                et_repwd);

        if(!b){
            return;
        }

        String oldpwd = et_oldpwd.getText().toString();
        String pwd = et_pwd.getText().toString();
        String repwd = et_repwd.getText().toString();

        UserService userService = GetRetrofitService.getRestClient(UserService.class);

        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("old_pwd", oldpwd);
        stringMap.put("new_pwd1", pwd);
        stringMap.put("new_pwd2", repwd);

        stringMap = MapToParams.getParsMap(stringMap);

        Call<UserBean> call = userService.upt_login_pwd(stringMap);
        RestService restService = new RestServiceImpl();
        restService.get(ChangeLoginPasswordActivity.this,"",call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                UserBean userBean =   ((UserBean)response.body());

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
