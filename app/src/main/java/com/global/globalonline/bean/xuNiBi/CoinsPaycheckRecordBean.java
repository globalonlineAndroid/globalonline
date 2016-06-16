package com.global.globalonline.bean.xuNiBi;

import com.global.globalonline.bean.BaseBean;

import java.util.List;

/**
 * Created by lijl on 16/6/16.
 * 虚拟币充值记录
 */
public class CoinsPaycheckRecordBean extends BaseBean{
        List<CoinsPaycheckRecordItemBean> record_list;

    public List<CoinsPaycheckRecordItemBean> getRecord_list() {
        return record_list;
    }

    public void setRecord_list(List<CoinsPaycheckRecordItemBean> record_list) {
        this.record_list = record_list;
    }
}





