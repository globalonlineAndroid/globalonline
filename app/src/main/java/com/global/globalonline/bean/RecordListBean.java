package com.global.globalonline.bean;

/**
 * Created by lijl on 16/6/14.
 */
public class RecordListBean {

    private  String    id;// 挂单id
    private  String    time;//挂单时间
    private  String    tradetype;//BUY,SELL交易类型
    private  String    number;//挂单量
    private  String        price;//挂单价格
    private  String        fee;//手续费费率
    private  String        volume;//已成交量
    private  String       dealmoney;//已成交金额
    private  String       status;//状态，0：（正常）-1：（已取消）

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTradetype() {
        return tradetype;
    }

    public void setTradetype(String tradetype) {
        this.tradetype = tradetype;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        price = price;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        volume = volume;
    }

    public String getDealmoney() {
        return dealmoney;
    }

    public void setDealmoney(String dealmoney) {
        this.dealmoney = dealmoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
