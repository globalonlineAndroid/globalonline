package com.global.globalonline.adapter.HistoricalRecord;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.bean.RecordListBean;
import com.global.globalonline.tools.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijl on 16/6/5.
 */
public class HisWeiTuoFlowAdapter extends BaseAdapter {

    LayoutInflater layoutInflater = null;
    List<RecordListBean> list = new ArrayList<RecordListBean>();
    Activity activity;



    public HisWeiTuoFlowAdapter(Activity activity , List<RecordListBean> list) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        Mandatory  viewHolder = null;

        if(convertView == null){

             convertView = layoutInflater.inflate(R.layout.act_item_his_weituo_flow, null);
             viewHolder = new Mandatory();


             viewHolder.tv_status = (TextView) convertView.findViewById(R.id.tv_status);
             viewHolder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
             viewHolder.tv_type = (TextView) convertView.findViewById(R.id.tv_type);
             viewHolder.tv_number = (TextView) convertView.findViewById(R.id.tv_number);
             viewHolder.tv_weituoprice = (TextView) convertView.findViewById(R.id.tv_weituoprice);
             viewHolder.tv_shouxuprice = (TextView) convertView.findViewById(R.id.tv_shouxuprice);
             viewHolder.tv_weituojiage = (TextView) convertView.findViewById(R.id.tv_weituojiage);
             viewHolder.tv_chengjiaonumber = (TextView) convertView.findViewById(R.id.tv_chengjiaonumber);
             viewHolder.tv_chengjiaojine = (TextView) convertView.findViewById(R.id.tv_chengjiaojine);
             viewHolder.tv_avgprice = (TextView) convertView.findViewById(R.id.tv_avgprice);

             convertView.setTag(viewHolder);

        }else {
            viewHolder = (Mandatory)convertView.getTag();
        }
        final int index = position;
        RecordListBean recordListBean = list.get(position);


        viewHolder.tv_status.setText(recordListBean.getStatus());
        viewHolder.tv_date.setText(DateUtils.getDateString(recordListBean.getTime()));
        viewHolder.tv_type.setText(recordListBean.getTradetype());
        viewHolder.tv_number.setText(recordListBean.getNumber());
        viewHolder.tv_date.setText(recordListBean.getTime());
        float weituoprice = (Float.parseFloat(recordListBean.getNumber())*Float.parseFloat(recordListBean.getPrice()));
        viewHolder.tv_weituoprice.setText(weituoprice+"");
        float feeprice = weituoprice*Float.parseFloat(recordListBean.getFee())/100;
        viewHolder.tv_shouxuprice.setText(feeprice+"");
        viewHolder.tv_weituojiage.setText(recordListBean.getPrice());
        viewHolder.tv_chengjiaonumber.setText(recordListBean.getVolume());
        viewHolder.tv_chengjiaojine.setText(recordListBean.getDealmoney());
        Float avg = Float.parseFloat(recordListBean.getDealmoney())/Float.parseFloat(recordListBean.getNumber());
        viewHolder.tv_avgprice.setText(avg+"");
        return convertView;
    }



    /*存放控件 的ViewHolder*/
    public final class Mandatory {



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
