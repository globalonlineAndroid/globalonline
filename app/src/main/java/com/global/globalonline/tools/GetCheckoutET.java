package com.global.globalonline.tools;

import android.content.Context;
import android.widget.EditText;

import com.global.globalonline.R;

/**
 * Created by lijl on 16/6/5.
 */
public class GetCheckoutET {


    public static  boolean    checkout(Context cxt , EditText... args){



        for (int i = 0; i < args.length; i++) {

            if (args[i].getText().toString().equals("")){
                GetToastUtil.getToads(cxt,cxt.getResources().getString(R.string.act_base_no_wanzheng));
                return false;
            }

        }

        return true;

    }
}
