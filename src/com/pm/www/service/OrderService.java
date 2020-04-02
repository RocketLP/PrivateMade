
package com.pm.www.service;

import com.pm.www.domain.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {

    /**
     * 添加订单
     */

    int addOrder(Order order);


    /**
     * 查询用户最新订单编号
     */

    int queryOrderId(int mid);


    /**
     * 查询订单详细信息
     */

    Map<String, Object> queryOrderById(int oid);


    /**
     * 通过商家id查询订单
     */

    List<Map<String, Object>> queryOrdersByMerchantsId(int mcid);


    /**
     * 接单
     */

    int acceptOrder(int oid);


    /**
     * 待完成订单
     */

    List<Map<String, Object>> queryOrdersDoingByMerchantsId(int mcid);


    /**
     * 添加订单明细
     */

    int addOrderDetail(int oid, int gid);


    /**
     * 通过会员id查询订单
     */

    List<Map<String, Object>> queryOrderByMemberId(int mid);


    /**
     * 确认收货
     */

    int confirmReceive(int oid);


    /**
     * 查询已完成订单
     */

    List<Map<String, Object>> queryOrdersFinishedByMerchantsId(int mcid);

}

