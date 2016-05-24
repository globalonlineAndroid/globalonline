package com.global.globalonline.service.user;

import com.global.globalonline.bean.UserBean;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by lijl on 16/5/24.
 */
public interface RegisteredService {


    @GET("send_authcode.json")
    Call<UserBean> send_authcode(@Query("mobile") String mobile, @Query("pwd") String pwd, @Query("token") String token);


}
