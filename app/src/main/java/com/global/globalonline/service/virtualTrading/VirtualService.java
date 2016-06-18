package com.global.globalonline.service.virtualTrading;

import com.global.globalonline.bean.BaseBean;
import com.global.globalonline.bean.CoinsDetailBean;
import com.global.globalonline.bean.CoinsEntrustRecordBena;
import com.global.globalonline.bean.CoinsPaycheckBean;
import com.global.globalonline.bean.VirtualListItemBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by lijl on 16/6/2.
 */
public interface VirtualService {


    @GET("index.json")  //获取币种最新行情数据
    Call<VirtualListItemBean> index(@QueryMap Map<String,String> map);

    @GET("coins_detail.json")  //某币详情及最新买卖价格（未成交）
    Call<CoinsDetailBean> coins_detail(@QueryMap Map<String,String> map);
    @GET("purchase.json")  //某币买入接口
    Call<BaseBean> purchase(@QueryMap Map<String,String> map);
    @GET("sellout.json")  //某币卖出接口
    Call<BaseBean> sellout(@QueryMap Map<String,String> map);
    @GET("coins_entrust_record.json")  //某币种成交&委托记录
    Call<CoinsEntrustRecordBena> coins_entrust_record(@QueryMap Map<String,String> map);
    @GET("cancel_entrust.json")  //某币种成交&委托记录-->撤销
    Call<BaseBean> cancel_entrust(@QueryMap Map<String,String> map);

    @GET("coins_paycheck.json")  //转入虚拟币
    Call<CoinsPaycheckBean> coins_paycheck(@QueryMap Map<String,String> map);
    @GET("coins_extract.json")  //转出虚拟币提交接
    Call<BaseBean> coins_extract(@QueryMap Map<String,String> map);
    @GET("collect_money.json")  //付款
    Call<BaseBean> collect_money(@QueryMap Map<String,String> map);


}
