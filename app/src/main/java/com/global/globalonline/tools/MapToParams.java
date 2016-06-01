package com.global.globalonline.tools;

import com.global.globalonline.base.MyApplication;
import com.global.globalonline.base.UrlApi;
import com.global.globalonline.bean.ParsMapBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by thinkPad on 2015/7/4.
 */
public class MapToParams {

    public static String getToken(Map<String,String> mapParams){

        //mapParams.put("timestamp", (new Date()).getTime() + "");

        ArrayList list = new ArrayList();

        Iterator it = mapParams.keySet().iterator();
        String keyStr = "";
        while(it.hasNext()) {
            keyStr = it.next().toString();
            list.add(keyStr);
        }

        Collections.sort(list);
        String s_token = "";

        for(int var6 = 0; var6 < list.size(); ++var6) {
            s_token = s_token + (String)mapParams.get(list.get(var6)) + "|";
        }

        s_token = MD5Util.getMD5String(s_token + UrlApi.key);

       // mapParams.put("token",s_token) ;

        return  s_token;
    }


    public static ParsMapBean getMap(Map<String,String> map){
        String userid = "0";
        StringBuffer  pars =new StringBuffer();
        if(map == null){
            map = new HashMap<>();
        }
        ParsMapBean parsMap = new ParsMapBean();

        String time = new Date().getTime()+"";
        map.put("timestamp",time);
        pars.append(time);
        if(!map.containsKey("userid") && MyApplication.userBean != null &&!StringUtil.isBlank(MyApplication.userBean.getUserid())) {
            map.put("userid", MyApplication.userBean.getUserid());
            pars.append("&userid="+MyApplication.userBean.getUserid());
        }
        if( MyApplication.userBean != null &&!StringUtil.isBlank(MyApplication.userBean.getAuth_key())) {
            map.put("auth_key", MyApplication.userBean.getAuth_key());
            parsMap.setAuth_key(MyApplication.userBean.getAuth_key());
            pars.append("&auth_key="+MyApplication.userBean.getAuth_key());
        }

        String token = getToken(map);

        parsMap.setTime(time);
        parsMap.setToken(token);



        pars.append("&token="+token);


        parsMap.setPublicPars(pars.toString());

        return parsMap;
    }



    public static Map<String,String> getParsMap(Map<String,String> parsMap){
        String userid = "0";

        if(parsMap == null){
            parsMap = new HashMap<>();
        }

        String time = new Date().getTime()+"";
        parsMap.put("timestamp",time);


        if(!parsMap.containsKey("userid") && MyApplication.userBean != null &&!StringUtil.isBlank(MyApplication.userBean.getUserid())) {
            parsMap.put("userid", MyApplication.userBean.getUserid());

        }else {
            parsMap.put("userid", "0");
        }
        if( MyApplication.userBean != null &&!StringUtil.isBlank(MyApplication.userBean.getAuth_key())) {
            parsMap.put("auth_key", MyApplication.userBean.getAuth_key());
        }

        String token = getToken(parsMap);
        parsMap.put("token",token);


        return parsMap;
    }




}
