package com.global.globalonline.bean;

import java.util.List;

/**
 * Created by lijl on 16/6/2.
 */
public class VirtualListItemBean extends  BaseBean{

    private  String next_id;
    private List<VirtualTradingBean>  items  ;

    public String getNext_id() {
        return next_id;
    }

    public void setNext_id(String next_id) {
        this.next_id = next_id;
    }

    public List<VirtualTradingBean> getItems() {
        return items;
    }

    public void setItems(List<VirtualTradingBean> items) {
        this.items = items;
    }


}
