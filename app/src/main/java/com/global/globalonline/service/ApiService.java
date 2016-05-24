package com.global.globalonline.service;

import com.global.globalonline.bean.UserBean;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by lijl on 16/5/22.
 */
public interface ApiService {


    @GET("login.json")
    Call<UserBean>  getInfo(@Query("mobile") String mobile,@Query("pwd") String pwd,@Query("token") String token);
}
