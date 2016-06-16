package com.global.globalonline.activities.mainTab;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.base.GetConfiguration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Locale;


@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity {

    @ViewById
    ImageButton ibtn_homepage,ibtn_tradingfloor,ibtn_my;
    @ViewById
    TextView title,tv_english,operation;
    @ViewById
    LinearLayout xiala;




    public static  String TAG = "MainActivity";
    private View currentButton;

    HomePageFrament homePageFrament;
    TradingFloorFrament tradingFloorFrament;
    MyFrament myFrament;


    @AfterViews
    void init(){
        initComponents();
        Locale.setDefault(Locale.CHINESE);
        if(GetConfiguration.LANGUAGE.equals(GetConfiguration.ZH)){
            operation.setText("中文");
        }else {
            operation.setText("En");
        }
    }


    @Click({R.id.operation,R.id.tv_english,R.id.tv_cn})
    void click(View view){
        switch (view.getId()){
            case R.id.operation :

                if(xiala.getVisibility() == View.VISIBLE){
                    xiala.setVisibility(View.GONE);
                }else {
                    xiala.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.tv_english:
                checkLa(Locale.ENGLISH);
                GetConfiguration.LANGUAGE = GetConfiguration.EN;
                break;
            case R.id.tv_cn:
                checkLa(Locale.CHINESE);
                GetConfiguration.LANGUAGE = GetConfiguration.ZH;
                break;
        }

    }


    private void initComponents() {

        ibtn_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText(getResources().getString(R.string.act_main_title_home));

                FragmentManager fm = MainActivity.this.getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                hideFragments(ft);

                if(homePageFrament != null){
                    ft.show(homePageFrament);
                }else {
                    homePageFrament = new HomePageFrament_();
                    ft.add(R.id.fl_mian, homePageFrament, MainActivity.TAG);


                }
                ft.commit();
                setButton(v);

            }
        });
        ibtn_tradingfloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText(getResources().getString(R.string.act_main_title_dating));

                FragmentManager fm = MainActivity.this.getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                hideFragments(ft);
                if(tradingFloorFrament != null){
                    ft.show(tradingFloorFrament);
                }else {
                    tradingFloorFrament = new TradingFloorFrament_();
                    ft.add(R.id.fl_mian, tradingFloorFrament, MainActivity.TAG);
                }
                ft.commit();
                setButton(v);
            }
        });

        ibtn_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText(getResources().getString(R.string.act_main_title_my));
                FragmentManager fm = MainActivity.this.getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                hideFragments(ft);
                if(myFrament != null){
                    ft.show(myFrament);
                }else {
                    myFrament = new MyFrament_();
                    ft.add(R.id.fl_mian, myFrament, MainActivity.TAG);
                }
                ft.commit();

                setButton(v);

            }
        });


        ibtn_homepage.performClick();

    }

    /**
     * 设置按钮的背景图片
     *
     * @param v
     */
    private void setButton(View v) {
        if (currentButton != null && currentButton.getId() != v.getId()) {
            currentButton.setEnabled(true);
        }
        v.setEnabled(false);
        currentButton = v;
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (homePageFrament != null) {
            transaction.hide(homePageFrament);
        }
        if (tradingFloorFrament != null) {
            transaction.hide(tradingFloorFrament);
        }
        if (myFrament != null) {
            transaction.hide(myFrament);
        }

    }


    public  void checkLa(Locale locale ){
        Configuration configCN = getBaseContext().getResources().getConfiguration();
        configCN.locale = locale;
        getBaseContext().getResources().updateConfiguration(configCN
                , getBaseContext().getResources().getDisplayMetrics());
        xiala.setVisibility(View.GONE);
        Intent intent = new Intent();
        intent.setClass(this,MainActivity_.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
