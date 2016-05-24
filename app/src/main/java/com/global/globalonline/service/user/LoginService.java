package com.global.globalonline.service.user;

import com.global.globalonline.bean.UserBean;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by lijl on 16/5/24.
 */
public interface LoginService {

    @GET("login.json")
    Call<UserBean> loging(@Query("mobile") String mobile, @Query("pwd") String pwd,@Query("timestamp") String timestamp, @Query("token") String token);
    @GET("login.json")
    Call<UserBean> getInfoa(@Query("mobile") String mobile, @Query("pwd") String pwd, @Query("token") String token);

}
