package com.global.globalonline.activities.virtualCurrency;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.activities.HistoricalRecord.ZhuanChuVirtualFlowActivity;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.base.StaticBase;
import com.global.globalonline.bean.BaseBean;
import com.global.globalonline.bean.CoinsDetailBean;
import com.global.globalonline.dao.DBHelper;
import com.global.globalonline.db.bean.DataSource;
import com.global.globalonline.service.CallBackService;
import com.global.globalonline.service.GetRetrofitService;
import com.global.globalonline.service.RestService;
import com.global.globalonline.service.serviceImpl.RestServiceImpl;
import com.global.globalonline.service.virtualTrading.VirtualService;
import com.global.globalonline.tools.GetCheckoutET;
import com.global.globalonline.tools.GetSelectBouncedUtil;
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

@EActivity(R.layout.act_archived)
public class ArchivedActivity extends BaseActivity {


    @ViewById
    TextView operation,title,tv_keyong,tv_dongjie,tv_shuoming;

    @ViewById
    EditText    et_dizhi, et_number,  et_trade_pwd;
    @ViewById
    Button btn_tijiao;


    String symbol = "";
    VirtualService virtualService ;

    public static void  toActivity(Activity activity, String symbol){

        Intent intent = new Intent(activity, ArchivedActivity_.class);
        intent.putExtra("symbol",symbol);
        activity.startActivity(intent);
    }

    @AfterViews
    void init(){
        symbol  = getIntent().getStringExtra("symbol");
        virtualService = GetRetrofitService.getRestClient(VirtualService.class);
        String name =  GetSelectBouncedUtil.getBankName(ArchivedActivity.this, StaticBase.VIRTUALOIN,symbol);
        title.setText(getResources().getString(R.string.act_virtualcurrency_archived_zhuanchu)+name);

        DBHelper dbHelper  = DBHelper.getInstance(ArchivedActivity.this);
        DataSource dataSource = dbHelper.getByModeOrId(StaticBase.VIRTUALOIN,symbol).get(0);

        tv_shuoming.setText(getResources().getString(R.string.act_virtualcurrency_archived_text)+" "+
                dataSource.getMinwithdrawbtc()+getResources().getString(R.string.act_virtualcurrency_archived_text_1)+" "+
                dataSource.getMaxwithdrawbtc()+getResources().getString(R.string.act_virtualcurrency_archived_text_2)+dataSource.getDraw_fee()+"%"+
                getResources().getString(R.string.act_virtualcurrency_archived_text_3));
        initView();

    }
    @Click({R.id.operation,R.id.btn_tijiao})
    void click(View view){
        switch (view.getId())
        {
            case R.id.operation:
                ZhuanChuVirtualFlowActivity.toActivity(ArchivedActivity.this,symbol);
                break;
            case R.id.btn_tijiao:
                tijiao();
                break;
        }
    }


    /* tv_keyong.setText(virtualListItemBean.getAmount());
                    tv_dongjie.setText(virtualListItemBean.getFrozen());*/

    void initView(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("symbol",symbol);

        map = MapToParams.getParsMap(map);

        //Call<CoinsPaycheckBean> call = virtualService.coins_paycheck(map);
        Call<CoinsDetailBean> call = virtualService.coins_detail(map);
        RestService restService = new RestServiceImpl();

        restService.get(ArchivedActivity.this, "loading...", call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                CoinsDetailBean coinsDetailBean =(CoinsDetailBean) response.body();
                if(coinsDetailBean.getErrorCode().equals("0")) {

                    try {

                    tv_keyong.setText(coinsDetailBean.getCoins_balance());
                    tv_dongjie.setText(coinsDetailBean.getFrozen());

                    }catch (Exception e){

                    }
                }else{
                    GetToastUtil.getToads(ArchivedActivity.this, coinsDetailBean.getMessage());
                }

            }

            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {
                GetToastUtil.getToads(ArchivedActivity.this,t.getMessage());
            }
        });
    }

    void tijiao(){

        Boolean b  = GetCheckoutET.checkout(getApplicationContext(),et_dizhi,et_number,et_trade_pwd);
        if(!b){
            return;
        }
        String wallet = et_dizhi.getText().toString();
        String quantity = et_number.getText().toString();
        String trade_pwd = et_trade_pwd.getText().toString();

        Map<String,String> map = new HashMap<String,String>();
        map.put("symbol",symbol);
        map.put("wallet",wallet);
        map.put("quantity",quantity);
        map.put("trade_pwd",trade_pwd);


        map = MapToParams.getParsMap(map);

        Call<BaseBean> call = virtualService.coins_extract(map);
        RestService restService = new RestServiceImpl();

        restService.get(null, "", call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                BaseBean baseBean =(BaseBean) response.body();
                if(baseBean.getErrorCode().equals("0")){
                    GetToastUtil.getToads(ArchivedActivity.this,"提交成功");
                    finish();
                   // GetToastUtil.getSuccessToads(ArchivedActivity.this);
                }else {
                    GetToastUtil.getToads(ArchivedActivity.this,baseBean.getMessage());
                }
            }

            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {
                GetToastUtil.getToads(ArchivedActivity.this,t.getMessage());
            }
        });
    }

}
