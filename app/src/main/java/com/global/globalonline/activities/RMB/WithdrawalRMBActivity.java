package com.global.globalonline.activities.RMB;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.activities.HistoricalRecord.TiXianFlowActivity_;
import com.global.globalonline.base.BaseActivity;
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
import com.global.globalonline.view.CarNumberTextWatcher;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

@EActivity(R.layout.activity_withdrawal_rmb)
public class WithdrawalRMBActivity extends BaseActivity {
    @ViewById
    TextView tv_banktype,operation,tv_rmntixian;
    @ViewById
    EditText et_cardNum,et_reCardNum,et_toName,et_kaihuhang,et_price,et_trade_pwd;
    @ViewById
    Button btn_tijiao;


    @AfterViews
    void init(){

        GetSelectBouncedUtil.get(this,tv_banktype, StaticBase.BANK,"1");
        et_cardNum.addTextChangedListener(new CarNumberTextWatcher(et_cardNum));
        et_reCardNum.addTextChangedListener(new CarNumberTextWatcher(et_reCardNum));


        DBHelper dbHelper  = DBHelper.getInstance(WithdrawalRMBActivity.this);
        DataSource dataSource = dbHelper.getByModekeyList(StaticBase.RMB).get(0);

        String  sAgeFormat1 = getResources().getString(R.string.act_WithdrawalRMBActivity_text);
        String text = String.format(sAgeFormat1,"¥"+dataSource.getMaxwithdrawbtc(),"¥"+dataSource.getMinwithdrawbtc(),dataSource.getWithdraw_fee()+"%");
        tv_rmntixian.setText(text);
    }


    @Click({R.id.operation,R.id.btn_tijiao})
    void click(View view){
        switch (view.getId())
        {
            case R.id.operation:
                TiXianFlowActivity_.intent(WithdrawalRMBActivity.this).start();
                break;
            case R.id.btn_tijiao:
                tijiao();
                break;
        }
    }

    public void tijiao() {

        boolean b =  GetCheckoutET.checkout(getApplicationContext(),et_cardNum,et_reCardNum,et_toName,
                et_kaihuhang,et_price,et_trade_pwd);

        if(!b){
            return;
        }

        String brank = tv_banktype.getTag().toString();
        String brank_card1 = et_cardNum.getText().toString().replace(" ","");
        String brank_card2 = et_reCardNum.getText().toString().replace(" ","");
        String name = et_toName.getText().toString();
        String brank_name = et_kaihuhang.getText().toString();
        String amount = et_price.getText().toString();
        String trade_pwd = et_trade_pwd.getText().toString();

        BaseService baseService = GetRetrofitService.getRestClient(BaseService.class);

        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("bank", brank);
        stringMap.put("bank_card1", brank_card1);
        stringMap.put("bank_card2", brank_card2);
        stringMap.put("name", name);
        stringMap.put("bank_name", brank_name);
        stringMap.put("amount", amount);
        stringMap.put("trade_pwd", trade_pwd);

        stringMap = MapToParams.getParsMap(stringMap);

        Call<BaseBean> call = baseService.rmb_extract_apply(stringMap);
        RestService restService = new RestServiceImpl();
        restService.get(WithdrawalRMBActivity.this,"",call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                BaseBean baseBean =   ((BaseBean)response.body());

                if(baseBean.getErrorCode().equals("0")) {
                   // GetToastUtil.getToads(getApplication(), "提交成功");
                  //  finish();
                    GetToastUtil.getSuccessToads(WithdrawalRMBActivity.this);
                }else {
                    GetToastUtil.getToads(getApplication(), baseBean.getMessage());

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



}
