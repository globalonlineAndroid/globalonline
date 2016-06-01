package com.global.globalonline.service;

import com.global.globalonline.bean.ConfigBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lijl on 16/5/27.
 */
public interface ConfigService {

    @GET("get_config.json")
    Call<ConfigBean> getCofig(@Query("timestamp") String timestamp, @Query("token") String token);
}
