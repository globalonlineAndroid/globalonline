package com.global.globalonline.activities.mainTab;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.global.globalonline.R;
import com.global.globalonline.activities.virtualCurrency.VirtualTradeActivity;
import com.global.globalonline.adapter.mainTab.HomePageAdapter;
import com.global.globalonline.bean.VirtualListItemBean;
import com.global.globalonline.bean.VirtualTradingBean;
import com.global.globalonline.service.CallBackService;
import com.global.globalonline.service.GetRetrofitService;
import com.global.globalonline.service.RestService;
import com.global.globalonline.service.serviceImpl.RestServiceImpl;
import com.global.globalonline.service.virtualTrading.VirtualService;
import com.global.globalonline.tools.GetQuanXian;
import com.global.globalonline.tools.GetToastUtil;
import com.global.globalonline.tools.MapToParams;
import com.global.globalonline.view.AutoSwipeRefreshLayout;
import com.global.globalonline.view.LocalImageHolderView;

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
public class HomePageFrament extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    @ViewById
    AutoSwipeRefreshLayout srl_tradingFloor;
    @ViewById
    ListView lv_tradingFloor;

    private ArrayList<Integer> localImages = new ArrayList<Integer>();


    HomePageAdapter tradingFloorAdapter;
    private static final int REFRESH_COMPLETE = 0X110;

    List<VirtualTradingBean> list = null;
    VirtualService virtualService;

    private String[] images = {"http://www.globalonline.cc/upload/shop/cdfghiklmortvwyz0128.png",
            "http://www.globalonline.cc/upload/shop/cdfghiklmortvwyz0128.png"
    };

    @AfterViews()
    void init(){
        virtualService = GetRetrofitService.getRestClient(VirtualService.class);
        //initlist();
        srl_tradingFloor.autoRefresh();
        lv_tradingFloor.addHeaderView(initGuanGao());
        srl_tradingFloor.setColorSchemeResources(R.color.springgreen, R.color.forestgreen, R.color.goldenrod,
                R.color.indianred,R.color.maroon);
        srl_tradingFloor.setOnRefreshListener(this);

        lv_tradingFloor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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


    @Override
    public void onRefresh() {

        initlist();

    }

    public void initlist(){

        if(list != null){
            list.clear();
        }

        Map<String, String> stringMap = new HashMap<String, String>();
        /*tringMap.put("next_id", "0");
        stringMap.put("perpage", "20");
        stringMap.put("orderby", "1");*/

        stringMap = MapToParams.getParsMap(stringMap);

        Call<VirtualListItemBean> call = virtualService.index(stringMap);
        RestService restService = new RestServiceImpl();

        restService.get(null, "", call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {
                srl_tradingFloor.setRefreshing(false);
                VirtualListItemBean virtualListItemBean =(VirtualListItemBean) response.body();
                if(virtualListItemBean.getErrorCode().equals("0")){

                    list = new ArrayList<VirtualTradingBean>();
                    for (int i = 0; i < virtualListItemBean.getItems().size(); i++) {
                        VirtualTradingBean v = virtualListItemBean.getItems().get(i);
                        list.add(v);
                    }

                    tradingFloorAdapter = new HomePageAdapter(getActivity(),list);
                    lv_tradingFloor.setAdapter(tradingFloorAdapter);

                }else {
                    GetToastUtil.getToads(getActivity(),virtualListItemBean.getMessage());
                }
            }

            @Override
            public <T> void onFailure(Call<T> call, Throwable t) {
                srl_tradingFloor.setRefreshing(false);
            }
        });
    }


    public  View initGuanGao(){

        WindowManager wm = getActivity().getWindowManager();

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();


         int  imgeHeight =  width/3;         //300/60

        localImages.add(R.drawable.home_1);
        localImages.add(R.drawable.home_1);
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
    }

}
