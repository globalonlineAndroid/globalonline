package com.global.globalonline.adapter.HistoricalRecord;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.bean.xuNiBi.CoinsPaycheckRecordItemBean;
import com.global.globalonline.tools.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijl on 16/6/5.
 */
public class ZhuanRuVirtualFlowAdapter extends BaseAdapter {

    LayoutInflater layoutInflater = null;
    List<CoinsPaycheckRecordItemBean> list = new ArrayList<CoinsPaycheckRecordItemBean>();
    Activity activity;


    public ZhuanRuVirtualFlowAdapter(Activity activity , List<CoinsPaycheckRecordItemBean> list) {
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
                convertView = layoutInflater.inflate(R.layout.act_item_zhuanru_xunibi_flow, null);
                viewHolder = new Mandatory();

                viewHolder.tv_dizhi = (TextView) convertView.findViewById(R.id.tv_dizhi);
                viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
                viewHolder.tv_updatedate = (TextView) convertView.findViewById(R.id.tv_updatedate);
                viewHolder.tv_status = (TextView) convertView.findViewById(R.id.tv_status);

                convertView.setTag(viewHolder);

        }else {
            viewHolder = (Mandatory)convertView.getTag();
        }
        CoinsPaycheckRecordItemBean coin = list.get(position);
        viewHolder.tv_dizhi.setText(coin.getWallet());
        viewHolder.tv_price.setText(coin.getQuantity());
        viewHolder.tv_updatedate.setText(DateUtils.getDateString(coin.getTime()));
        viewHolder.tv_status.setText(coin.getStatus_name());

        return convertView;
    }



    /*存放控件 的ViewHolder*/
    public final class Mandatory {

        TextView tv_dizhi;
        TextView tv_price;
        TextView tv_updatedate;
        TextView tv_status;

    }
}
