package com.global.globalonline.bean.xuNiBi;

/**
 * Created by lijl on 16/6/16.
 */
public class CoinsPaycheckRecordItemBean {

    /*      "id": 6,
      "wallet": "TgG8DFrRGxU9dbGGvrYum4NY5H2Patakxa",
      "quantity": 12,
      "time": 1466087625,
      "fee": "0.1000",
      "status_name": "充值成功"*/


       private String time;
       private String quantity;
       private String status_name;
       private String id;
       private String wallet;
       private String fee;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }
}
