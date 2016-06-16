package com.global.globalonline.adapter.HistoricalRecord;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.bean.RMB.chongzhi.RecordListBean;
import com.global.globalonline.tools.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijl on 16/6/5.
 */
public class RMBChongZhiFlowAdapter extends BaseAdapter {

    LayoutInflater layoutInflater = null;
    List<RecordListBean> list = new ArrayList<RecordListBean>();
    Activity activity;


    public RMBChongZhiFlowAdapter(Activity activity , List<RecordListBean> list) {
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
                convertView = layoutInflater.inflate(R.layout.act_item_rmb_chongzhi_flow, null);
                viewHolder = new Mandatory();

                viewHolder.tv_id = (TextView) convertView.findViewById(R.id.tv_id);
                viewHolder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
                viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
                viewHolder.tv_status = (TextView) convertView.findViewById(R.id.tv_status);
                viewHolder.tv_chongzhifangshi = (TextView) convertView.findViewById(R.id.tv_chongzhifangshi);

                convertView.setTag(viewHolder);

        }else {
            viewHolder = (Mandatory)convertView.getTag();
        }
        RecordListBean recordListBean = list.get(position);
        viewHolder.tv_id.setText(recordListBean.getId());
        viewHolder.tv_date.setText(DateUtils.getDateString(recordListBean.getCreate_time()));
        viewHolder.tv_price.setText(recordListBean.getMoney());
        viewHolder.tv_status.setText(recordListBean.getStatu());
        viewHolder.tv_chongzhifangshi.setText(recordListBean.getBankname());

        return convertView;
    }



    /*存放控件 的ViewHolder*/
    public final class Mandatory {

        TextView tv_id;
        TextView tv_date;
        TextView tv_price;
        TextView tv_status;
        TextView tv_chongzhifangshi;

    }
}
