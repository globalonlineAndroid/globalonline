package com.global.globalonline.tools;

import android.content.Context;
import android.widget.EditText;

/**
 * Created by lijl on 16/6/5.
 */
public class GetCheckoutET {


    public static  boolean    checkout(Context cxt , EditText... args){



        for (int i = 0; i < args.length; i++) {

            if (args[i].getText().toString().equals("")){
                GetToastUtil.getToads(cxt,"请填写完整信息");
                return false;
            }

        }

        return true;

    }
}
