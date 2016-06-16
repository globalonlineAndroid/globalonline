package com.global.globalonline.adapter.select;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.global.globalonline.R;
import com.global.globalonline.base.UrlApi;
import com.global.globalonline.bean.VirtualcoinBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijl on 16/5/25.
 */
public class SelectVirtualdapter extends BaseAdapter{

    LayoutInflater layoutInflater = null;
    List<VirtualcoinBean>  list = new ArrayList<VirtualcoinBean>();
    Activity activity;


    public SelectVirtualdapter( Activity activity , List<VirtualcoinBean> list) {
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
        ViewHolder  viewHolder = null;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.act_def_select_virtual,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_ico = (ImageView)convertView.findViewById(R.id.iv_ico) ;
            viewHolder.tv_name = (TextView)convertView.findViewById(R.id.tv_name) ;
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.tv_name.setText(list.get(position).getName()+"("+list.get(position).getEname()+")");

        Glide.with(activity)
                .load(UrlApi.baseImageUrl+list.get(position).getLogo())
                .into(viewHolder.iv_ico);

        return convertView;
    }



    /*存放控件 的ViewHolder*/
    public final class ViewHolder {

        ImageView iv_ico;
        TextView tv_name;

    }
}
