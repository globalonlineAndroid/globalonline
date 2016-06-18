package com.global.globalonline.activities.account;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.activities.BaseActivie.PayResultsActivity;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.base.GetConfiguration;
import com.global.globalonline.bean.BaseBean;
import com.global.globalonline.bean.CoinsDetailBean;
import com.global.globalonline.service.CallBackService;
import com.global.globalonline.service.GetRetrofitService;
import com.global.globalonline.service.RestService;
import com.global.globalonline.service.serviceImpl.RestServiceImpl;
import com.global.globalonline.service.virtualTrading.VirtualService;
import com.global.globalonline.tools.GetToastUtil;
import com.global.globalonline.tools.MapToParams;
import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

@EActivity(R.layout.activity_pay)
public class PayActivity extends BaseActivity {
    @ViewById
    Button bt_tijiao;
    @ViewById
    TextView tv_zhuanghu,tv_bizhong,tv_number;
    @ViewById
    EditText et_rmb,et_tradePwd;

    String str = "";
    Map<String,String > map = new HashMap<>();
    String price = "";

    VirtualService virtualService;
    @AfterViews
    void init(){
        str = getIntent().getStringExtra("str");
        map = new Gson().fromJson(str,Map.class);
        tv_zhuanghu.setText(new DecimalFormat("#").format(map.get("userid")));
        virtualService = GetRetrofitService.getRestClient(VirtualService.class);
        initView();


    }


    @Click({R.id.bt_tijiao})
    void clik(View view){

        switch (view.getId()){
            case R.id.bt_tijiao:
                tijiao();
                break;
        }

    }

    void addText(){
        et_rmb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s != null) {
                    if (s.toString().equals("")) {
                        tv_number.setText("0");
                    } else {
                        String rmb = et_rmb.getText().toString();

                        float  numberFloat =  Float.parseFloat(rmb)/Float.parseFloat(price);
                        tv_number.setText(String.valueOf(numberFloat));
                    }
                }else {
                    tv_number.setText("0");
                }

            }
        });
    }




    public void initView(){
        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("symbol",map.get("sysid").toString());

        stringMap = MapToParams.getParsMap(stringMap);

        Call<CoinsDetailBean> call = virtualService.coins_detail(stringMap);
        RestService restService = new RestServiceImpl();

        restService.get(PayActivity.this, "loading...", call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                CoinsDetailBean coinsDetailBean =(CoinsDetailBean) response.body();
                if(coinsDetailBean.getErrorCode().equals("0")){
                    price = coinsDetailBean.getPrice();
                    if(GetConfiguration.isZh()) {
                        tv_bizhong.setText(coinsDetailBean.getName());
                    }else {
                        tv_bizhong.setText(coinsDetailBean.getEname());
                    }
                    addText();
                }else {
                    GetToastUtil.getToads(PayActivity.this,coinsDetailBean.getMessage());
                }
            }

            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {
                GetToastUtil.getToads(PayActivity.this,t.getMessage());
            }
        });
    }


    public void tijiao(){

        final String symbol = map.get("sysid").toString();
        final String receiver_id = new DecimalFormat("#").format(map.get("userid"));
        final String money = et_rmb.getText().toString();
        final String trade_pwd = et_tradePwd.getText().toString();

        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("symbol",symbol);
        stringMap.put("receiver_id",receiver_id);
        stringMap.put("money",money);
        stringMap.put("trade_pwd",trade_pwd);

        stringMap = MapToParams.getParsMap(stringMap);

        Call<BaseBean> call = virtualService.collect_money(stringMap);
        RestService restService = new RestServiceImpl();

        restService.get(PayActivity.this, "loading...", call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                BaseBean baseBean =(BaseBean) response.body();
                if(baseBean.getErrorCode().equals("0")){
                    PayResultsActivity.toActivity(PayActivity.this,symbol,receiver_id,money,tv_number.getText().toString());
                }else {
                    GetToastUtil.getToads(PayActivity.this,baseBean.getMessage());
                }
            }

            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {
                GetToastUtil.getToads(PayActivity.this,t.getMessage());
            }
        });
    }




}
