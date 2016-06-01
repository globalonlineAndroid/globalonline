package com.global.globalonline.bean;

/**
 * Created by lijl on 16/5/21.
 * 虚拟币类
 */
public class VirtualCurrencyBean {

   private   String id ;
   private   String name ;
   private   String zhangfu ;
   private   String maxPrice ;
   private   String minPrice ;
   private   String count ;
   private   String shangzhang ;

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

   public String getZhangfu() {
      return zhangfu;
   }

   public void setZhangfu(String zhangfu) {
      this.zhangfu = zhangfu;
   }

   public String getMaxPrice() {
      return maxPrice;
   }

   public void setMaxPrice(String maxPrice) {
      this.maxPrice = maxPrice;
   }

   public String getMinPrice() {
      return minPrice;
   }

   public void setMinPrice(String minPrice) {
      this.minPrice = minPrice;
   }

   public String getCount() {
      return count;
   }

   public void setCount(String count) {
      this.count = count;
   }

   public String getShangzhang() {
      return shangzhang;
   }

   public void setShangzhang(String shangzhang) {
      this.shangzhang = shangzhang;
   }
}
