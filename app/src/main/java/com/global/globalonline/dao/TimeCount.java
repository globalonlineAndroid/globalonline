package com.global.globalonline.dao;

import android.os.CountDownTimer;
import android.widget.Button;

import com.global.globalonline.R;

/**
 * Created by lijl on 16/5/29.
 */
public class TimeCount extends CountDownTimer{

    Button button;

    public TimeCount(long millisInFuture, long countDownInterval, Button button) {
        super(millisInFuture, countDownInterval);
        this.button = button;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        button.setBackgroundResource(R.drawable.btn_jinzhi);
        button.setClickable(false);
        button.setText(millisUntilFinished / 1000 + "秒后重新获取");


    }

    @Override
    public void onFinish() {
        button.setText("获取验证码");
        button.setClickable(true);
        button.setBackgroundResource(R.drawable.btn_jinzhi);

    }
}
