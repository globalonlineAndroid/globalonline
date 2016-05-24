package com.global.globalonline.tools;

import com.global.globalonline.base.MyApplication;
import com.global.globalonline.base.UrlApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by thinkPad on 2015/7/4.
 */
public class MapToParams {

    public static String getToken(Map<String,String> mapParams){

        //mapParams.put("timestamp", (new Date()).getTime() + "");
        if(!mapParams.containsKey("userid") && !StringUtil.isBlank(MyApplication.userid)) {
            mapParams.put("userid", MyApplication.userid);
        }

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


        return  s_token;
    }






}
