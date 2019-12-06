package com.wn.pojo;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Administrator on 2019/12/2.
 */
@Data
public class Pay {
    private String out_trade_no;//商户订单号,64个字符以内、可包含字母、数字、下划线；需保证在商户端不重复
    private String product_code="FAST_INSTANT_TRADE_PAY";//销售产品码，与支付宝签约的产品码名称。  注：目前仅支持FAST_INSTANT_TRADE_PAY
    private String total_amount;//订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]。
    private String subject;//订单标题
    private String body;//订单描述




}


