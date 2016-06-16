package com.global.globalonline.bean;

import java.util.List;

/**
 * Created by lijl on 16/6/14.
 * 某币种成交&委托记录
 */
public class CoinsEntrustRecordBena extends BaseBean{

    private  String    next_id;//
    List<RecordListBean>  record_list;

    public List<com.global.globalonline.bean.RecordListBean> getRecord_list() {
        return record_list;
    }

    public void setRecord_list(List<com.global.globalonline.bean.RecordListBean> record_list) {
        this.record_list = record_list;
    }

    public String getNext_id() {

        return next_id;
    }

    public void setNext_id(String next_id) {
        this.next_id = next_id;
    }


}

