package com.global.globalonline.activities.mainTab;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.activities.BaseActivie.CertificationResultsActivity_;
import com.global.globalonline.activities.BaseActivie.WebViewActivity;
import com.global.globalonline.activities.RMB.RechargeRMBActivity_;
import com.global.globalonline.activities.RMB.WithdrawalRMBActivity_;
import com.global.globalonline.activities.select.SelectVirtualActivity_;
import com.global.globalonline.activities.user.CertificationActivity_;
import com.global.globalonline.activities.user.ChangeLoginPasswordActivity_;
import com.global.globalonline.activities.user.ChangeTradePasswordActivity_;
import com.global.globalonline.activities.user.LoginActivity_;
import com.global.globalonline.base.MyApplication;
import com.global.globalonline.bean.AccountDetailBean;
import com.global.globalonline.dao.TishiResDao;
import com.global.globalonline.service.CallBackService;
import com.global.globalonline.service.GetRetrofitService;
import com.global.globalonline.service.RestService;
import com.global.globalonline.service.serviceImpl.RestServiceImpl;
import com.global.globalonline.service.user.UserService;
import com.global.globalonline.tools.GetDialogUtil;
import com.global.globalonline.tools.GetQuanXian;
import com.global.globalonline.tools.GetToastUtil;
import com.global.globalonline.tools.MapToParams;
import com.zbar.lib.CaptureActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

@EFragment(R.layout.activity_my)
public class MyFrament extends Fragment {

    @ViewById
    TextView tv_loginName,tv_ename,tv_isRenZheng,tv_uid,tv_zichan;

    @ViewById
    LinearLayout ll_RMBPay,ll_RMBTiXian,ll_XuNiPay,ll_XuNiTiXian,ll_weiTuoManager,ll_jiaoYiLiuShui,ll_shouKuan,
            ll_fuKuan,ll_renZheng,ll_updateLoginPWD,ll_updateJiaoYiPWD, ll_message,ll_woMen,ll_exit;



    @AfterViews
    void init (){

        try {

            if(GetQuanXian.getIsQuanXian(getActivity())) {

                tv_loginName.setText(MyApplication.userBean.getMobile());
                tv_ename.setText(MyApplication.userBean.getUsername());


                tv_isRenZheng.setText(getResources().getString(R.string.act_base_yirenzheng));

            }

        }catch (Exception e){
            LoginActivity_.intent(getActivity()).start();
        }
        //initView();

    }

    @Click({R.id.ll_RMBPay,R.id.ll_RMBTiXian,R.id.ll_XuNiPay,R.id.ll_XuNiTiXian,R.id.ll_weiTuoManager,R.id.ll_jiaoYiLiuShui,R.id.ll_shouKuan
            ,R.id.ll_fuKuan,R.id.ll_renZheng,R.id.ll_updateLoginPWD,R.id.ll_updateJiaoYiPWD,R.id.ll_message,R.id.ll_woMen,R.id.ll_exit})
    void click(View view){

        switch (view.getId()){
            case R.id.ll_RMBPay:
                RechargeRMBActivity_.intent(getActivity()).start();

                break;
            case R.id.ll_RMBTiXian:
                WithdrawalRMBActivity_.intent(getActivity()).start();
                break;
            case R.id.ll_XuNiPay:
                Intent xuni = new Intent(getActivity(), SelectVirtualActivity_.class);
                xuni.putExtra("type","xunizhuanru");
                startActivity(xuni);
                break;
            case R.id.ll_XuNiTiXian:
                Intent xunizhuanchu = new Intent(getActivity(), SelectVirtualActivity_.class);
                xunizhuanchu.putExtra("type","xunizhuanchu");
                startActivity(xunizhuanchu);
                break;
            case R.id.ll_weiTuoManager:
                Intent weituo = new Intent(getActivity(), SelectVirtualActivity_.class);
                weituo.putExtra("type","weituo");
                startActivity(weituo);
                break;
            case R.id.ll_jiaoYiLiuShui:
                //SelectVirtualActivity_.intent(getActivity()).start();
                Intent jiaoyiliushui = new Intent(getActivity(), SelectVirtualActivity_.class);
                jiaoyiliushui.putExtra("type","jiaoyiliushui");
                startActivity(jiaoyiliushui);
                break;
            case R.id.ll_shouKuan:
               /* Intent intent_shengcheng = new Intent(getActivity(), ShengChengActivity.class);
                startActivity(intent_shengcheng);*/
                Intent intent = new Intent(getActivity(), SelectVirtualActivity_.class);
                intent.putExtra("type","fukuan");
                startActivity(intent);
                break;
            case R.id.ll_fuKuan:
                Intent intentFuKuan = new Intent(getActivity(), CaptureActivity.class);
                startActivity(intentFuKuan);

                break;
            case R.id.ll_renZheng:
                if(MyApplication.userBean.getIdentity_info() != null){
                    CertificationResultsActivity_.intent(getActivity()).start();
                }else {
                    CertificationActivity_.intent(getActivity()).start();
                }
                break;
            case R.id.ll_updateLoginPWD:
                ChangeLoginPasswordActivity_.intent(getActivity()).start();
                break;
            case R.id.ll_updateJiaoYiPWD:
                ChangeTradePasswordActivity_.intent(getActivity()).start();
                break;
            case R.id.ll_message:
                break;
            case R.id.ll_woMen:
                //AboutUsActivity_.intent(getActivity()).start();
                WebViewActivity.toActivity(getActivity(),"guanyu");
                break;
            case R.id.ll_exit:

                GetDialogUtil.tishi(getActivity(), getResources().getString(R.string.act_base_tishi),
                        getResources().getString(R.string.act_base_istuichu), new TishiResDao() {
                    @Override
                    public void getTiShi(String args) {
                        MyApplication.userBean = null;
                        LoginActivity_.intent(getActivity()).start();
                    }
                });

                break;

        }

    }


    void initView(){

        UserService userService = GetRetrofitService.getRestClient(UserService.class);

        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap = MapToParams.getParsMap(stringMap);

        Call<AccountDetailBean> call = userService.account_detail(stringMap);
        RestService restService = new RestServiceImpl();
        restService.get(null,"",call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                AccountDetailBean accountDetailBean =   ((AccountDetailBean)response.body());

                if(accountDetailBean.getErrorCode().equals("0")) {
                    tv_loginName.setText(accountDetailBean.getAccount_detail().getAccount());
                    tv_ename.setText(accountDetailBean.getAccount_detail().getUsername());
                    tv_uid.setText(accountDetailBean.getAccount_detail().getUid());
                    tv_zichan.setText(accountDetailBean.getAccount_detail().getAccount_balance());
                    tv_ename.setText(MyApplication.userBean.getIdentity_info().getName());

                }else {
                    GetToastUtil.getToads(getActivity(), accountDetailBean.getMessage());

                }
                // dialog.cancel();
            }
            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {
                // dialog.cancel();
                GetToastUtil.getToads(getActivity(), t.getMessage());
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        initView();
    }
}
