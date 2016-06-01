package com.global.globalonline.service;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by lijl on 16/5/28.
 */
public interface CallBackService {

    public <T> void onResponse(Call<T> call, Response<T> response);


    public <T> void onFailure(Call<T> call, Throwable t) ;


}
