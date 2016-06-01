package com.global.globalonline.bean;

/**
 * Created by lijl on 16/5/26.
 * 验证吗
 */
public class CodeBean extends BaseBean{


  public String getCodeid() {
    return codeid;
  }

  public void setCodeid(String codeid) {
    this.codeid = codeid;
  }

  public String getCodetype() {
    return codetype;
  }

  public void setCodetype(String codetype) {
    this.codetype = codetype;
  }

  private   String         codeid;//验证码ID，
  private   String       codetype;//数据库中保存的验证码类型，用于验证的时候原值传给下一步，
}
