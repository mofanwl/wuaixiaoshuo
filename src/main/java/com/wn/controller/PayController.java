package com.wn.controller;

import com.alipay.api.AlipayApiException;
import com.wn.pojo.OrderPay;
import com.wn.pojo.Pay;
import com.wn.pojo.User;
import com.wn.pojo.gbook;
import com.wn.service.OrderPayService;
import com.wn.utils.AlipayUtils;
import com.wn.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019/12/2.
 */
@RestController
public class PayController {
    @Autowired
    private AlipayUtils alipayUtils;
    @Autowired
    private OrderPayService orderPayService;

    /*
    用户订单查询
     */
    @RequestMapping("/selUserByPay")
    @ResponseBody
    public List<OrderPay> selUserByPay(@RequestParam("id")String id){
        List<OrderPay> orderPays = orderPayService.selUserByPay(id);
        return orderPays;
    }

    /*
    支付
     */
    @RequestMapping("/pay/{user_id}/{total_amount}")
    public String pay(@PathVariable("user_id")Integer user_id,@PathVariable("total_amount")String total_amount){
        String pay="";
        total_amount=total_amount+".00";
        System.out.println(total_amount+"充值金额");
        System.out.println(user_id+"user_id");
        try {
            Pay pays=new Pay();
            String out_trade_no= Tools.getOrderNo();
            //total_amount=total_amount;
            String Subject="吾爱币充值："+total_amount+"元";
            String body="y这个是你买的华为Mate30";
            System.out.println("生成的订单号："+out_trade_no);
            pays.setOut_trade_no(out_trade_no);
           // pays.setOut_trade_no("20150320013333");
            pays.setTotal_amount(total_amount);
            pays.setSubject(Subject);
            pays.setBody(body);
            //开始支付

            OrderPay orderPay = new OrderPay();
            orderPay.setUser_id(user_id);
            orderPay.setOut_trade_no(out_trade_no);
            orderPay.setTotal_amount(total_amount);
            orderPay.setSubject(Subject);
            orderPay.setPay_state("支付失败");
            orderPay.setPay_time(new Date());
            //写入订单信息到数据库
            int i = orderPayService.insertPay(orderPay);
            if(i==1){
                System.out.println("订单信息写入成功");
                //查询pay_id
                OrderPay orderPay1 = orderPayService.selectByTrade(out_trade_no);
                orderPay1.setUser_id(orderPay.getUser_id());
                if(orderPay1!=null){
                    System.out.println("订单id查询成功");
                    //写入关系数据
                    int i1 = orderPayService.insertOrderAndPay(orderPay1);
                    if(i1==1){
                        System.out.println("写入关系数据成功");
                        //发起支付
                        pay = alipayUtils.pay(pays);

                    }else{
                        System.out.println("写入关系数据失败");
                    }
                }else{
                }
            }else{
                System.out.println("订单信息写入失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        System.out.println(pay);
        return pay;
    }
}
