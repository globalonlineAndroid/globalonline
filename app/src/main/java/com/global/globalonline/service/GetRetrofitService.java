package com.global.globalonline.service;

import com.global.globalonline.base.UrlApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lijl on 16/5/28.
 */
public class GetRetrofitService {

   /* HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);*/
    /*OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .retryOnConnectionFailure(true)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addNetworkInterceptor(mTokenInterceptor)
            .build();*/

    public static Retrofit retrofit;

    public static  <T> T getRestClient(Class<T> aClass){

        /* Interceptor mTokenInterceptor = new Interceptor() {

             @Override
             public Response intercept(Chain chain) throws IOException {

                 return null;
             }
         };
*/
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                //.addInterceptor(interceptor)
               // .addNetworkInterceptor(mTokenInterceptor)

                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();


        retrofit = new Retrofit.Builder()
                .baseUrl(UrlApi.baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        T t = retrofit.create(aClass);
       return  t;
    }

    public static  <T> T getRestClientNoGson(Class<T> aClass){

        /* Interceptor mTokenInterceptor = new Interceptor() {

             @Override
             public Response intercept(Chain chain) throws IOException {

                 return null;
             }
         };



*/

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                //.addInterceptor(interceptor)
                // .addNetworkInterceptor(mTokenInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();


        retrofit = new Retrofit.Builder()
                .baseUrl(UrlApi.baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        T t = retrofit.create(aClass);
        return  t;
    }

}

