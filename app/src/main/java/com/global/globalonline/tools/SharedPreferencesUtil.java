package com.global.globalonline.tools;

import android.app.Activity;
import android.content.SharedPreferences;

import com.global.globalonline.bean.UserBean;
import com.google.gson.Gson;

/**
 * Created by lijl on 16/6/14.
 */
public class SharedPreferencesUtil {

    public static String USER = "userInfo";


    public static String  get(Activity act,String key){
        String t = null;

        SharedPreferences preferences = act.getSharedPreferences(
                USER, act.MODE_PRIVATE);

        // 取得相应的值，如果没有该值，说明还未写入，用true作为默认值
        t = preferences.getString(key, "");

        return t;
    }

    public static UserBean  getUserInfo(Activity act){
        UserBean t = null;

        Gson gson = new Gson();
        //String user_json = gson.toJson(text);

        SharedPreferences preferences = act.getSharedPreferences(
                USER, act.MODE_PRIVATE);

        // 取得相应的值，如果没有该值，说明还未写入，用true作为默认值
        String str = preferences.getString("userInfo","");
        if(!StringUtil.isBlank(str)){
            t = gson.fromJson(str ,UserBean.class);
        }

        return t;
    }
    public static void  setUserInfo(Activity act,UserBean userBean){

        Gson g = new Gson();
        String str = g.toJson(userBean);
        SharedPreferences preferences =act.getSharedPreferences(
                USER, act.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        // 存入数据
        editor.putString("userInfo", str);
        // 提交修改
        editor.commit();

    }
}