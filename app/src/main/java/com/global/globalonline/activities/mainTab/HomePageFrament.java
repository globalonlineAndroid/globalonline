package com.global.globalonline.activities.mainTab;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.global.globalonline.R;
import com.global.globalonline.activities.virtualCurrency.VirtualTradeActivity;
import com.global.globalonline.adapter.mainTab.HomePageAdapter;
import com.global.globalonline.bean.VirtualListItemBean;
import com.global.globalonline.bean.VirtualTradingBean;
import com.global.globalonline.bean.gonggao.GongGaoBean;
import com.global.globalonline.bean.gonggao.GongGaoItemBean;
import com.global.globalonline.dao.PullRefreshDao;
import com.global.globalonline.service.CallBackService;
import com.global.globalonline.service.GetRetrofitService;
import com.global.globalonline.service.RestService;
import com.global.globalonline.service.serviceImpl.RestServiceImpl;
import com.global.globalonline.service.virtualTrading.VirtualService;
import com.global.globalonline.tools.GetQuanXian;
import com.global.globalonline.tools.GetToastUtil;
import com.global.globalonline.tools.MapToParams;
import com.global.globalonline.view.PullRefreshView;
import com.global.globalonline.view.VerticalTextview;
import com.global.globalonline.view.infiniteindicator.InfiniteIndicatorLayout;
import com.global.globalonline.view.infiniteindicator.slideview.BaseSliderView;
import com.global.globalonline.view.infiniteindicator.slideview.DefaultSliderView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

@EFragment(R.layout.activity_home_page)
public class HomePageFrament extends Fragment{

    @ViewById
    PullRefreshView plf_tradingFloor;
    @ViewById
    InfiniteIndicatorLayout viewPager;


    private String block = "1"; //1交易区 2实验区
    private String gonggao_type = "1" ;//文章类型  1:官方公告 2：业界动态


    private ArrayList<Integer> localImages = new ArrayList<Integer>();


    HomePageAdapter tradingFloorAdapter;
    private static final int REFRESH_COMPLETE = 0X110;

    List<VirtualTradingBean> list = new ArrayList<>();
    VirtualService virtualService;
    VerticalTextview verticalTextview = null;

    private String[] images = {"http://www.globalonline.cc/upload/shop/cdfghiklmortvwyz0128.png",
            "http://www.globalonline.cc/upload/shop/cdfghiklmortvwyz0128.png"
    };


    //首页循环图片
    int[] mainViewImgs={R.drawable.home_page,R.drawable.home_page,R.drawable.home_page};

    @AfterViews()
    void init(){
        //addImgPager();
        //导航显示位置

        virtualService = GetRetrofitService.getRestClient(VirtualService.class);

        plf_tradingFloor.audoRefresh();


        plf_tradingFloor.pullRefresh(new PullRefreshDao() {
            @Override
            public void DownRefresh() {
                if(plf_tradingFloor.getListView().getHeaderViewsCount() == 0) {
                    plf_tradingFloor.getListView().addHeaderView(addImgPager());
                }
                initlist();
            }

            @Override
            public void UpLoading() {
                Log.e("log", "滑到底部b");
            }
        });

        plf_tradingFloor.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //VirtualTradeActivity_.intent(getActivity()).start();
                if(GetQuanXian.getIsQuanXian(getActivity())) {
                    VirtualTradingBean virtualTradingBean = (VirtualTradingBean) parent.getItemAtPosition(position);
                    VirtualTradeActivity.toActiviy(getActivity(), virtualTradingBean.getSymbol());
                }
            }
        });

    }

    private View addImgPager() {


        View headerView = View.inflate(getActivity(),R.layout.act_main_lunbotu, null);
        InfiniteIndicatorLayout viewPager = (InfiniteIndicatorLayout)headerView.findViewById(R.id.viewPager);
        VerticalTextview vt_gonggao = (VerticalTextview)headerView.findViewById(R.id.vt_gonggao);
        get_gonggao_data(vt_gonggao);
        tab(headerView);

        WindowManager wm = getActivity().getWindowManager();

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();


        int  imgeHeight =  height/3;
        headerView.setMinimumHeight(imgeHeight);
       // viewPager.setMinimumHeight(imgeHeight);

        DefaultSliderView textSliderView = new DefaultSliderView(getActivity());
        textSliderView
                .image(mainViewImgs[0])
                .setScaleType(BaseSliderView.ScaleType.Fit);
        viewPager.addSlider(textSliderView);
        DefaultSliderView textSliderView1 = new DefaultSliderView(getActivity());
        textSliderView1
                .image(mainViewImgs[1])
                .setScaleType(BaseSliderView.ScaleType.Fit);
        viewPager.addSlider(textSliderView1);
       /* DefaultSliderView textSliderView2 = new DefaultSliderView(getActivity());
        textSliderView2
                .image(mainViewImgs[2])
                .setScaleType(BaseSliderView.ScaleType.Fit);
        viewPager.addSlider(textSliderView2);*/

        viewPager.setIndicatorPosition(InfiniteIndicatorLayout.IndicatorPosition.Center_Bottom);
        //循环速度
        viewPager.setInterval(2000);
        viewPager.startAutoScroll();

        return  headerView;

    }


    public void initlist(){


        tradingFloorAdapter = null;


        Map<String, String> stringMap = new HashMap<String, String>();
        /*tringMap.put("next_id", "0");
        stringMap.put("perpage", "20");
        stringMap.put("orderby", "1");*/
        stringMap.put("block",block);

        stringMap = MapToParams.getParsMap(stringMap);

        Call<VirtualListItemBean> call = virtualService.index(stringMap);
        RestService restService = new RestServiceImpl();

        restService.get(null, "", call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                VirtualListItemBean virtualListItemBean =(VirtualListItemBean) response.body();
                if(virtualListItemBean.getErrorCode().equals("0")){

                    list = new ArrayList<VirtualTradingBean>();
                    for (int i = 0; i < virtualListItemBean.getItems().size(); i++) {
                        VirtualTradingBean v = virtualListItemBean.getItems().get(i);
                        list.add(v);
                    }
                    if(tradingFloorAdapter == null) {
                        tradingFloorAdapter = new HomePageAdapter(getActivity(), list);
                        plf_tradingFloor.getListView().setAdapter(tradingFloorAdapter);
                    }else {
                        tradingFloorAdapter.notifyDataSetChanged();
                    }

                }else {
                    GetToastUtil.getToads(getActivity(),virtualListItemBean.getMessage());
                }
            }

            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {

            }
        });
    }

    public void get_gonggao_data(final VerticalTextview ver){


        tradingFloorAdapter = null;


        Map<String, String> stringMap = new HashMap<String, String>();
        /*tringMap.put("next_id", "0");
        stringMap.put("perpage", "20");
        stringMap.put("orderby", "1");*/
        stringMap.put("type",gonggao_type);
        stringMap.put("count","10");

        stringMap = MapToParams.getParsMap(stringMap);

        Call<GongGaoBean> call = virtualService.get_gonggao(stringMap);
        RestService restService = new RestServiceImpl();

        restService.get(null, "", call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {

                GongGaoBean gong =(GongGaoBean) response.body();
                if(gong.getErrorCode().equals("0")){

                   init_gonggao_view(ver,gong.getGongGaoItemBeen());
                }else {
                    GetToastUtil.getToads(getActivity(),gong.getMessage());
                }
            }

            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {

            }
        });
    }
