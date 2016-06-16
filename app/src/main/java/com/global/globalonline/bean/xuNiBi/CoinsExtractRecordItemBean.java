package com.global.globalonline.bean.xuNiBi;

/**
 * Created by lijl on 16/6/17.
 * 转出虚拟币记录子表
 */
public class CoinsExtractRecordItemBean  {

    private String id;
    private String extract_address;
    private String quantity;
    private String time;
    private String procedures;
    private String status_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExtract_address() {
        return extract_address;
    }

    public void setExtract_address(String extract_address) {
        this.extract_address = extract_address;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProcedures() {
        return procedures;
    }

    public void setProcedures(String procedures) {
        this.procedures = procedures;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }
}
