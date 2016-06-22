package com.global.globalonline.activities.RMB;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.activities.HistoricalRecord.RMBChongZhiFlowActivity_;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.base.MyApplication;
import com.global.globalonline.base.StaticBase;
import com.global.globalonline.bean.BaseBean;
import com.global.globalonline.dao.DBHelper;
import com.global.globalonline.db.bean.DataSource;
import com.global.globalonline.service.BaseService;
import com.global.globalonline.service.CallBackService;
import com.global.globalonline.service.GetRetrofitService;
import com.global.globalonline.service.RestService;
import com.global.globalonline.service.serviceImpl.RestServiceImpl;
import com.global.globalonline.tools.GetCheckoutET;
import com.global.globalonline.tools.GetSelectBouncedUtil;
import com.global.globalonline.tools.GetToastUtil;
import com.global.globalonline.tools.MapToParams;
import com.global.globalonline.view.SetListViewHeightBasedOnChildren;

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

@EActivity(R.layout.activity_recharge_rmb)
public class RechargeRMBActivity extends BaseActivity {
        @ViewById
    TextView operation,tv_wangyi,tv_zhifubao,tv_banktype;
    @ViewById
    EditText et_huichuzhanghao,et_name,et_price,et_miaoshu,et_phone;
    @ViewById
    Button btn_tijiao;
    @ViewById
    LinearLayout ll_cartype,ll_text3;
    @ViewById
    TextView tv_zhuyi;

    @ViewById
    ListView lv_fanben;


    String recharge_type = "1";  //1网银2支付宝

    @AfterViews
    void init(){

        GetSelectBouncedUtil.get(this,tv_banktype, StaticBase.BANK,"1");
        initList(true);
    }



    @Click({R.id.operation,R.id.btn_tijiao,R.id.tv_wangyi,R.id.tv_zhifubao})
    void click(View view){
        switch (view.getId())
        {
            case R.id.operation:
                RMBChongZhiFlowActivity_.intent(RechargeRMBActivity.this).start();
                break;
            case R.id.btn_tijiao:
               tijiao();
                break;
            case R.id.tv_wangyi:
                recharge_type = "1";
                ll_cartype.setVisibility(View.VISIBLE);
                tv_wangyi.setBackgroundResource(R.color.ac_base_tab);
                tv_zhifubao.setBackgroundResource(R.color.ac_virtual_chunk);
                tv_zhuyi.setText(getResources().getString(R.string.act_RechargeRMBActivity_test));
                ll_text3.setVisibility(View.VISIBLE);
                initList(true);
                break;
            case R.id.tv_zhifubao:
                recharge_type = "2";
                ll_cartype.setVisibility(View.GONE);
                tv_wangyi.setBackgroundResource(R.color.ac_virtual_chunk);
                tv_zhifubao.setBackgroundResource(R.color.ac_base_tab);
                tv_zhuyi.setText(getResources().getString(R.string.act_RechargeRMBActivity_alipay_test));
                initList(false);
                ll_text3.setVisibility(View.GONE);
                break;
        }
    }


    void tijiao(){
        boolean b =  GetCheckoutET.checkout(getApplicationContext(),et_huichuzhanghao,et_name,et_price,et_miaoshu);

        if(!b){
            return;
        }
        String bankid = "";
       String account = et_huichuzhanghao.getText().toString();
       String name = et_name.getText().toString();
       String money = et_price.getText().toString();
       String mobile = et_phone.getText().toString();
       String message = et_miaoshu.getText().toString();
        if (recharge_type.equals("1")){
            bankid = tv_banktype.getTag().toString();
        }else {
            bankid = "";
        }

        BaseService baseService = GetRetrofitService.getRestClient(BaseService.class);

        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("recharge_type", recharge_type);
        stringMap.put("account", account);
        stringMap.put("money", money);
        stringMap.put("bankid", bankid);
        stringMap.put("name", name);
        stringMap.put("mobile", mobile);
        stringMap.put("message", message);



        stringMap = MapToParams.getParsMap(stringMap);

        Call<BaseBean> call = baseService.rmb_paycheck_apply(stringMap);
        RestService restService = new RestServiceImpl();
        restService.get(RechargeRMBActivity.this,"loading...",call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                BaseBean userBean =   ((BaseBean)response.body());

                if(userBean.getErrorCode().equals("0")) {
                    //GetToastUtil.getToads(getApplication(),"充值成功");

                    GetToastUtil.getSuccessToads(RechargeRMBActivity.this);

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


    void initList(boolean b){

        List<Map<String,String>> list = new ArrayList<>();
        DBHelper dbHelper = DBHelper.getInstance(RechargeRMBActivity.this);


        if(b) {
            List<DataSource> dataSources = dbHelper.getByModekeyList(StaticBase.INCOMEBANK);
            for (int i = 0; i < dataSources.size(); i++) {
                DataSource d = dataSources.get(i);
                Map<String, String> map = new HashMap<>();
                map.put("shoukuanren", d.getName());
                map.put("shoukuanzhanghao", d.getBankno());
                map.put("shoukuankaihu", d.getBankadd());
                map.put("jine", getResources().getString(R.string.act_base_chongzhi_text));
                map.put("beizhu", MyApplication.userBean.getUserid());
                list.add(map);
            }
            Sa sa  =  new Sa(RechargeRMBActivity.this,list,R.layout.act_item_recharge_rmb,
                    new String[]{"shoukuanren","shoukuanzhanghao","shoukuankaihu","jine","beizhu"},
                    new int[]{R.id.tv_shoukuanren,R.id.tv_shoukuanzhanghao,R.id.tv_shoukuankaihu,R.id.tv_jine,R.id.tv_beizhu}
            );

            lv_fanben.setAdapter(sa);

        }else {
            List<DataSource> dataSources = dbHelper.getByModekeyList(StaticBase.ALIPAY);
            for (int i = 0; i < dataSources.size(); i++) {
                DataSource d = dataSources.get(i);
                Map<String, String> map = new HashMap<>();
                map.put("name", getResources().getString(R.string.act_RechargeRMBActivity_tab2));
                map.put("zhanghao", d.getBankno());
                map.put("shoukuanname", d.getName());
                map.put("jine", "您要转入的金额");
                map.put("nenrong", MyApplication.userBean.getUserid());
                list.add(map);
            }
            Sa sa  =  new Sa(RechargeRMBActivity.this,list,R.layout.act_item_recharge_zhifubao_rmb,
                    new String[]{"name","zhanghao","shoukuanname","nenrong","jine"},
                    new int[]{R.id.tv_zchongzhifangshi,R.id.tv_zhanghao,R.id.tv_shoukuanname,R.id.tv_nenrong,R.id.tv_price}
            );

            lv_fanben.setAdapter(sa);
        }



        SetListViewHeightBasedOnChildren.setList(lv_fanben);
    }

}

class Sa extends SimpleAdapter {

    public Sa(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }


}
