package com.global.globalonline.service;

import com.global.globalonline.bean.ConfigBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by lijl on 16/5/27.
 */
public interface ConfigService {

    @GET("get_config.json")
    Call<ConfigBean> getCofig(@QueryMap Map<String,String>  map);
}
