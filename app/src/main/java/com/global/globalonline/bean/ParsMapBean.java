package com.global.globalonline.bean;

import java.util.Map;

/**
 * Created by lijl on 16/5/28.
 */
public class ParsMapBean {
    private String time;
    private String token;
    Map<String,String> map;
    private String auth_key;
    private String publicPars;

    public String getAuth_key() {
        return auth_key;
    }

    public void setAuth_key(String auth_key) {
        this.auth_key = auth_key;
    }







    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
    public String getPublicPars() {
        return publicPars;
    }

    public void setPublicPars(String publicPars) {
        this.publicPars = publicPars;
    }
}
