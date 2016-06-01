package com.global.globalonline.activities.mainTab;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.global.globalonline.R;
import com.global.globalonline.activities.virtualCurrency.VirtualTradeActivity_;
import com.global.globalonline.adapter.mainTab.HomePageAdapter;
import com.global.globalonline.bean.VirtualCurrencyBean;
import com.global.globalonline.bean.VirtualListItemBean;
import com.global.globalonline.service.CallBackService;
import com.global.globalonline.service.GetRetrofitService;
import com.global.globalonline.service.RestService;
import com.global.globalonline.service.serviceImpl.RestServiceImpl;
import com.global.globalonline.service.virtualTrading.VirtualService;
import com.global.globalonline.tools.GetToastUtil;
import com.global.globalonline.tools.MapToParams;
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
    SwipeRefreshLayout srl_tradingFloor;
    @ViewById
    ListView lv_tradingFloor;

    private ArrayList<Integer> localImages = new ArrayList<Integer>();


    HomePageAdapter tradingFloorAdapter;
    private static final int REFRESH_COMPLETE = 0X110;

    List<VirtualCurrencyBean> list = null;
    VirtualService virtualService;

    private String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
    };

    @AfterViews()
    void init(){
        virtualService = GetRetrofitService.getRestClient(VirtualService.class);

        list = new ArrayList<VirtualCurrencyBean>();
        for (int i = 0; i < 10; i++) {
            VirtualCurrencyBean v = new VirtualCurrencyBean();
            v.setCount(String.valueOf(10+i));
            list.add(v);

        }


        lv_tradingFloor.addHeaderView(initGuanGao());
        srl_tradingFloor.setColorSchemeResources(R.color.springgreen, R.color.forestgreen, R.color.goldenrod,
                R.color.indianred,R.color.maroon);
        srl_tradingFloor.setOnRefreshListener(this);
        tradingFloorAdapter = new HomePageAdapter(getActivity(),list);
        lv_tradingFloor.setAdapter(tradingFloorAdapter);
        lv_tradingFloor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VirtualTradeActivity_.intent(getActivity()).start();
            }
        });

    }


    @Override
    public void onRefresh() {


        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("next_id", "0");
        stringMap.put("perpage", "20");
        stringMap.put("orderby", "1");

        stringMap = MapToParams.getParsMap(stringMap);

        Call<VirtualListItemBean> call = virtualService.index(stringMap);
        RestService restService = new RestServiceImpl();

        restService.get(null, "", call, new CallBackService() {
            @Override
            public <T> void onResponse(Call<T> call, Response<T> response) {
                srl_tradingFloor.setRefreshing(false);
                VirtualListItemBean virtualListItemBean =(VirtualListItemBean) response.body();
                if(virtualListItemBean.getErrorCode().equals("0")){

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
        localImages.add(R.drawable.page_1);
        localImages.add(R.drawable.page_1);
        localImages.add(R.drawable.page_1);

        ConvenientBanner mConvenientBanner = new ConvenientBanner(getActivity());
        mConvenientBanner.setMinimumHeight(500);
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
