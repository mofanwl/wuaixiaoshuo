package com.wn.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wn.pojo.OrderPay;
import com.wn.service.OrderPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2019/12/6.
 */
@Controller
@RequestMapping("/order")
public class OrderPayController {
    @Autowired
    private OrderPayService orderPayService;
    /*
    后台订单的查询
     */
    @RequestMapping("/selAdminListByOrder")
    @ResponseBody
    public PageInfo selAdminListByOrder(){

        PageHelper.startPage(1,2);
        List<OrderPay> orderPays = orderPayService.selAdminListOrderPay();
        PageInfo pageInfo = new PageInfo<>(orderPays);
        long total = pageInfo.getTotal();
        System.out.println(total);
        return pageInfo;
    }



    /*
    用户订单的查询
     */
}
