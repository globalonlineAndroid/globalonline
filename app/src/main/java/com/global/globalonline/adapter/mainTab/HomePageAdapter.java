package com.global.globalonline.adapter.mainTab;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.bean.VirtualCurrencyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijl on 16/5/26.
 */
public class HomePageAdapter extends BaseAdapter {
    LayoutInflater layoutInflater = null;
    List<VirtualCurrencyBean> list = new ArrayList<VirtualCurrencyBean>();

    public HomePageAdapter(FragmentActivity activity , List<VirtualCurrencyBean> list) {
        layoutInflater = LayoutInflater.from(activity);
        this.list = list;
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
            viewHolder.tv_chengjiaoliang = (TextView)convertView.findViewById(R.id.tv_chengjiaoliang) ;


            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.tv_chengjiaoliang.setText(list.get(position).getCount());
        return convertView;
    }



    /*存放控件 的ViewHolder*/
    public final class ViewHolder {

        TextView tv_chengjiaoliang;
    }
}
