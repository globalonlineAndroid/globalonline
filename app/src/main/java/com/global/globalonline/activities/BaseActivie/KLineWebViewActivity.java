package com.global.globalonline.activities.BaseActivie;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.global.globalonline.R;
import com.global.globalonline.base.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_kline_web_view)
public class KLineWebViewActivity extends BaseActivity {

    @ViewById
    WebView wv_kLine;


    String symbol = "";


    public static  void toActivity(Activity activity, String symbol){

        Intent intent  = new Intent(activity, KLineWebViewActivity_.class);
        intent.putExtra("symbol",symbol);
        activity.startActivity(intent);


    }
    @AfterViews
    void init(){
        symbol = getIntent().getStringExtra("symbol");
        //wv_kLine.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        WebSettings settings = wv_kLine.getSettings();

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


        wv_kLine.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        String url = getResources().getString(R.string.act_base_KLine)+"symbol="+symbol;
        wv_kLine.loadUrl(url);


        wv_kLine.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });


        wv_kLine.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

                if (newProgress == 100) {
                    // 网页加载完成

                } else {
                    // 加载中

                }

            }

            @Override
            public void onReceivedTitle(WebView view, String titles) {
                super.onReceivedTitle(view, titles);
                //title.setText(titles);
            }
        });

    }

}
