package com.global.globalonline.activities.mainTab;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;

import com.global.globalonline.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity {

    @ViewById
    ImageButton ibtn_homepage,ibtn_tradingfloor,ibtn_my;



    public static  String TAG = "MainActivity";
    private View currentButton;

    HomePageFrament homePageFrament;
    TradingFloorFrament tradingFloorFrament;
    MyFrament myFrament;


    @AfterViews
    void init(){
        initComponents();
    }


    private void initComponents() {

        ibtn_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

}
