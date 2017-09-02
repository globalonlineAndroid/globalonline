package com.global.globalonline.bean.gonggao;

import com.global.globalonline.bean.BaseBean;

import java.util.List;

/**
 * Created by lijl on 2017/9/2.
 */

public class GongGaoBean extends BaseBean {

    List<GongGaoItemBean> gongGaoItemBeen;

    public List<GongGaoItemBean> getGongGaoItemBeen() {
        return gongGaoItemBeen;
    }

    public void setGongGaoItemBeen(List<GongGaoItemBean> gongGaoItemBeen) {
        this.gongGaoItemBeen = gongGaoItemBeen;
    }
}
