package com.pm.www.contorler;

import com.google.gson.Gson;
import com.pm.www.domain.Goods;
import com.pm.www.domain.Order;
import com.pm.www.service.OrderService;
import com.pm.www.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@WebServlet({"/addOrder", "/queryOrderId", "/queryOrderById", "/queryOrdersByMerchantsId", "/acceptOrder", "/queryOrdersDoingByMerchantsId", "/addOrderDetail","/queryOrderByMemberId","/confirmReceive","/queryOrdersFinishedByMerchantsId"})
public class OrderServlet extends BaseServlet {
    /*@Autowired
    private OrderServiceImpl service;*/

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderServiceImpl service=context.getBean(OrderServiceImpl.class);
        Gson gson = new Gson();
        Goods goods = new Goods();
        String uri = request.getRequestURI();
        PrintWriter out = response.getWriter();
        String address = uri.substring(uri.lastIndexOf("/") + 1);
        orderMap.clear();
        if ("addOrder".equals(address)) {
            int mid = Integer.parseInt(request.getParameter("mid"));
            int gid = Integer.parseInt(request.getParameter("gid"));
            int goodQuantity = Integer.parseInt(request.getParameter("goodQuantity"));
            int orderTatolMoney = Integer.parseInt(request.getParameter("orderTatolMoney"));
            Order order = new Order(mid, gid, goodQuantity, orderTatolMoney);
            int index = service.addOrder(order);
            if (index == 1) {
                orderMap.put("code", 200);
                orderMap.put("msg", "下单成功");
            } else {
                orderMap.put("code", 201);
                orderMap.put("msg", "下单失败");
            }
            out.print(gson.toJson(orderMap));
        } else if ("queryOrderId".equals(address)) {
            int mid = Integer.parseInt(request.getParameter("mid"));
            int index = service.queryOrderId(mid);
            if (index != -1) {
                orderMap.put("code", 200);
                orderMap.put("msg", "查询成功");
                orderMap.put("result", index);
            } else {
                orderMap.put("code", 201);
                orderMap.put("msg", "查询失败");
            }
            out.print(gson.toJson(orderMap));
        } else if ("queryOrderById".equals(address)) {
            int oid = Integer.parseInt(request.getParameter("oid"));
            Map<String, Object> orderDetail = service.queryOrderById(oid);
            if (orderDetail.size() > 0) {
                orderMap.put("code", 200);
                orderMap.put("msg", "查询成功");
                orderMap.put("result", orderDetail);
            } else {
                orderMap.put("code", 201);
                orderMap.put("msg", "查询失败");
            }
            out.print(gson.toJson(orderMap));
        } else if ("queryOrdersByMerchantsId".equals(address)) {
            int merchantsId = Integer.parseInt(request.getParameter("merchantsId"));
            List<Map<String, Object>> ordersManager = service.queryOrdersByMerchantsId(merchantsId);
            if (ordersManager.size() > 0) {
                orderMap.put("code", 200);
                orderMap.put("msg", "查询成功");
                orderMap.put("result", ordersManager);
            } else {
                orderMap.put("code", 201);
                orderMap.put("msg", "查询失败");
            }
            out.print(gson.toJson(orderMap));
        } else if ("acceptOrder".equals(address)) {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            int index = service.acceptOrder(orderId);
            if (index != -1) {
                orderMap.put("code", 200);
                orderMap.put("msg", "接单成功");
            } else {
                orderMap.put("code", 201);
                orderMap.put("msg", "接单失败");
            }
            out.print(gson.toJson(orderMap));
        } else if ("queryOrdersDoingByMerchantsId".equals(address)) {
            int merchantsId = Integer.parseInt(request.getParameter("merchantsId"));
            List<Map<String, Object>> orderDoing = service.queryOrdersDoingByMerchantsId(merchantsId);
            if (orderDoing.size() > 0) {
                orderMap.put("code", 200);
                orderMap.put("msg", "查询成功");
                orderMap.put("result", orderDoing);
            } else {
                orderMap.put("code", 201);
                orderMap.put("msg", "查询失败");
            }
            out.print(gson.toJson(orderMap));
        } else if ("addOrderDetail".equals(address)) {
            int goodId = Integer.parseInt(request.getParameter("goodId"));
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            int index = service.addOrderDetail(orderId, goodId);
            if (index != -1) {
                orderMap.put("code", 200);
                orderMap.put("msg", "添加成功");
            } else {
                orderMap.put("code", 201);
                orderMap.put("msg", "添加失败");
            }
            out.print(gson.toJson(orderMap));
        }else if ("queryOrderByMemberId".equals(address)){
            int memberId=Integer.parseInt(request.getParameter("mid"));
            List<Map<String, Object>> orderDoing = service.queryOrderByMemberId(memberId);
            if (orderDoing.size() > 0) {
                orderMap.put("code", 200);
                orderMap.put("msg", "查询成功");
                orderMap.put("result", orderDoing);
            } else {
                orderMap.put("code", 201);
                orderMap.put("msg", "查询失败");
            }
            out.print(gson.toJson(orderMap));
        }else if ("confirmReceive".equals(address)){
            int orderId=Integer.parseInt(request.getParameter("orderId"));
            int index=service.confirmReceive(orderId);
            if (index != -1) {
                orderMap.put("code", 200);
                orderMap.put("msg", "成功");
            } else {
                orderMap.put("code", 201);
                orderMap.put("msg", "失败");
            }
            out.print(gson.toJson(orderMap));
        }else if ("queryOrdersFinishedByMerchantsId".equals(address)){
            int merchantsId = Integer.parseInt(request.getParameter("merchantsId"));
            List<Map<String, Object>> orderFinished = service.queryOrdersFinishedByMerchantsId(merchantsId);
            if (orderFinished.size() > 0) {
                orderMap.put("code", 200);
                orderMap.put("msg", "查询成功");
                orderMap.put("result", orderFinished);
            } else {
                orderMap.put("code", 201);
                orderMap.put("msg", "查询失败");
            }
            out.print(gson.toJson(orderMap));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
