package com.global.globalonline.service;

import android.app.Activity;

import retrofit2.Call;

/**
 * Created by lijl on 16/5/28.
 */
public interface RestService {

        public <T> void  get(Activity activity,String tishi,Call<T> tCall, final CallBackService callBackService);
}
