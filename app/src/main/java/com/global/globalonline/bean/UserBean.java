package com.global.globalonline.bean;

/**
 * Created by lijl on 16/5/21.
 * 用户类
 */
public class UserBean {

       private   String ErrorCode ;
       private   String message ;
       private   String token ;
       private   String userid ;
       private   String username ;

       private IdentifyBean identity_info;



    private   String auth_key ;
       private   String mobile ;
       private   String timestamp ;


    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAuth_key() {
        return auth_key;
    }

    public void setAuth_key(String auth_key) {
        this.auth_key = auth_key;
    }

    public IdentifyBean getIdentity_info() {
        return identity_info;
    }

    public void setIdentity_info(IdentifyBean identity_info) {
        this.identity_info = identity_info;
    }
}

