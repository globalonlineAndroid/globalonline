package com.global.globalonline.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.global.globalonline.R;
import com.global.globalonline.base.StaticBase;
import com.global.globalonline.dao.PullRefreshDao;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by lijl on 2016/12/29.
 */

@EViewGroup(R.layout.view_comm_refresh)
public class PullRefreshView extends LinearLayout {


    @ViewById
    AutoSwipeRefreshLayout srl_tradingFloor;
    @ViewById
    ListView lv_tradingFloor;

    public PullRefreshView(Context context) {
        super(context);
    }

    public PullRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullRefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void audoRefresh(){
        srl_tradingFloor.autoRefresh();

    }

    public ListView getListView(){
        return lv_tradingFloor;

    }




    public static int  status = 0;
    public void pullRefresh(final PullRefreshDao pullRefreshDao){
        srl_tradingFloor.setColorSchemeResources(StaticBase.colorResIds);

        lv_tradingFloor.setOnScrollListener(new AbsListView.OnScrollListener() {

            int firstVisibleItem; // 当前第一个可见Item的位置
            int totalItemCount;
            int lastVisibleItem;
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                if(totalItemCount==lastVisibleItem && scrollState == SCROLL_STATE_IDLE && status == 0){
                    status = 1;
                    Log.e("log", "滑到底部");
                    pullRefreshDao.UpLoading();
                    srl_tradingFloor.setRefreshing(false);


                    status = 0;

                }

            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                this.firstVisibleItem = firstVisibleItem;
                this.totalItemCount=totalItemCount;
                this.lastVisibleItem=firstVisibleItem+visibleItemCount;

            }
        });



        srl_tradingFloor.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(status == 0) {
                    status = 1;
                    Log.e("log", "下拉刷新");
                    pullRefreshDao.DownRefresh();
                    srl_tradingFloor.setRefreshing(false);

                    status = 0;
                }

            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @AfterViews
    void init() {

    }


}
