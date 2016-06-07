package com.global.globalonline.service.KLine;

import com.global.globalonline.bean.CombinedChartEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by lijl on 16/6/6.
 */
public interface KLineService {

    @GET("kline.json")  //获K线数据
    Call<CombinedChartEntity> kline(@QueryMap Map<String,String> map);
}
