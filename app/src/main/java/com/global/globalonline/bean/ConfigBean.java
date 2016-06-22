package com.global.globalonline.bean;

/**
 * Created by lijl on 16/5/27.
 */
public class ConfigBean extends BaseBean {




    private String data_md5;

    private ConfigsBean config;

    public ConfigsBean getConfig() {
        return config;
    }

    public void setConfig(ConfigsBean config) {
        this.config = config;
    }

    public String getData_md5() {
        return data_md5;
    }

    public void setData_md5(String data_md5) {
        this.data_md5 = data_md5;
    }
}
