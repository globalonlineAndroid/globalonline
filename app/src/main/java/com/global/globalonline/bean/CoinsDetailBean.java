package com.global.globalonline.bean;

import java.util.List;

/**
 * Created by lijl on 16/6/2.
 * 虚拟币交易
 */
public class CoinsDetailBean extends BaseBean{

    private String   id;
    private String   name;//虚拟币名称”
    private String   ename;//虚拟币英文名称”
    private String   img;//币种的小图标地址”
    private String   price;//一个币换多少人民币”
    private String   max_price;//最高价”
    private String   min_price;//最低价
    private String   close_price;//收盘价”,
    private String   volume;//成交量”，
    private String   turnover;//成交额”,

    private String frozen;//冻结币种数量
    private String ratio;//涨跌比例-负数显示绿色，正数显示红色”,
    private String account_balance;//当前用户可用余额”

    List<CoinsDetailItemBean>  buy_list ; ///买入前五条
    List<CoinsDetailItemBean>  sell_list;//卖出前五条

    public List<CoinsDetailItemBean> getSell_list() {
        return sell_list;
    }

    public void setSell_list(List<CoinsDetailItemBean> sell_list) {
        this.sell_list = sell_list;
    }

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

    public String getFrozen() {
        return frozen;
    }

    public void setFrozen(String frozen) {
        this.frozen = frozen;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(String account_balance) {
        this.account_balance = account_balance;
    }

    public List<CoinsDetailItemBean> getBuy_list() {
        return buy_list;
    }

    public void setBuy_list(List<CoinsDetailItemBean> buy_list) {
        this.buy_list = buy_list;
    }


}
