package com.global.globalonline.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.global.globalonline.R;

/**
 * Created by lijl on 16/5/31.
 */
public class GetToastUtil {

    public static void getLog(String messge) {
        Log.wtf("tag", messge);

    }

    public static void getToads(Context context, String messge) {
        Toast.makeText(context, messge, Toast.LENGTH_SHORT).show();
    }


    public static void getSuccessToads(Activity activity) {
        Toast.makeText(activity, activity.getResources().getString(R.string.act_base_successful), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setClass(activity,activity.getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);

    }
}
