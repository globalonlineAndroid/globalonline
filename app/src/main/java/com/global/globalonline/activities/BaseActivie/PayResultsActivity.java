package com.global.globalonline.activities.BaseActivie;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.base.StaticBase;
import com.global.globalonline.tools.GetSelectBouncedUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_certification_results)
public class PayResultsActivity extends BaseActivity {

    @ViewById
    TextView tv_zhuanghu,tv_price,tv_xunibiName,tv_xunibinuber;


    String zhanghao = "";
    String price = "";
    String xuniNumber = "";
    String symbol = "";


    public static void  toActivity(Activity activity,String symbol,String zhanghao,String price,String xuniNumber){

        Intent i = new Intent(activity, PayResultsActivity_.class);

        i.putExtra("zhanghao",zhanghao);
        i.putExtra("price",price);
        i.putExtra("xuniNumber",xuniNumber);
        i.putExtra("symbol",symbol);
        activity.startActivity(i);
    }

    @AfterViews
    void init(){
        zhanghao = getIntent().getStringExtra("zhanghao");
        price = getIntent().getStringExtra("price");
        xuniNumber = getIntent().getStringExtra("xuniNumber");
        symbol = getIntent().getStringExtra("symbol");
        tv_zhuanghu.setText(zhanghao);
        tv_price.setText(price);
        tv_xunibiName.setText(xuniNumber);

       String symbolName =  GetSelectBouncedUtil.getBankName(PayResultsActivity.this, StaticBase.VIRTUALOIN,symbol);

        tv_xunibinuber.setText("折合"+symbolName+"数量:");
    }




}
