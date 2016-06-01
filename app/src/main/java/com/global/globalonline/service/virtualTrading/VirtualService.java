package com.global.globalonline.service.virtualTrading;

import com.global.globalonline.bean.VirtualListItemBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by lijl on 16/6/2.
 */
public interface VirtualService {


    @GET("index.json")  //获取币种最新行情数据
    Call<VirtualListItemBean> index(@QueryMap Map<String,String> map);

}
