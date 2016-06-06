package com.global.globalonline.activities.user;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.global.globalonline.R;
import com.global.globalonline.activities.BaseActivie.CertificationResultsActivity_;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.base.StaticBase;
import com.global.globalonline.bean.UserBean;
import com.global.globalonline.service.CallBackService;
import com.global.globalonline.service.GetRetrofitService;
import com.global.globalonline.service.RestService;
import com.global.globalonline.service.serviceImpl.RestServiceImpl;
import com.global.globalonline.service.user.UserService;
import com.global.globalonline.tools.GetCheckoutET;
import com.global.globalonline.tools.GetSelectBouncedUtil;
import com.global.globalonline.tools.MapToParams;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

@EActivity(R.layout.activity_certification)
public class CertificationActivity extends BaseActivity {

    @ViewById
    Button bt_tijiao;
    @ViewById
    EditText et_name,et_card;
    @ViewById
    TextView et_cardtype;

    @AfterViews
    void init(){

       // GetSelectBouncedUtil.get(this,et_cardtype, StaticBase.BANKTYPE,"39");
        String index = "0";
        GetSelectBouncedUtil.get(this,et_cardtype, StaticBase.BANKTYPE,index);
    }


    @Click({R.id.bt_tijiao})
    void click(View view){

        switch (view.getId()){
            case R.id.bt_tijiao:
                tijiao();
                //CertificationResultsActivity_.intent(this).start();
                break;

        }

    }


    public  void tijiao(){


        boolean b =  GetCheckoutET.checkout(getApplicationContext(),et_name,et_card);

        if(!b){
            return;
        }
        String name = et_name.getText().toString();
        String card = et_card.getText().toString();
        String card_type = et_cardtype.getTag().toString();

        UserService userService = GetRetrofitService.getRestClient(UserService.class);

        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("name", name);
        stringMap.put("card_type", card_type);
        stringMap.put("card_id", card);

        stringMap = MapToParams.getParsMap(stringMap);

        Call<UserBean> call = userService.identify_auth(stringMap);
        RestService restService = new RestServiceImpl();
        restService.get(CertificationActivity.this,"",call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                UserBean userBean =   ((UserBean)response.body());

                if(userBean.getErrorCode().equals("0")) {
                    //MyApplication.userBean = userBean;
                    CertificationResultsActivity_.intent(CertificationActivity.this).start();
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), userBean.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {

            }
        });
    }
}
