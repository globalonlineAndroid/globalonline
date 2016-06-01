package com.global.globalonline.bean;

/**
 * Created by lijl on 16/6/2.
 */
public class CoinsDetailItemBean {

    private  String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String price;
    private String quantity;

    public String getPrice() {

        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
