package com.global.globalonline.tools;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by lijl on 16/5/31.
 */
public class GetToastUtil {

    public static  void getLog(String messge){
        Log.wtf("tag",messge);

    }

    public static  void getToads(Context context,String messge){
        Toast.makeText(context,messge, Toast.LENGTH_SHORT).show();

    }
}
