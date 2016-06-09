package com.global.globalonline.service.serviceImpl;

import android.app.Activity;
import android.app.Dialog;

import com.global.globalonline.service.CallBackService;
import com.global.globalonline.service.RestService;
import com.global.globalonline.tools.GetDialogUtil;
import com.global.globalonline.tools.GetToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lijl on 16/5/28.
 */
public class RestServiceImpl implements RestService {

    Dialog dialog = null;
    @Override
    public <T> void get(final Activity activity, String tishi , Call<T> tCall, final CallBackService callBackService) {
        dialog = null;
        if(activity != null) {
            dialog = GetDialogUtil.loading(activity,tishi);
        }

        tCall.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                T baseBean = (T) response.body();

                if(dialog != null){
                    dialog.cancel();
                }
                GetToastUtil.getLog( call.request().url().encodedPath());

                //if (baseBean.getErrorCode().equals("0")) {
                    callBackService.onResponse(call, response);
                //}else {
                    //Toast.makeText()
               // }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if(dialog != null){
                    dialog.cancel();
                }
               /* if(activity != null){
                    GetToastUtil.getToads(activity,t.getMessage());
                }*/
                callBackService.onFailure(call,t);
            }
        });
    }
}
