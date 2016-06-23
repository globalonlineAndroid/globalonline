package com.global.globalonline.adapter.HistoricalRecord;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.bean.xuNiBi.CoinsTradeRecordItemBean;
import com.global.globalonline.tools.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijl on 16/6/5.
 */
public class VirtualDealFlowAdapter extends BaseAdapter {

    LayoutInflater layoutInflater = null;
    List<CoinsTradeRecordItemBean> list = new ArrayList<CoinsTradeRecordItemBean>();
    Activity activity;
    String type = "";


    public VirtualDealFlowAdapter(Activity activity , List<CoinsTradeRecordItemBean> list,String type) {
        layoutInflater = LayoutInflater.from(activity);
        this.list = list;
        this.activity = activity;
        this.type = type;

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
                convertView = layoutInflater.inflate(R.layout.act_item_virtual_deal_flow, null);
                viewHolder = new Mandatory();

                viewHolder.tv_type = (TextView) convertView.findViewById(R.id.tv_type);
                viewHolder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
                viewHolder.tv_rmbNumber = (TextView) convertView.findViewById(R.id.tv_rmbNumber);
                viewHolder.tv_xunibiNumber = (TextView) convertView.findViewById(R.id.tv_xunibiNumber);
                viewHolder.tv_status = (TextView) convertView.findViewById(R.id.tv_status);
                viewHolder.tv_avgprice = (TextView) convertView.findViewById(R.id.tv_avgprice);
                viewHolder.tv_feel = (TextView) convertView.findViewById(R.id.tv_feel);

                convertView.setTag(viewHolder);

        }else {
            viewHolder = (Mandatory)convertView.getTag();
        }


        CoinsTradeRecordItemBean co = list.get(position);
        viewHolder.tv_type.setText(type);
        viewHolder.tv_date.setText(DateUtils.getDateString(co.getTime()));
        viewHolder.tv_rmbNumber.setText(co.getDealmoney() == null ? co.getMoney():co.getDealmoney());
        viewHolder.tv_xunibiNumber.setText(co.getVolume());
        viewHolder.tv_status.setText(activity.getResources().getString(R.string.act_base_succ));
        viewHolder.tv_status.setTextColor(activity.getResources().getColor(R.color.green));
        viewHolder.tv_avgprice.setText("¥"+co.getPrice());
        viewHolder.tv_feel.setText("¥"+co.getFee());

        return convertView;
    }



    /*存放控件 的ViewHolder*/
    public final class Mandatory {

        TextView tv_type;
        TextView tv_date;
        TextView tv_rmbNumber;
        TextView tv_xunibiNumber;
        TextView tv_status;
        TextView tv_avgprice;
        TextView tv_feel;

    }
}
