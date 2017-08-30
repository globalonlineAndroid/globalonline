package com.global.globalonline.dao;

import android.app.Activity;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import com.global.globalonline.R;

/**
 * Created by lijl on 16/5/29.
 */
public class TimeCount extends CountDownTimer{

    Button button;
    TextView button_textView;
    Activity activity;

    public TimeCount(long millisInFuture, long countDownInterval, Button button) {
        super(millisInFuture, countDownInterval);
        this.button = button;
    }
    public TimeCount(long millisInFuture, long countDownInterval, Activity activity, TextView button) {
        super(millisInFuture, countDownInterval);
        this.button_textView = button;
        this.activity = activity;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if(button != null) {
            button.setBackgroundResource(R.drawable.btn_jinzhi);
            button.setClickable(false);
            button.setText(millisUntilFinished / 1000 + "秒后重新获取");
        }else {
            button_textView.setTextColor(activity.getResources().getColor(R.color.ac_user_registerd_hint));
            button_textView.setClickable(false);
            button_textView.setText(millisUntilFinished / 1000 + "秒后重新获取");
        }


    }

    @Override
    public void onFinish() {
        if(button != null) {
            button.setText("获取验证码");
            button.setClickable(true);
            button.setBackgroundResource(R.drawable.btn_jinzhi);
        }else {

            button_textView.setText("获取验证码");
            button_textView.setClickable(true);
            button_textView.setTextColor(activity.getResources().getColor(R.color.FFA200));

        }

    }
}
