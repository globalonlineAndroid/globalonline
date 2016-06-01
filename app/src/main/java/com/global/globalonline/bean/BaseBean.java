package com.global.globalonline.bean;

/**
 * Created by lijl on 16/5/26.
 */
public class BaseBean {

    private   String ErrorCode ;
    private   String message ;

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
