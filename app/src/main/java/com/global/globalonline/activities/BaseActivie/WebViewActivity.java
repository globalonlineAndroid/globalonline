package com.global.globalonline.activities.BaseActivie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.base.UrlApi;

public class WebViewActivity extends BaseActivity {


    WebView webView;
    TextView title;

    String type = "";
    String url = "";

    public static void toActivity(Activity activity, String type){
        Intent intent = new Intent(activity,WebViewActivity.class);
        intent.putExtra("type",type);
        activity.startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = (WebView) this.findViewById(R.id.webview);
        title = (TextView) this.findViewById(R.id.title);

        type = getIntent().getStringExtra("type");
        if(type.equals("guanyu")){
            title.setText(getResources().getString(R.string.act_user_aboutUs_title));
                    url = UrlApi.b ? getResources().getString(R.string.act_base_about) : getResources().getString(R.string.act_base_about_test);
        }else if(type.equals("fuwuxieyi")){
            title.setText(getResources().getString(R.string.act_base_serviceAgreement_title));
            url = getResources().getString(R.string.act_base_fuwuxieyi);
        }

        webView.loadUrl(url);
    }
}
