package com.global.globalonline.base;

/**
 * Created by lijl on 16/6/12.
 */
public class GetConfiguration {

    public static  String ZH = "zh";   /* 中文*/
    public static  String EN = "en"; /* 英语*/

    public static String LANGUAGE = ZH ;   /* 是否是中文*/

    public static boolean isZh(){
        boolean b = true;;

        if(LANGUAGE.equals(EN)){
            b = false;
        }
        return b;
    }
}
