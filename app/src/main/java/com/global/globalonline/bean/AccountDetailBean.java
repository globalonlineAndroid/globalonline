package com.global.globalonline.bean;

/**
 * Created by lijl on 16/6/16.
 * 个人账户详情
 */
public class AccountDetailBean extends BaseBean{


    private IdentifyBean identity_info;
    private AccountInfoBean account_detail;

    public AccountInfoBean getAccount_detail() {
        return account_detail;
    }

    public void setAccount_detail(AccountInfoBean account_detail) {
        this.account_detail = account_detail;
    }

    public IdentifyBean getIdentity_info() {
        return identity_info;
    }

    public void setIdentity_info(IdentifyBean identity_info) {
        this.identity_info = identity_info;
    }



}


