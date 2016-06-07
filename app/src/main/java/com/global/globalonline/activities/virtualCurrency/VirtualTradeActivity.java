package com.global.globalonline.activities.virtualCurrency;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.global.globalonline.R;
import com.global.globalonline.activities.BaseActivie.KLineActivity;
import com.global.globalonline.activities.user.LoginActivity_;
import com.global.globalonline.adapter.VirtualTrade.VirtualTradeAdapter;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.base.MyApplication;
import com.global.globalonline.base.UrlApi;
import com.global.globalonline.bean.BaseBean;
import com.global.globalonline.bean.CoinsDetailBean;
import com.global.globalonline.bean.CoinsDetailItemBean;
import com.global.globalonline.service.CallBackService;
import com.global.globalonline.service.GetRetrofitService;
import com.global.globalonline.service.RestService;
import com.global.globalonline.service.serviceImpl.RestServiceImpl;
import com.global.globalonline.service.virtualTrading.VirtualService;
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

@EActivity(R.layout.activity_virtual_trade)
public class VirtualTradeActivity extends BaseActivity {

    @ViewById
    ListView lv_mai,lv_sell;
    @ViewById
    TextView back,tv_name,tv_price,tv_zhangfu,tv_chengjiaoe,tv_chengjiaoliang,tv_minprice,tv_maxprice,tv_buy,
            tv_sell,
            tv_buy_keyongRMB,tv_buy_kemaixunibi,tv_buy_login,
            tv_sell_login,tv_sell_keyongXuNiBi,tv_sell_dongjiexunibi;
    @ViewById
    EditText et_buy_price,et_buy_number,et_buy_jiaoyipwd,
                et_sell_price,et_sell_number,et_sell_jiaoyipwd;

    @ViewById
    TextView tv_mairu_tab,tv_maichu_tab,tv_chedan;
    @ViewById
    ImageView operation;
    @ViewById
    Button btn_buy,btn_sell;
    @ViewById
    LinearLayout ll_buy,ll_sell;

    @ViewById
    ImageView iv_image;

    CoinsDetailBean coinsDetailBean ;
    VirtualTradeAdapter virtualTradeAdapter;

    List<CoinsDetailItemBean> buy_list  = new ArrayList<>(); ;
    List<CoinsDetailItemBean>  sell_list = new ArrayList<>();

    VirtualService virtualService;
    String symbol = "";

    public static  void toActiviy(Activity act,String symbol){
        Intent intent = new Intent(act,VirtualTradeActivity_.class);
        intent.putExtra("symbol",symbol);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        act.startActivity(intent);

    }

    @AfterViews
    void init(){
        if(MyApplication.userBean != null){
            tv_sell_login.setVisibility(View.GONE);
            tv_buy_login.setVisibility(View.GONE);
        }
        virtualService = GetRetrofitService.getRestClient(VirtualService.class);
        symbol = getIntent().getStringExtra("symbol");
        initView();
    }

