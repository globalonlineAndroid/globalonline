package com.global.globalonline.service;

import com.global.globalonline.bean.BaseBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by lijl on 16/6/15.
 */
public interface BaseService {

    @GET("rmb_paycheck_apply.json")  //人民币充值
    Call<BaseBean> rmb_paycheck_apply(@QueryMap Map<String,String> map);

    @GET("rmb_extract_apply.json")  //人民币提现申请
    Call<BaseBean> rmb_extract_apply(@QueryMap Map<String,String> map);

}