/*
    public  View initGuanGao(){

        WindowManager wm = getActivity().getWindowManager();

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();


         int  imgeHeight =  width/3;         //300/60

        localImages.add(R.drawable.home_page);
        localImages.add(R.drawable.home_page);
        //localImages.add(R.drawable.page_1);

        ConvenientBanner mConvenientBanner = new ConvenientBanner(getActivity());
        mConvenientBanner.setMinimumHeight(imgeHeight);
        mConvenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, localImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.icon_round_hui,R.drawable.icon_round_hl})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                    }
                });
        mConvenientBanner.startTurning(2000);
        return  mConvenientBanner;
    }*/


    private void init_gonggao_view(VerticalTextview mTextview,final List<GongGaoItemBean> titleList){

        if (titleList == null || titleList.size() == 0){
            return;
        }
        mTextview.setTextList(titleList);
        mTextview.setText(14, 5, getResources().getColor(R.color.ac_base_ziti_hui));//设置属性
        mTextview.setTextStillTime(3000);//设置停留时长间隔
        mTextview.setAnimTime(1000);//设置进入和退出的时间间隔
        mTextview.setOnItemClickListener(new VerticalTextview.OnItemClickListener(){


            @Override
            public void onItemClick(int position) {

                Toast.makeText(getActivity(),"我点击了"+titleList.get(position).getTitle(),Toast.LENGTH_SHORT).show();

            }
        });

        mTextview.startAutoScroll();
        verticalTextview = mTextview;

    }

    private void tab(View view){

        final TextView tv_jiaoyi = (TextView) view.findViewById(R.id.tv_jiaoyi);
        final TextView tv_shiyan = (TextView) view.findViewById(R.id.tv_shiyan);
        final View view_tv_jiaoyi = (View) view.findViewById(R.id.view_tv_jiaoyi);
        final View view_tv_shiyan = (View) view.findViewById(R.id.view_tv_shiyan);


        tv_jiaoyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                block = "1";
                tv_jiaoyi.setTextColor(getResources().getColor(R.color.F58703));
                view_tv_jiaoyi.setBackgroundResource(R.color.F58703);
                tv_shiyan.setTextColor(getResources().getColor(R.color.ac_base_ziti_hui));
                view_tv_shiyan.setBackgroundResource(R.color.ac_base_ziti_hui);
                tradingFloorAdapter = null;

            }
        });

        tv_shiyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                block = "2";
                tv_jiaoyi.setTextColor(getResources().getColor(R.color.ac_base_ziti_hui));
                view_tv_jiaoyi.setBackgroundResource(R.color.ac_base_ziti_hui);
                tv_shiyan.setTextColor(getResources().getColor(R.color.F58703));
                view_tv_shiyan.setBackgroundResource(R.color.F58703);
                tradingFloorAdapter = null;

            }
        });


    }





    @Override
    public void onPause() {
        super.onPause();

        if (verticalTextview != null){

           // verticalTextview.stopAutoScroll();
        }
    }
}
