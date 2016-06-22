package com.global.globalonline.activities.mainTab;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.global.globalonline.R;
import com.global.globalonline.base.GetConfiguration;
import com.global.globalonline.base.StaticBase;
import com.global.globalonline.bean.AlipayBean;
import com.global.globalonline.bean.BankBean;
import com.global.globalonline.bean.CardTypeBean;
import com.global.globalonline.bean.ConfigBean;
import com.global.globalonline.bean.CoutryBean;
import com.global.globalonline.bean.IncomebankBean;
import com.global.globalonline.bean.VirtualcoinBean;
import com.global.globalonline.control.ActivityCollector;
import com.global.globalonline.dao.DBHelper;
import com.global.globalonline.db.bean.DataSource;
import com.global.globalonline.service.CallBackService;
import com.global.globalonline.service.ConfigService;
import com.global.globalonline.service.GetRetrofitService;
import com.global.globalonline.service.RestService;
import com.global.globalonline.service.serviceImpl.RestServiceImpl;
import com.global.globalonline.tools.GetQuanXian;
import com.global.globalonline.tools.GetToastUtil;
import com.global.globalonline.tools.MapToParams;
import com.global.globalonline.tools.SharedPreferencesUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;


@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity {

    @ViewById
    ImageButton ibtn_homepage,ibtn_tradingfloor,ibtn_my;
    @ViewById
    TextView title,tv_english,operation,tv_home,tv_dating,tv_my;
    @ViewById
    LinearLayout xiala;




    public static  String TAG = "MainActivity";
    private View currentButton;

    HomePageFrament homePageFrament;
    TradingFloorFrament tradingFloorFrament;
    MyFrament myFrament;

    DBHelper dbHelper = null;
    String is_data = "1";  //默认0，返回所有数据，1，只返回md5加密串

    private long exitTime = 0;


    @AfterViews
    void init(){
        dbHelper = DBHelper.getInstance(MainActivity.this);
        initComponents();
        initDate();
        Locale.setDefault(Locale.CHINESE);
        if(GetConfiguration.LANGUAGE.equals(GetConfiguration.ZH)){
            operation.setText("中文");
        }else {
            operation.setText("En");
        }
    }


    @Click({R.id.operation,R.id.tv_english,R.id.tv_cn})
    void click(View view){
        switch (view.getId()){
            case R.id.operation :

                if(xiala.getVisibility() == View.VISIBLE){
                    xiala.setVisibility(View.GONE);
                }else {
                    xiala.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.tv_english:
                checkLa(Locale.ENGLISH);
                GetConfiguration.LANGUAGE = GetConfiguration.EN;
                break;
            case R.id.tv_cn:
                checkLa(Locale.CHINESE);
                GetConfiguration.LANGUAGE = GetConfiguration.ZH;
                break;
        }

    }


    private void initComponents() {

        ibtn_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText(getResources().getString(R.string.act_main_title_home));

                tv_home.setTextColor(getResources().getColor(R.color.btn_queding));
                tv_dating.setTextColor(getResources().getColor(R.color.ac_base_ziti_hui));
                tv_my.setTextColor(getResources().getColor(R.color.ac_base_ziti_hui));


                FragmentManager fm = MainActivity.this.getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                hideFragments(ft);

                if(homePageFrament != null){
                    ft.show(homePageFrament);
                }else {
                    homePageFrament = new HomePageFrament_();
                    ft.add(R.id.fl_mian, homePageFrament, MainActivity.TAG);


                }
                ft.commit();
                setButton(v);

            }
        });
        ibtn_tradingfloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText(getResources().getString(R.string.act_main_title_dating));
                tv_home.setTextColor(getResources().getColor(R.color.ac_base_ziti_hui));
                tv_dating.setTextColor(getResources().getColor(R.color.btn_queding));
                tv_my.setTextColor(getResources().getColor(R.color.ac_base_ziti_hui));

                FragmentManager fm = MainActivity.this.getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                hideFragments(ft);
                if(tradingFloorFrament != null){
                    ft.show(tradingFloorFrament);
                }else {
                    tradingFloorFrament = new TradingFloorFrament_();
                    ft.add(R.id.fl_mian, tradingFloorFrament, MainActivity.TAG);
                }
                ft.commit();
                setButton(v);
            }
        });

        ibtn_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GetQuanXian.getIsQuanXian(MainActivity.this)) {
                    title.setText(getResources().getString(R.string.act_main_title_my));
                    tv_home.setTextColor(getResources().getColor(R.color.ac_base_ziti_hui));
                    tv_dating.setTextColor(getResources().getColor(R.color.ac_base_ziti_hui));
                    tv_my.setTextColor(getResources().getColor(R.color.btn_queding));
                    FragmentManager fm = MainActivity.this.getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    hideFragments(ft);
                    if (myFrament != null) {
                        ft.show(myFrament);
                    } else {
                        myFrament = new MyFrament_();
                        ft.add(R.id.fl_mian, myFrament, MainActivity.TAG);
                    }
                    ft.commit();
                    setButton(v);
                }

            }
        });


        ibtn_homepage.performClick();

    }

    /**
     * 设置按钮的背景图片
     *
     * @param v
     */
    private void setButton(View v) {
        if (currentButton != null && currentButton.getId() != v.getId()) {
            currentButton.setEnabled(true);
        }
        v.setEnabled(false);
        currentButton = v;
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (homePageFrament != null) {
            transaction.hide(homePageFrament);
        }
        if (tradingFloorFrament != null) {
            transaction.hide(tradingFloorFrament);
        }
        if (myFrament != null) {
            transaction.hide(myFrament);
        }

    }


    public  void checkLa(Locale locale ){
        Configuration configCN = getBaseContext().getResources().getConfiguration();
        configCN.locale = locale;
        getBaseContext().getResources().updateConfiguration(configCN
                , getBaseContext().getResources().getDisplayMetrics());
        xiala.setVisibility(View.GONE);
        Intent intent = new Intent();
        intent.setClass(this,MainActivity_.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    public void initDate() {

        ConfigService configService = GetRetrofitService.getRestClient(ConfigService.class);
        Map<String, String> stringMap = new HashMap<String, String>();

        stringMap.put("is_data",is_data);
        stringMap = MapToParams.getParsMap(stringMap);

        Call<ConfigBean> call = configService.getCofig(stringMap);

        RestService restService = new RestServiceImpl();
        restService.get(null,"",call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                ConfigBean configBean =   ((ConfigBean)response.body());
                List<DataSource> list =dbHelper.getDataInfoList();
                if(configBean.getErrorCode().equals("0")) {

                    if(is_data.equals("0")){
                        dbHelper.clearAllData();
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
                            dataSource.setName(bankBean.getName());
                            dataSource.setEname(bankBean.getEname());
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
                            dataSource.setModule(StaticBase.CARTYPE);
                            dataSources.add(dataSource);

                        }
                        //  for (int i = 0; i < configBean.getConfig().getAlipay().size(); i++) {
                        DataSource dataSource = new DataSource();
                        AlipayBean alipayBean = configBean.getConfig().getAlipay();

                        dataSource.setName(alipayBean.getName());
                        dataSource.setBankno(alipayBean.getAccount());
                        dataSource.setModule(StaticBase.ALIPAY);
                        dataSources.add(dataSource);

                        // }
                        dbHelper.addDataTable(dataSources);

                    }else {

                        String md5Str = SharedPreferencesUtil.getShared(MainActivity.this, SharedPreferencesUtil.MD5, SharedPreferencesUtil.MD5_STR);
                        if (configBean.getData_md5().equals(md5Str)) {
                            call.cancel();
                        } else {
                            is_data = "0";
                            initDate();
                            SharedPreferencesUtil.setShared(MainActivity.this, SharedPreferencesUtil.MD5, SharedPreferencesUtil.MD5_STR, configBean.getData_md5());
                        }
                    }



                }else {

                    GetToastUtil.getToads(MainActivity.this, configBean.getMessage());

                }
            }
            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {
                GetToastUtil.getToads(MainActivity.this,t.getMessage());
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            //clearMessage();
            ActivityCollector.finishAll();
            System.exit(0);

        }
    }

}
