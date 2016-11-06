package com.global.globalonline.bean;

/**
 * Created by lijl on 2016/11/4.
 */
public class AppBean  extends BaseBean{

    private String latest_version;
    private String url;
    private String size;
    private String must;
    private String app_version;
    private String download;
    private String app_type;

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getApp_type() {
        return app_type;
    }

    public void setApp_type(String app_type) {
        this.app_type = app_type;
    }

    public String getLatest_version() {
        return latest_version;
    }

    public void setLatest_version(String latest_version) {
        this.latest_version = latest_version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMust() {
        return must;
    }

    public void setMust(String must) {
        this.must = must;
    }
}
