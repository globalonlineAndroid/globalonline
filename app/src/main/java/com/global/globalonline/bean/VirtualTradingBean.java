package com.global.globalonline.bean;

/**
 * Created by lijl on 16/6/1.
 *
 * 用于首页，交易大厅的列表展示
 */
public class VirtualTradingBean {

    private String   id;
    private String   name;//虚拟币名称”
    private String   ename;//虚拟币英文名称”
    private String   img;//币种的小图标地址”
    private String   price;//当前价格”
    private String   open_price;//开盘价”
    private String   max_price;//最高价”
    private String   min_price;//最低价
    private String   close_price;//收盘价”,
    private String   volume;//成交量”，
    private String   turnover;//成交额”,

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOpen_price() {
        return open_price;
    }

    public void setOpen_price(String open_price) {
        this.open_price = open_price;
    }

    public String getMax_price() {
        return max_price;
    }

    public void setMax_price(String max_price) {
        this.max_price = max_price;
    }

    public String getMin_price() {
        return min_price;
    }

    public void setMin_price(String min_price) {
        this.min_price = min_price;
    }

    public String getClose_price() {
        return close_price;
    }

    public void setClose_price(String close_price) {
        this.close_price = close_price;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getTurnover() {
        return turnover;
    }

    public void setTurnover(String turnover) {
        this.turnover = turnover;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    private String   ratio;//涨跌幅计算方法：（收盘-开盘）/开盘”
}
