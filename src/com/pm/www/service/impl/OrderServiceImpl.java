package com.pm.www.service.impl;

import com.pm.www.dao.OrderDao;
import com.pm.www.dao.impl.OrderDaoImpl;
import com.pm.www.domain.Order;
import com.pm.www.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDaoImpl orderDao;
    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false)
    public int addOrder(Order order) {
        return orderDao.addOrder(order);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false)
    public int queryOrderId(int mid) {
        return orderDao.queryOrderId(mid);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> queryOrderById(int oid) {
        return orderDao.queryOrderById(oid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> queryOrdersByMerchantsId(int mcid) {
        return orderDao.queryOrdersByMerchantsId(mcid);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false)
    public int acceptOrder(int oid) {
        return orderDao.acceptOrder(oid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> queryOrdersDoingByMerchantsId(int mcid) {
        return orderDao.queryOrdersDoingByMerchantsId(mcid);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false)
    public int addOrderDetail(int oid, int gid) {
        return orderDao.addOrderDetail(oid,gid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> queryOrderByMemberId(int mid) {
        return orderDao.queryOrderByMemberId(mid);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false)
    public int confirmReceive(int oid) {
        return orderDao.confirmReceive(oid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> queryOrdersFinishedByMerchantsId(int mcid) {
        return orderDao.queryOrdersFinishedByMerchantsId(mcid);
    }
}
