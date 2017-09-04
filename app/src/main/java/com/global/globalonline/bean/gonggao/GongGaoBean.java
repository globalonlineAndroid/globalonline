package com.global.globalonline.bean.gonggao;

import com.global.globalonline.bean.BaseBean;

import java.util.List;

/**
 * Created by lijl on 2017/9/2.
 */

public class GongGaoBean extends BaseBean {

    List<GongGaoItemBean> items;

    public List<GongGaoItemBean> getItems() {
        return items;
    }

    public void setItems(List<GongGaoItemBean> items) {
        this.items = items;
    }
}
