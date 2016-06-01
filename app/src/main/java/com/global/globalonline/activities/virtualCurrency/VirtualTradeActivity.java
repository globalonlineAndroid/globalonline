package com.global.globalonline.activities.virtualCurrency;

import android.widget.ListView;

import com.global.globalonline.R;
import com.global.globalonline.adapter.VirtualTrade.VirtualTradeAdapter;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.bean.CoinsDetailBean;
import com.global.globalonline.bean.CoinsDetailItemBean;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_virtual_trade)
public class VirtualTradeActivity extends BaseActivity {

    @ViewById
    ListView lv_mai,lv_sell;

    CoinsDetailBean coinsDetailBean ;
    VirtualTradeAdapter virtualTradeAdapter;

    List<CoinsDetailItemBean> buy_list  = new ArrayList<>(); ;
    List<CoinsDetailItemBean>  sell_list = new ArrayList<>();
    @AfterViews
    void init(){
        coinsDetailBean = new CoinsDetailBean();
        for (int i = 0; i < 5; i++) {
            CoinsDetailItemBean  c= new CoinsDetailItemBean();
            c.setName("买"+(i+1));
            c.setQuantity("1100");
            c.setPrice("0.054");
            buy_list.add(c);

        }
        coinsDetailBean.setBuy_list(buy_list);
        for (int i = 0; i < 5; i++) {
            CoinsDetailItemBean  c= new CoinsDetailItemBean();
            c.setName("卖"+(i+1));
            c.setQuantity("1000");
            c.setPrice("0.055");
            sell_list.add(c);

        }

        coinsDetailBean.setSell_list( sell_list);
        virtualTradeAdapter = new VirtualTradeAdapter(R.color.limegreen,VirtualTradeActivity.this,coinsDetailBean.getBuy_list());
        lv_mai.setAdapter(virtualTradeAdapter);
        virtualTradeAdapter = new VirtualTradeAdapter(R.color.red,VirtualTradeActivity.this,coinsDetailBean.getSell_list());
        lv_sell.setAdapter(virtualTradeAdapter);
    }

}
