package com.global.globalonline.adapter.HistoricalRecord;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.base.StaticBase;
import com.global.globalonline.bean.RMB.tixian.RecordListBean;
import com.global.globalonline.tools.DateUtils;
import com.global.globalonline.tools.GetSelectBouncedUtil;
import com.global.globalonline.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijl on 16/6/5.
 */
public class TiXianFlowAdapter extends BaseAdapter {

    LayoutInflater layoutInflater = null;
    List<RecordListBean> list = new ArrayList<RecordListBean>();
    Activity activity;


    public TiXianFlowAdapter(Activity activity , List<RecordListBean> list) {
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
                convertView = layoutInflater.inflate(R.layout.act_item_tixian_flow, null);
                viewHolder = new Mandatory();

                viewHolder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
                viewHolder.tv_zhanghu = (TextView) convertView.findViewById(R.id.tv_zhanghu);
                viewHolder.tv_cartype = (TextView) convertView.findViewById(R.id.tv_cartype);
                viewHolder.tv_carnumber = (TextView) convertView.findViewById(R.id.tv_carnumber);
                viewHolder.tv_tixianprice = (TextView) convertView.findViewById(R.id.tv_tixianprice);
                viewHolder.tv_feel = (TextView) convertView.findViewById(R.id.tv_feel);
                viewHolder.tv_status = (TextView) convertView.findViewById(R.id.tv_status);

                convertView.setTag(viewHolder);

        }else {
            viewHolder = (Mandatory)convertView.getTag();
        }

        RecordListBean recordListBean = list.get(position);
        viewHolder.tv_date.setText(DateUtils.getDateString(recordListBean.getCreate_time()));
        viewHolder.tv_zhanghu.setText(recordListBean.getName());
        viewHolder.tv_cartype.setText(GetSelectBouncedUtil.getBankName(activity, StaticBase.BANK,recordListBean.getBank()));
        viewHolder.tv_carnumber.setText(StringUtil.getCarNumberForment(recordListBean.getBank_card()));
        viewHolder.tv_tixianprice.setText(recordListBean.getMoney());
        viewHolder.tv_feel.setText(recordListBean.getFee());
        viewHolder.tv_status.setText(recordListBean.getState());

        return convertView;
    }



    /*存放控件 的ViewHolder*/
    public final class Mandatory {

        TextView tv_date;
        TextView tv_zhanghu;
        TextView tv_cartype;
        TextView tv_carnumber;
        TextView tv_tixianprice;
        TextView tv_feel;
        TextView tv_status;

    }
}
