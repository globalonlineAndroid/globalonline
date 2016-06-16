package com.global.globalonline.activities.select;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.global.globalonline.R;
import com.global.globalonline.activities.HistoricalRecord.VirtualDealFlowActivity;
import com.global.globalonline.activities.virtualCurrency.MandatoryAdministrationActivity;
import com.global.globalonline.adapter.select.SelectVirtualdapter;
import com.global.globalonline.base.BaseActivity;
import com.global.globalonline.base.StaticBase;
import com.global.globalonline.bean.VirtualcoinBean;
import com.global.globalonline.dao.DBHelper;
import com.global.globalonline.db.bean.DataSource;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;


@EActivity(R.layout.activity_select_virtual)
public class SelectVirtualActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @ViewById
    SwipeRefreshLayout srl_trading;
    @ViewById
    ListView lv_trading;



    SelectVirtualdapter  selectVirtualdapter;


    List<VirtualcoinBean> list = null;
    DBHelper dbHelper;


    String type = "";

    @AfterViews()
    void init(){
        dbHelper = DBHelper.getInstance(SelectVirtualActivity.this);
        type = getIntent().getStringExtra("type");
        initlist();


        srl_trading.setColorSchemeResources(StaticBase.colorResIds);
        srl_trading.setOnRefreshListener(this);

        lv_trading.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                VirtualcoinBean virtualcoinBean =(VirtualcoinBean) parent.getItemAtPosition(position);
                //VirtualTradeActivity.toActiviy(getActivity(),virtualTradingBean.getSymbol());
                if(type.equals("weituo")){
                    MandatoryAdministrationActivity.toActivity(SelectVirtualActivity.this, virtualcoinBean.getId());
                }else {
                    VirtualDealFlowActivity.toActivity(SelectVirtualActivity.this, virtualcoinBean.getId());
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
        }else {
            list = new ArrayList<VirtualcoinBean>();
        }

      List<DataSource>  date =  dbHelper.getByModekeyList(StaticBase.VIRTUALOIN);
        for (int i = 0; i < date.size(); i++) {
            VirtualcoinBean v = new VirtualcoinBean();
            v.setId(date.get(i).getId_());
            v.setName(date.get(i).getName());
            v.setEname(date.get(i).getEname());
            v.setLogo(date.get(i).getLogo());
            list.add(v);
        }

        selectVirtualdapter = new SelectVirtualdapter(SelectVirtualActivity.this,list);
        lv_trading.setAdapter(selectVirtualdapter);

        srl_trading.setRefreshing(false);



    }



}