    @Click({R.id.back,R.id.tv_mairu_tab,R.id.tv_maichu_tab,R.id.tv_chedan,R.id.btn_buy, R.id.btn_sell,
            R.id.tv_sell_login,R.id.tv_buy_login,R.id.operation})
    void click(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.tv_mairu_tab:
                ll_buy.setVisibility(View.VISIBLE);
                ll_sell.setVisibility(View.GONE);
                tv_mairu_tab.setBackgroundResource(R.color.ac_base_tab);
                tv_maichu_tab.setBackgroundResource(R.color.ac_virtual_chunk);
                tv_chedan.setBackgroundResource(R.color.ac_virtual_chunk);
                break;
            case R.id.tv_maichu_tab:
                ll_buy.setVisibility(View.GONE);
                ll_sell.setVisibility(View.VISIBLE);
                tv_mairu_tab.setBackgroundResource(R.color.ac_virtual_chunk);
                tv_maichu_tab.setBackgroundResource(R.color.ac_base_tab);
                tv_chedan.setBackgroundResource(R.color.ac_virtual_chunk);
                break;
            case R.id.tv_chedan:
                tv_mairu_tab.setBackgroundResource(R.color.ac_virtual_chunk);
                tv_maichu_tab.setBackgroundResource(R.color.ac_virtual_chunk);
                tv_chedan.setBackgroundResource(R.color.ac_base_tab);
                break;
            case R.id.btn_buy:
                buy();
                break;
            case R.id.btn_sell:
                sell();
                break;
            case R.id.tv_sell_login:
            case R.id.tv_buy_login:
                LoginActivity_.intent(VirtualTradeActivity.this).start();
                break;
            case R.id.operation:

               // KLineTestActivity.toActivity(VirtualTradeActivity.this,symbol);
                KLineActivity.toActivity(VirtualTradeActivity.this,symbol);
                break;
        }
    }


    public void initView(){
        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("symbol",symbol);

        stringMap = MapToParams.getParsMap(stringMap);

        Call<CoinsDetailBean> call = virtualService.coins_detail(stringMap);
        RestService restService = new RestServiceImpl();

        restService.get(VirtualTradeActivity.this, "loading...", call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                CoinsDetailBean coinsDetailBean =(CoinsDetailBean) response.body();
                if(coinsDetailBean.getErrorCode().equals("0")){


                    Glide.with(VirtualTradeActivity.this)
                            .load(UrlApi.baseImageUrl+coinsDetailBean.getImg())
                            .into(iv_image);

                    String  buyPrice  = coinsDetailBean.getBuy_list().get(0).getPrice();
                    String  sellPrice  = coinsDetailBean.getSell_list().get(0).getPrice();

                    tv_name.setText(coinsDetailBean.getName());
                    tv_price.setText(coinsDetailBean.getPrice());
                    tv_zhangfu.setText(coinsDetailBean.getRatio());
                    //tv_chengjiaoe.setText(coinsDetailBean.getVolume());
                    tv_chengjiaoliang.setText(coinsDetailBean.getVolume());
                    tv_minprice.setText(coinsDetailBean.getMin_price());
                    tv_maxprice.setText(coinsDetailBean.getMax_price());
                    tv_buy.setText(buyPrice);
                    tv_sell.setText(sellPrice);
                    tv_sell_keyongXuNiBi.setText(coinsDetailBean.getCoins_balance());
                    tv_buy_keyongRMB.setText(coinsDetailBean.getAccount_balance());
                    tv_sell_dongjiexunibi.setText(coinsDetailBean.getFrozen());

                    et_buy_price.setText(sellPrice);
                    et_sell_price.setText(buyPrice);


                    List<CoinsDetailItemBean> list = new ArrayList<CoinsDetailItemBean>();

                    for (int i = 0; i < coinsDetailBean.getBuy_list().size(); i++) {
                        coinsDetailBean.getBuy_list().get(i).setName("买"+(i+1));

                    }

                    for (int i = coinsDetailBean.getSell_list().size(); i > 0; i --) {
                        CoinsDetailItemBean cb = coinsDetailBean.getSell_list().get(i-1);
                        cb.setName("卖"+i);
                        list.add(cb);

                    }

                    if(coinsDetailBean.getBuy_list().size() > 0){
                       coinsDetailBean.getBuy_list();
                    }
                    if(coinsDetailBean.getSell_list().size()>0){
                       coinsDetailBean.getSell_list();
                    }

                    virtualTradeAdapter = new VirtualTradeAdapter(R.color.limegreen,VirtualTradeActivity.this,list);
                    lv_mai.setAdapter(virtualTradeAdapter);
                    virtualTradeAdapter = new VirtualTradeAdapter(R.color.red,VirtualTradeActivity.this,coinsDetailBean.getBuy_list());
                    lv_sell.setAdapter(virtualTradeAdapter);

                }else {
                    GetToastUtil.getToads(VirtualTradeActivity.this,coinsDetailBean.getMessage());
                }
            }

            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {
                GetToastUtil.getToads(VirtualTradeActivity.this,t.getMessage());
            }
        });
    }

    public void buy(){
       String buyprice  = et_buy_price.getText().toString();
       String buynumber  = et_buy_number.getText().toString();
       String jiaoyipwd  = et_buy_jiaoyipwd.getText().toString();

        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("symbol",symbol);
        stringMap.put("price",buyprice);
        stringMap.put("count",buynumber);
        stringMap.put("trade_pwd",jiaoyipwd);

        stringMap = MapToParams.getParsMap(stringMap);

        Call<BaseBean> call = virtualService.purchase(stringMap);
        RestService restService = new RestServiceImpl();

        restService.get(VirtualTradeActivity.this, "loading...", call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                BaseBean base =(BaseBean) response.body();
                if(base.getErrorCode().equals("0")){
                    GetToastUtil.getToads(VirtualTradeActivity.this,"买入成功");
                    VirtualTradeActivity.toActiviy(VirtualTradeActivity.this,symbol);

                }else {
                    GetToastUtil.getToads(VirtualTradeActivity.this,base.getMessage());
                }
            }

            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {
                GetToastUtil.getToads(VirtualTradeActivity.this,t.getMessage());
            }
        });
    }

    public void sell(){
        String sellprice  = et_sell_price.getText().toString();
        String sellnumber  = et_sell_number.getText().toString();
        String jiaoyipwd  = et_sell_jiaoyipwd.getText().toString();

        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("symbol",symbol);
        stringMap.put("price",sellprice);
        stringMap.put("count",sellnumber);
        stringMap.put("trade_pwd",jiaoyipwd);

        stringMap = MapToParams.getParsMap(stringMap);

        Call<BaseBean> call = virtualService.sellout(stringMap);
        RestService restService = new RestServiceImpl();

        restService.get(VirtualTradeActivity.this, "loading...", call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                BaseBean base =(BaseBean) response.body();
                if(base.getErrorCode().equals("0")){
                    GetToastUtil.getToads(VirtualTradeActivity.this,"卖出成功");
                    VirtualTradeActivity.toActiviy(VirtualTradeActivity.this,symbol);
                }else {
                    GetToastUtil.getToads(VirtualTradeActivity.this,base.getMessage());
                }
            }

            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {
                GetToastUtil.getToads(VirtualTradeActivity.this,t.getMessage());
            }
        });
    }
}
