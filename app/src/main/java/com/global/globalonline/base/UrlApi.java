package com.global.globalonline.base;

/**
 * Created by lijl on 16/5/23.
 */
public class UrlApi {

    public  static  String key = "1234567890";
    public static  String action =  "/mapi/";

   /* public  static  String baseUrl = "http://api.50f.cn/mapi/";
   public  static  String baseImageUrl = "http://coins.zhaojizi.com";*/

    public  static  String baseUrl = "http://api.globalonline.cc" + action;   //接口地址
    public  static  String baseImageUrl = "http://v.globalonline.cc"; //图片地址接口

    public  static  String aboutUrl_zh = "http://api.50f.cn/page/about_us_zh.html"; //关于我们_中文地址
    public  static  String aboutUrl_en = "http://api.50f.cn/page/about_us_en.html"; //关于我们_英文地址
    public  static  String service_agreement_zh = "http://api.50f.cn/page/service_agreement_zh.html"; //服务协议_中文地址
    public  static  String service_agreement_en = "http://api.50f.cn/page/service_agreement_en.html"; //服务协议_英文地址

    public  static  String KLine_zh = "http://v.globalonline.cc/index.php?r=wap/kline&"; //  symbol=3    K线图_中文地址
    public  static  String KLine_en = "http://v.globalonline.cc/index.php?r=wap/kline&lang=en&"; //  symbol=3    K线图_中文地址
    public  static  String KLine = GetConfiguration.isZh()?KLine_zh:KLine_en; //  symbol=3    K线图_中文地址



    public  static  String aboutUrl = GetConfiguration.isZh()?aboutUrl_zh:aboutUrl_en; //关于我们
    public  static  String service_agreement = GetConfiguration.isZh()?service_agreement_zh:service_agreement_en; //服务协议




}
