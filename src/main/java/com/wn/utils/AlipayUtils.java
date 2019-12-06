package com.wn.utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.google.gson.Gson;
import com.wn.pojo.Pay;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/12/2.
 */
@Component
public class AlipayUtils {
    public String pay(Pay pay) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl,AlipayConfig.app_id,AlipayConfig.merchant_private_key,"json","utf-8",AlipayConfig.alipay_public_key,"RSA2");
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        /*alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"20150320013333\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":88.88," +
                "    \"subject\":\"Iphone6 16G\"," +
                "    \"body\":\"Iphone6 16G\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088511833207846\"" +
                "    }"+
                "  }");*/
        alipayRequest.setBizContent(new Gson().toJson(pay));


        //这里设置支付后跳转的地址
        alipayRequest.setNotifyUrl("http://localhost:8584/alipay_callback.do");
        alipayRequest.setReturnUrl("http://localhost:8584/alipay_callback.do");
        String form="";
        form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单        System.out.println(response.getBody());
        return form;
    }
}
