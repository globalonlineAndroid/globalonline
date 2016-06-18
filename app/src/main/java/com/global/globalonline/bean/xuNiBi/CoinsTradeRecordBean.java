package com.global.globalonline.bean.xuNiBi;

import com.global.globalonline.bean.BaseBean;

import java.util.List;

/**
 * Created by lijl on 16/6/18.
 */
public class CoinsTradeRecordBean extends BaseBean {
    private  String next_id;
    private  String timestamp;
    private List<CoinsTradeRecordItemBean> record_list;

    public String getNext_id() {
        return next_id;
    }

    public void setNext_id(String next_id) {
        this.next_id = next_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<CoinsTradeRecordItemBean> getRecord_list() {
        return record_list;
    }

    public void setRecord_list(List<CoinsTradeRecordItemBean> record_list) {
        this.record_list = record_list;
    }
}
