package com.global.globalonline.bean;

import java.util.List;

/**
 * Created by lijl on 16/5/28.
 */
public class ConfigsBean {

    private List<CoutryBean> coutry_list;
    private List<IncomebankBean> incomebank_list;
    private List<BankBean> bank_list;
    private List<VirtualcoinBean> virtualcointype;
    private List<CardTypeBean> card_type_list;
    private AlipayBean alipay;
    private String drawcnyfee;
    private String max_double;
    private String min_double;


    public String getDrawcnyfee() {
        return drawcnyfee;
    }

    public void setDrawcnyfee(String drawcnyfee) {
        this.drawcnyfee = drawcnyfee;
    }

    public String getMax_double() {
        return max_double;
    }

    public void setMax_double(String max_double) {
        this.max_double = max_double;
    }

    public String getMin_double() {
        return min_double;
    }

    public void setMin_double(String min_double) {
        this.min_double = min_double;
    }

    public AlipayBean getAlipay() {
        return alipay;
    }

    public void setAlipay(AlipayBean alipay) {
        this.alipay = alipay;
    }

    public List<VirtualcoinBean> getVirtualcointype() {
        return virtualcointype;
    }

    public void setVirtualcointype(List<VirtualcoinBean> virtualcointype) {
        this.virtualcointype = virtualcointype;
    }

    public List<CardTypeBean> getCard_type_list() {
        return card_type_list;
    }

    public void setCard_type_list(List<CardTypeBean> card_type_list) {
        this.card_type_list = card_type_list;
    }

    public List<BankBean> getBank_list() {
        return bank_list;
    }

    public void setBank_list(List<BankBean> bank_list) {
        this.bank_list = bank_list;
    }

    public List<CoutryBean> getCoutry_list() {
        return coutry_list;
    }

    public void setCoutry_list(List<CoutryBean> coutry_list) {
        this.coutry_list = coutry_list;
    }
    public List<IncomebankBean> getIncomebank_list() {
        return incomebank_list;
    }

    public void setIncomebank_list(List<IncomebankBean> incomebank_list) {
        this.incomebank_list = incomebank_list;
    }

}
