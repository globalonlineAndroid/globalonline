package com.global.globalonline.base;

import android.support.annotation.ColorRes;

import com.global.globalonline.R;

/**
 * Created by lijl on 16/5/29.
 */
public class StaticBase {

    /*数据库表的module*/

    public  static  String COUTRY = "coutry";
    public  static  String INCOMEBANK = "incomebank";
    public  static  String BANK="bank";
    public  static  String VIRTUALOIN = "virtualoin";
    public  static  String BANKTYPE="cartype";
    public  static  String ALIPAY="alipay";
    /*end*/


    @ColorRes
    public static int[] colorResIds = {R.color.springgreen, R.color.forestgreen, R.color.goldenrod,
            R.color.indianred,R.color.maroon};

    /*public static void  set(){

        srl_trading.setColorSchemeResources(R.color.springgreen, R.color.forestgreen, R.color.goldenrod,
                R.color.indianred,R.color.maroon);
    }*/


}
