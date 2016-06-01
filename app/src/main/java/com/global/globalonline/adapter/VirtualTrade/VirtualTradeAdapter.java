package com.global.globalonline.adapter.VirtualTrade;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.bean.CoinsDetailItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijl on 16/5/25.
 */
public class VirtualTradeAdapter extends BaseAdapter{

    LayoutInflater layoutInflater = null;
    List<CoinsDetailItemBean>  list = new ArrayList<CoinsDetailItemBean>();
    Activity activity;
    int i ;

    public VirtualTradeAdapter(int i, Activity activity , List<CoinsDetailItemBean> list) {
        layoutInflater = LayoutInflater.from(activity);
        this.list = list;
        this.activity = activity;
        this.i = i;
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
            convertView = layoutInflater.inflate(R.layout.act_item_virtualtrading,null);
            viewHolder = new ViewHolder();
            viewHolder.tv_maimai = (TextView)convertView.findViewById(R.id.tv_maimai) ;
            viewHolder.tv_jiage = (TextView)convertView.findViewById(R.id.tv_jiage) ;
            viewHolder.tv_number = (TextView)convertView.findViewById(R.id.tv_number) ;


            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.tv_maimai.setText(list.get(position).getName());
        viewHolder.tv_jiage.setText(list.get(position).getPrice());
        viewHolder.tv_number.setText(list.get(position).getQuantity());
        viewHolder.tv_maimai.setTextColor(activity.getResources().getColor(i));
        viewHolder.tv_jiage.setTextColor(activity.getResources().getColor(i));
        viewHolder.tv_number.setTextColor(activity.getResources().getColor(i));
        return convertView;
    }



    /*存放控件 的ViewHolder*/
    public final class ViewHolder {

        TextView tv_maimai;
        TextView tv_jiage;
        TextView tv_number;
    }
}
