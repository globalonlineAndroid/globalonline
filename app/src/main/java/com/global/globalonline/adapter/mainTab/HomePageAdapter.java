package com.global.globalonline.adapter.mainTab;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.global.globalonline.R;
import com.global.globalonline.base.GetConfiguration;
import com.global.globalonline.base.UrlApi;
import com.global.globalonline.bean.VirtualTradingBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijl on 16/5/26.
 */
public class HomePageAdapter extends BaseAdapter {
    LayoutInflater layoutInflater = null;
    List<VirtualTradingBean> list = new ArrayList<VirtualTradingBean>();
    Activity act;
    public HomePageAdapter(FragmentActivity activity , List<VirtualTradingBean> list) {
        layoutInflater = LayoutInflater.from(activity);
        this.list = list;
        this.act = activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder  viewHolder = null;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.act_item_tradingfloor,null);
            viewHolder = new ViewHolder();
            viewHolder.tv_image = (ImageView)convertView.findViewById(R.id.tv_image) ;
            viewHolder.tv_xunibiName = (TextView)convertView.findViewById(R.id.tv_xunibiName) ;
            viewHolder.tv_price = (TextView)convertView.findViewById(R.id.tv_price) ;
            viewHolder.tv_zhangfu = (TextView)convertView.findViewById(R.id.tv_zhangfu) ;
            viewHolder.tv_chengjiaoe = (TextView)convertView.findViewById(R.id.tv_chengjiaoe) ;
            viewHolder.tv_chengjiaoliang = (TextView)convertView.findViewById(R.id.tv_chengjiaoliang) ;
            viewHolder.tv_minprice = (TextView)convertView.findViewById(R.id.tv_minprice) ;
            viewHolder.tv_mxPrice = (TextView)convertView.findViewById(R.id.tv_mxPrice) ;
            viewHolder.ll_zhangfu = (LinearLayout) convertView.findViewById(R.id.ll_zhangfu) ;


            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        VirtualTradingBean virtualTrading = list.get(position);
        viewHolder.tv_xunibiName.setText(GetConfiguration.LANGUAGE.equals(GetConfiguration.ZH)?virtualTrading.getName():virtualTrading.getEname());
        viewHolder.tv_price.setText(virtualTrading.getPrice());

        float zhangfu = Float.parseFloat(virtualTrading.getRatio());
        String str = "";
        if(zhangfu >= 0){
            str = "+"+virtualTrading.getRatio()+"%";
            viewHolder.ll_zhangfu.setBackgroundResource(R.color.red);
        }else {
            str = virtualTrading.getRatio()+"%";
            viewHolder.ll_zhangfu.setBackgroundResource(R.color.green);
        }
        viewHolder.tv_zhangfu.setText(str);
        viewHolder.tv_chengjiaoe.setText(virtualTrading.getTurnover());
        viewHolder.tv_chengjiaoliang.setText(virtualTrading.getVolume());
        viewHolder.tv_minprice.setText(virtualTrading.getMin_price());
        viewHolder.tv_mxPrice.setText(virtualTrading.getMax_price());
       /* if(virtualTrading.getRatio().indexOf("-") > 0){

        }else {

        }*/
        Glide.with(act)
                .load(UrlApi.baseImageUrl+virtualTrading.getImg())
                .into(viewHolder.tv_image);

        return convertView;
    }



    /*存放控件 的ViewHolder*/
    public final class ViewHolder {
        ImageView tv_image;
        TextView tv_xunibiName;
        TextView tv_price;
        TextView tv_zhangfu;
        TextView tv_chengjiaoe;
        TextView tv_chengjiaoliang;
        TextView tv_minprice;
        TextView tv_mxPrice;
        LinearLayout ll_zhangfu;
    }
}
