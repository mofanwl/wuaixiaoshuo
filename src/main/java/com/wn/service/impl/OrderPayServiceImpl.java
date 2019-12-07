package com.wn.service.impl;

import com.wn.dao.OrderPayDao;
import com.wn.pojo.OrderPay;
import com.wn.pojo.Page;
import com.wn.pojo.User;
import com.wn.service.OrderPayService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019/12/4.
 */
@Service
public class OrderPayServiceImpl implements OrderPayService {
    @Autowired
    private OrderPayDao orderPayDao;

    @Override
    public List<OrderPay> selAdminListOrderPay() {
        //加入分页


        return orderPayDao.selAdminListOrderPay();
    }

    @Override
    public OrderPay selAdminOrderPayByOid(String out_trade_no) {
        return orderPayDao.selAdminOrderPayByOid(out_trade_no);
    }

    @Override
    public OrderPay selAdminOrderPayByUserid(Integer user_id) {
        return orderPayDao.selAdminOrderPayByUserid(user_id);
    }

    @Override
    public OrderPay selUserIdByTrade(String out_trade_no) {
        return orderPayDao.selUserIdByTrade(out_trade_no);
    }

    @Override
    public List<OrderPay> selUserByPay(String user_id) {
        return orderPayDao.selUserByPay(user_id);
    }

    @Override
    public int insertPay(OrderPay orderPay) {
        return orderPayDao.insertPay(orderPay);
    }

    @Override
    public OrderPay selectByTrade(String out_trade_no) {
        return orderPayDao.selectByTrade(out_trade_no);
    }

    @Override
    public int updateStateAndTotal(@Param("pay_state") String pay_state, @Param("total_amount_1") String total_amount_1, @Param("out_trade_no") String out_trade_no,@Param("pay_time_1")Date pay_time_1) {
        return orderPayDao.updateStateAndTotal(pay_state,total_amount_1,out_trade_no,pay_time_1);
    }

    @Override
    public int insertOrderAndPay(OrderPay orderPay) {
        return orderPayDao.insertOrderAndPay(orderPay);
    }


/*
yc
 */

    @Override
    public Integer selectCountC() {
        return orderPayDao.selectCountC();
    }

    @Override
    public List<OrderPay> selectLimitC(Page p) {
        return orderPayDao.selectLimitC(p);
    }

    @Override
    public List<User> findInUNameC(Integer[] id) {
        return orderPayDao.findInUNameC(id);
    }

    /*
    YC
     */

}
