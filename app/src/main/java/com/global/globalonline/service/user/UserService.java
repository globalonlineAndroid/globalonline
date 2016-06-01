package com.global.globalonline.service.user;

import com.global.globalonline.bean.CodeBean;
import com.global.globalonline.bean.UserBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by lijl on 16/5/29.
 */
public interface UserService  {

    @GET("send_authcode.json")  //发送验证码
    Call<CodeBean> send_authcode(@QueryMap Map<String,String>  map);

    @GET("register.json")  //注册
    Call<UserBean> register(@QueryMap Map<String,String>  map);

    @GET("login.json") //登陆
    Call<UserBean> loging(@QueryMap Map<String,String>  map);

    @GET("find_pwd.json")  //找回密码
    Call<UserBean> find_pwd(@QueryMap Map<String,String>  map);

    @GET("identify_auth.json")  //认证
    Call<UserBean> identify_auth(@QueryMap Map<String,String>  map);

    @GET("upt_login_pwd.json")  // 修改登陆密码
    Call<UserBean> upt_login_pwd(@QueryMap Map<String,String>  map);
    @GET("upt_trade_pwd.json")     // 修改交易密码
    Call<UserBean> upt_trade_pwd(@QueryMap Map<String,String>  map);

}
