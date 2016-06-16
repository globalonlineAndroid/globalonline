package com.global.globalonline.bean;

/**
 * Created by lijl on 16/6/16.
 * 虚拟币充值接口
 */
public class CoinsPaycheckBean extends BaseBean{
    private  String wallet;
    private  String amount;
    private  String frozen;
    private  String timestamp;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFrozen() {
        return frozen;
    }

    public void setFrozen(String frozen) {
        this.frozen = frozen;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }
}
