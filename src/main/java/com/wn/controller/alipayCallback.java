package com.wn.controller;

import com.wn.pojo.OrderPay;
import com.wn.pojo.User;
import com.wn.service.OrderPayService;
import com.wn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2019/12/4.
 */
@Controller
public class alipayCallback {
    @Autowired
    private OrderPayService orderPayService;
    @Autowired
    private UserService userService;
    //支付宝回调函数
    @RequestMapping("/alipay_callback.do")
    public Object alipayCallback(HttpServletRequest request) {
        System.out.println("alipayCallback-------------------=====================");
        Map<String, String> params= new HashMap();

        Map requestParams = request.getParameterMap();
        System.out.println(requestParams+"这是requestParams==================================");
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for(int i = 0; i<values.length;i++){
                valueStr = (i==values.length-1)?valueStr+values[i]:valueStr+values[i]+",";
            }
            params.put(name,valueStr);
        }
        //检测支付状态
        String out_trade_no_1 = params.get("out_trade_no");
        String total_amount_1 = params.get("total_amount");
        //查询数据库该订单号的金额
        OrderPay orderPay = orderPayService.selectByTrade(out_trade_no_1);
        System.out.println(orderPay+"----------------------");
        if(orderPay.getTotal_amount().equals(total_amount_1)){
            //修改状态
            int res = orderPayService.updateStateAndTotal("支付成功", total_amount_1, out_trade_no_1,new Date());
            if(res==1){
                //System.out.println("数据库支付成功");
                //写入用户余额
                OrderPay orderPay1 = orderPayService.selUserIdByTrade(out_trade_no_1);
                //System.out.println(orderPay1+"这是根据out_trade_no查询 的ouderpay1");
                orderPay1.getUser_id();
                //id查询user
                User user1 = userService.selectUserById(orderPay1.getUser_id());
               // System.out.println("id查询user_id:"+user1+"----------------------------====================");
                User user = new User();
                user.setUser_id(orderPay1.getUser_id());
                user.setUser_total_mount(String.valueOf(Float.valueOf(user1.getUser_total_mount())+Float.valueOf(orderPay1.getTotal_amount_1())));
                int update = userService.update(user);
                if(update==1){
                    return "payok";
                }
            }else {
                //System.out.println("写入支付失败");
            }
        }else {
            //修改状态
            int res = orderPayService.updateStateAndTotal("支付金额不符", total_amount_1, out_trade_no_1,new Date());
            if(res==1){
               // System.out.println("数据库支付成功2");
            }else {
               // System.out.println("写入支付失败2");
            }
        }
        //System.out.println(params+"alipay=============================================================-------------------alipay回调");

       /* logger.info("支付宝回调, sign:{},trade_status:{},参数:{}",params.get("sign"),params.get("trade_status"),params.toString());

        //!!! 验证回调的正确性，是不是支付宝发了，而且还要避免重复通知

        params.remove("sign_type");

        try {
            boolean alipayRSACheckedV2 = AlipaySignature.rsaCheckV2(params, Configs.getAlipayPublicKey(),"utf-8", Configs.getSignType());
            if(!alipayRSACheckedV2){
                return ServerResponse.createByErrorMessage("非法请求，验证不通过");
            }
        } catch (AlipayApiException e) {
            logger.info("支付宝回调异常",e);
        }

        //TODO  验证各种数据

        ServerResponse serverResponse = iOrderService.aliCallback(params);
        if(serverResponse.isSuccess()){
            return Const.AlipayCallback.RESPONSE_SUCCESS;
        }
        return Const.AlipayCallback.RESPONSE_FAILED;*/
        return "payno";
    }

}
