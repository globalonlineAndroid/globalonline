package com.global.globalonline.activities.virtualCurrency;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.activities.HistoricalRecord.ZhuanRuVirtualFlowActivity;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.base.StaticBase;
import com.global.globalonline.bean.CoinsPaycheckBean;
import com.global.globalonline.service.CallBackService;
import com.global.globalonline.service.GetRetrofitService;
import com.global.globalonline.service.RestService;
import com.global.globalonline.service.serviceImpl.RestServiceImpl;
import com.global.globalonline.service.virtualTrading.VirtualService;
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

@EActivity(R.layout.activity_charge_fees)
public class ChargeFeesActivity extends BaseActivity {

    String symbol = "";
    @ViewById
    TextView title,tv_dizhi,tv_keyong,tv_dongjie,tv_fuzhi;

    public static void  toActivity(Activity activity, String symbol){

        Intent intent = new Intent(activity, ChargeFeesActivity_.class);
        intent.putExtra("symbol",symbol);
        activity.startActivity(intent);

    }

    @AfterViews
    void init(){
        symbol  = getIntent().getStringExtra("symbol");
        String name =  GetSelectBouncedUtil.getBankName(ChargeFeesActivity.this, StaticBase.VIRTUALOIN,symbol);
        title.setText(name+getResources().getString(R.string.act_virtualcurrency_chargeFees_chongzhi));
        initView();
    }

    @Click({R.id.operation,R.id.tv_fuzhi})
    void click(View view) {
        switch (view.getId()) {
            case R.id.operation:
                ZhuanRuVirtualFlowActivity.toActivity(ChargeFeesActivity.this,symbol);
                break;
            case R.id.tv_fuzhi:

                String str = tv_dizhi.getText() != null ? tv_dizhi.getText().toString():"";
                ClipboardManager clip = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("dizhi",str);
                clip.setPrimaryClip(clipData);
                GetToastUtil.getToads(ChargeFeesActivity.this,getResources().getString(R.string.act_base_fuzhichenggong));
                break;


        }
    }


    void initView(){

        VirtualService virtualService = GetRetrofitService.getRestClient(VirtualService.class);

        Map<String,String>  map = new HashMap<String,String>();
        map.put("symbol",symbol);

        map = MapToParams.getParsMap(map);

        Call<CoinsPaycheckBean> call = virtualService.coins_paycheck(map);
        RestService restService = new RestServiceImpl();

        restService.get(null, "", call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                CoinsPaycheckBean virtualListItemBean =(CoinsPaycheckBean) response.body();
                if(virtualListItemBean.getErrorCode().equals("0")){

                    tv_dizhi.setText(virtualListItemBean.getWallet());
                    tv_keyong.setText(virtualListItemBean.getAmount());
                    tv_dongjie.setText(virtualListItemBean.getFrozen());

                }else {
                    GetToastUtil.getToads(ChargeFeesActivity.this,virtualListItemBean.getMessage());
                }
            }

            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {
                GetToastUtil.getToads(ChargeFeesActivity.this,t.getMessage());
            }
        });
    }


}
