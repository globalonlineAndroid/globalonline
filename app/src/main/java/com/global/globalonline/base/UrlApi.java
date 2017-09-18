package com.global.globalonline.base;

/**
 * Created by lijl on 16/5/23.
 */
public class UrlApi {

    public  static  String key = "1234567890";
    public static  String action =  "/mapi/";

    public static  String t_url = "http://test.g.api.stupid2brave.com";
    public static  String url   = "http://api.globalonline.cc";

    public static  String t_image_url = "http://test.g.t.stupid2brave.com";
    public static  String image_url   = "http://v.globalonline.cc";

    public static  String t_url_gonggao = "http://test.g.www.stupid2brave.com/service/articleapp";
    public static  String url_gonggao   = "http://api.globalonline.cc";

    public static boolean b = false;   //是不是正式
    public  static  String baseUrl =  (b ? url  : t_url) + action;   //接口地址
    public  static  String baseUrlGongGao =  (b ? url_gonggao  : t_url_gonggao)+"?id=" ;   //公告地址
    public  static  String baseImageUrl =b ? image_url : t_image_url; //图片地址接口







}
