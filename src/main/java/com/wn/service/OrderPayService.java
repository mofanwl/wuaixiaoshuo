package com.wn.service;

import com.wn.pojo.OrderPay;
import com.wn.pojo.Page;
import com.wn.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019/12/4.
 */
public interface OrderPayService {
    /*
后台的订单查询
 */
    List<OrderPay> selAdminListOrderPay();
    /*
    后台订单号查询订单
     */
    OrderPay selAdminOrderPayByOid(String out_trade_no);
    /*
    后台用户id查询订单
     */
    OrderPay selAdminOrderPayByUserid(Integer user_id);


    //前台
    /*
    订单号查询用户
    */
    OrderPay selUserIdByTrade(String out_trade_no);
    /*
用户的订单查询
 */
    List<OrderPay> selUserByPay(String user_id);
    /*
写入生成的订单号，金额，支付状态，
 */
    public int insertPay(OrderPay orderPay);
    /*
    查询生成的订单号，金额，支付状态
     */
    public OrderPay selectByTrade(String out_trade_no);
    /*
    修改实际支付金额和状态
     */
    public int updateStateAndTotal(@Param("pay_state") String pay_state, @Param("total_amount_1") String total_amount_1, @Param("out_trade_no") String out_trade_no,@Param("pay_time_1")Date pay_time_1);
    /*
    写入关系数据
     */
    public int insertOrderAndPay(OrderPay orderPay);


    /*
YC
*/
    //数据总数
    public Integer selectCountC();
    //分页查询
    public List<OrderPay> selectLimitC(Page p);

    //根据user_id查询user_name
    public List<User> findInUNameC(Integer[] id);
/*
YC
 */

}
