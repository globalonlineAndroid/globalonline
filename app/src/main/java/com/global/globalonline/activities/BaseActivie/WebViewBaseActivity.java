package com.global.globalonline.activities.BaseActivie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.base.BaseActivity;

public class WebViewBaseActivity extends BaseActivity {


    WebView webView;
    TextView title;

    String type = "";
    String url = "";

    public static void toActivity(Activity activity, String url){
        Intent intent = new Intent(activity,WebViewBaseActivity.class);
        intent.putExtra("url",url);
        activity.startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_base_view);
        webView = (WebView) this.findViewById(R.id.webview);
        title = (TextView) this.findViewById(R.id.title);
        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setDisplayZoomControls(false);

        url = getIntent().getStringExtra("url");


        webView.loadUrl(url);
    }
}
