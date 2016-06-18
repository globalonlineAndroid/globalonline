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
