package com.global.globalonline.bean;

import java.util.List;

/**
 * Created by kqw on 2016/5/13.
 * CombinedChartEntity
 *
 * K线图
 */
public class CombinedChartEntity {


    public List<List<String>> getK() {
        return k;
    }

    public void setK(List<List<String>> k) {
        this.k = k;
    }

    private List<List<String>> k;

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
                ", data=" + k +
                '}';
    }
}
