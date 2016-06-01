package com.global.globalonline.activities.user;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.global.globalonline.R;
import com.global.globalonline.activities.mainTab.MainActivity_;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.base.MyApplication;
import com.global.globalonline.base.StaticBase;
import com.global.globalonline.bean.BankBean;
import com.global.globalonline.bean.CardTypeBean;
import com.global.globalonline.bean.ConfigBean;
import com.global.globalonline.bean.CoutryBean;
import com.global.globalonline.bean.IncomebankBean;
import com.global.globalonline.bean.ParsMapBean;
import com.global.globalonline.bean.UserBean;
import com.global.globalonline.bean.VirtualcoinBean;
import com.global.globalonline.dao.DBHelper;
import com.global.globalonline.db.bean.DataSource;
import com.global.globalonline.service.CallBackService;
import com.global.globalonline.service.ConfigService;
import com.global.globalonline.service.GetRetrofitService;
import com.global.globalonline.service.RestService;
import com.global.globalonline.service.serviceImpl.RestServiceImpl;
import com.global.globalonline.service.user.UserService;
import com.global.globalonline.tools.GetToastUtil;
import com.global.globalonline.tools.MapToParams;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * 登陆
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @ViewById
    TextView operation, tv_wangJiPWD;
    @ViewById
    Button bt_loging;
   @ViewById
    EditText et_phone,et_pwd;


    DBHelper dbHelper = null;

    @AfterViews
    void init() {
        dbHelper = DBHelper.getInstance(LoginActivity.this);
        initDate();
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



        String tel = et_phone.getText().toString();
        String pwd = et_pwd.getText().toString();

        UserService userService = GetRetrofitService.getRestClient(UserService.class);

        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("mobile", tel);
        stringMap.put("pwd", pwd);

        stringMap = MapToParams.getParsMap(stringMap);

        Call<UserBean> call = userService.loging(stringMap);
        RestService  restService = new RestServiceImpl();
        restService.get(LoginActivity.this,"",call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                UserBean userBean =   ((UserBean)response.body());

                if(userBean.getErrorCode().equals("0")) {

                    MyApplication.userBean = userBean;
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


    public void initDate() {


        final ConfigService configService = GetRetrofitService.getRestClient(ConfigService.class);
        ParsMapBean parse = MapToParams.getMap(null);

        Call<ConfigBean> call = configService.getCofig(parse.getTime(),parse.getToken());

        RestService  restService = new RestServiceImpl();
        restService.get(null,"",call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                ConfigBean configBean =   ((ConfigBean)response.body());
                List<DataSource>  list =dbHelper.getDataInfoList();
                dbHelper.clearAllData();
                if(configBean.getErrorCode().equals("0")) {
                    List<DataSource> dataSources = new ArrayList<DataSource>();
                    for (int i = 0; i < configBean.getConfig().getCoutry_list().size(); i++) {
                        DataSource dataSource = new DataSource();
                        CoutryBean coutry = configBean.getConfig().getCoutry_list().get(i);
                        dataSource.setId_(coutry.getId());
                        dataSource.setName(coutry.getName());
                        dataSource.setEname(coutry.getEname());
                        dataSource.setCode(coutry.getCode());
                        dataSource.setModule(StaticBase.COUTRY);
                        dataSources.add(dataSource);
                    }

                    for (int i = 0; i < configBean.getConfig().getIncomebank_list().size(); i++) {
                        DataSource dataSource = new DataSource();
                        IncomebankBean incomebank = configBean.getConfig().getIncomebank_list().get(i);

                        dataSource.setId_(incomebank.getId());
                        dataSource.setName(incomebank.getName());
                        dataSource.setBankname(incomebank.getBankname());
                        dataSource.setBankno(incomebank.getBankno());
                        dataSource.setBankadd(incomebank.getBankadd());
                        dataSource.setModule(StaticBase.INCOMEBANK);
                        dataSources.add(dataSource);
                    }

                    for (int i = 0; i < configBean.getConfig().getBank_list().size(); i++) {
                        BankBean bankBean = configBean.getConfig().getBank_list().get(i);
                        DataSource dataSource = new DataSource();
                        dataSource.setId_(bankBean.getId());
                        dataSource.setName( bankBean.getName());
                        dataSource.setEname( bankBean.getEname());
                        dataSource.setModule(StaticBase.BANK);
                        dataSources.add(dataSource);
                    }

                    for (int i = 0; i < configBean.getConfig().getVirtualcointype().size(); i++) {
                        DataSource dataSource = new DataSource();
                        VirtualcoinBean virtualcoin = configBean.getConfig().getVirtualcointype().get(i);

                        dataSource.setId_(virtualcoin.getId());
                        dataSource.setSort_id(virtualcoin.getSort_id());
                        dataSource.setName(virtualcoin.getName());
                        dataSource.setEname(virtualcoin.getEname());
                        dataSource.setShortname(virtualcoin.getShortname());
                        dataSource.setWord(virtualcoin.getWord());
                        dataSource.setLogo(virtualcoin.getLogo());
                        dataSource.setMiniconfirm(virtualcoin.getMiniconfirm());
                        dataSource.setWithdraw_fee(virtualcoin.getWithdraw_fee());
                        dataSource.setMinwithdrawbtc(virtualcoin.getMinwithdrawbtc());
                        dataSource.setMaxwithdrawbtc(virtualcoin.getMaxwithdrawbtc());
                        dataSource.setRecharge_fee(virtualcoin.getRecharge_fee());
                        dataSource.setDraw_fee(virtualcoin.getDraw_fee());
                        dataSource.setModule(StaticBase.VIRTUALOIN);
                        dataSources.add(dataSource);
                    }
                    for (int i = 0; i < configBean.getConfig().getCard_type_list().size(); i++) {
                        DataSource dataSource = new DataSource();
                        CardTypeBean cardType = configBean.getConfig().getCard_type_list().get(i);
                        dataSource.setId_(cardType.getId());
                        dataSource.setName(cardType.getName());
                        dataSource.setEname(cardType.getEname());
                        dataSource.setModule(StaticBase.BANKTYPE);
                        dataSources.add(dataSource);

                    }
                    dbHelper.addDataTable(dataSources);


                }else {
                    Toast.makeText(getApplicationContext(), configBean.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {
                String a = "";
            }
        });
    }


}
