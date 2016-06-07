package com.global.globalonline.bean;

import java.util.List;

/**
 * Created by lijl on 16/6/7.
 */
public class KLineBean {


    public List<List<Long>> getData() {
        return data;
    }

    public void setData(List<List<Long>> data) {
        this.data = data;
    }

    private List<List<Long>> data;

    private  long ErrorCode;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(long errorCode) {
        ErrorCode = errorCode;
    }

    private  long timestamp;




    @Override
    public String toString() {
        return "LKLineDataGson{" +

                ", err_no=" + this.ErrorCode +
                ", data=" + data +
                '}';
    }
}
