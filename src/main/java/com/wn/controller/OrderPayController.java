package com.wn.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wn.pojo.*;
import com.wn.service.OrderPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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



    /*
YC
 */

    private Page p=new Page();
    private  Integer a=p.getPage();
    @RequestMapping("/indexpageC")
    public String indexpageC(Model model){//下一页
        a=a+1;
        if(a>p.getPageTotal())
        {
            a=a-1;
        }
        return "redirect:findLimitC";
    }
    @RequestMapping("/lastpageC")
    public String lastpageC(Model model){//上一页
        a=a-1;
        if(a<1)
        {
            a=a+1;
        }
        return "redirect:findLimitC";
    }
    @RequestMapping("/startpageC")
    public String startpageC(Model model){//首页
        a=1;
        return "redirect:findLimitC";
    }
    @RequestMapping("/endpageC")
    public String endpageC(Model model){//尾页
        a=p.getPageTotal();
        return "redirect:findLimitC";
    }
    @RequestMapping("/findLimitC")
    public String findLimitC(Model model){
        //单例设计，放置登录传过来的账号
        Admin singleton = Single.getSingleton();
        if(singleton.getAdmin_name()==null){
            return "redirect:/admin/login1C";
        }
        model.addAttribute("username",singleton.getAdmin_name());

        //查询总数据
        int total = orderPayService.selectCountC();
        p.setPage(a);
        p.setPageSize(p.getPageSize());
        p.setTotal(total);
        int b=(p.getPage()-1)*p.getPageSize();
        p.setPageStart(b);
        //总页数
        int c=(p.getTotal()/p.getPageSize())+1;
        p.setPageTotal(c);
        model.addAttribute("P",p);

        //查询所有数据
        List<OrderPay> orderPays = orderPayService.selectLimitC(p);

        model.addAttribute("pays",orderPays);


        Integer[] id=new Integer[orderPays.size()];
        for(int i=0;i<orderPays.size();i++){
            id[i]=orderPays.get(i).getUser_id();
            System.out.println(orderPays.get(i).getUser_id()+"-------------");
        }
        List<User> users1 = orderPayService.findInUNameC(id);
        model.addAttribute("users1",users1);

        return "pay_index";
    }
/*
YC
 */

}
