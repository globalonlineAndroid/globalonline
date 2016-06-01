package com.global.globalonline.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by lijl on 16/5/31.
 */
public class GetToastUtil {

    public static  void getToads(Context context,String messge){
        Toast.makeText(context,messge, Toast.LENGTH_SHORT).show();

    }
}
