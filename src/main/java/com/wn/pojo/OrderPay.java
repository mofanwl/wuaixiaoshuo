package com.wn.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2019/12/4.
 */
@Data
public class OrderPay {
    private Integer order_id;
    private Integer user_id;
    private Integer pay_id;
    private String out_trade_no;//订单号
    private String out_trade_no_1;//订单号2
    private String total_amount;//金额
    private String total_amount_1;//金额2
    private String pay_state;//状态
    private String subject;//商品名

    private Date pay_time;
    private Date pay_time_1;
}
