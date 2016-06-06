package com.global.globalonline.adapter.VirtualTrade;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.bean.DelegateBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijl on 16/6/5.
 */
public class MandatoryAdministrationAdapter extends BaseAdapter {

    LayoutInflater layoutInflater = null;
    List<DelegateBean> list = new ArrayList<DelegateBean>();
    Activity activity;


    public MandatoryAdministrationAdapter(Activity activity ,  List<DelegateBean> list) {
        layoutInflater = LayoutInflater.from(activity);
        this.list = list;
        this.activity = activity;

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
        Mandatory  viewHolder = null;
        if(convertView == null){
            if(position == 0) {
                convertView = layoutInflater.inflate(R.layout.act_item_top_title, null);
                viewHolder = new Mandatory();

                viewHolder.tv_number = (TextView) convertView.findViewById(R.id.tv_number);

                convertView.setTag(viewHolder);
            }else {

                convertView = layoutInflater.inflate(R.layout.act_item_mandatory_administration, null);
                viewHolder = new Mandatory();

                viewHolder.tv_number = (TextView) convertView.findViewById(R.id.tv_number);

                convertView.setTag(viewHolder);
            }
        }else {
            viewHolder = (Mandatory)convertView.getTag();
        }

        viewHolder.tv_number.setText(position+"");

        return convertView;
    }



    /*存放控件 的ViewHolder*/
    public final class Mandatory {

        TextView tv_buquan;
        TextView tv_quxiao;
        TextView tv_status;
        TextView tv_date;
        TextView tv_type;
        TextView tv_number;
        TextView tv_weituoprice;
        TextView tv_shouxuprice;
        TextView tv_weituojiage;
        TextView tv_chengjiaonumber;
        TextView tv_chengjiaojine;
        TextView tv_avgprice;
    }
}
