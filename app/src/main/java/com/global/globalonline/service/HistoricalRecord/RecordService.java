package com.global.globalonline.service.HistoricalRecord;

import com.global.globalonline.bean.RMB.chongzhi.ChongZhiBean;
import com.global.globalonline.bean.RMB.tixian.RmbExtractRecordBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by lijl on 16/6/15.
 */
public interface RecordService {
    @GET("rmb_extract_record.json")  //人民币提现记录
    Call<RmbExtractRecordBean> rmb_extract_record(@QueryMap Map<String,String> map);

    @GET("rmb_paycheck_record.json")  //人民币充值记录
    Call<ChongZhiBean> rmb_paycheck_record(@QueryMap Map<String,String> map);
}
