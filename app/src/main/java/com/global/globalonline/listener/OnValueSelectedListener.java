package com.global.globalonline.listener;

/**
 * Created by kqw on 2016/5/16.
 * OnValueSelectedListener
 */
public interface OnValueSelectedListener {
    public void start();

    public void data(double open, double close, double high, double low);

    public void end();
}
