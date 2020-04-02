package com.pm.www.contorler;

import com.google.gson.Gson;
import com.pm.www.domain.Merchants;
import com.pm.www.service.impl.MerchantsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@WebServlet({"/queryByMcName","/mcRegister","/queryAllMerchants","/reject","/pass","/queryNoCheckedMerchants","/queryMerchantsNameById"})
public class MerchantsServlet extends BaseServlet {
    /*@Autowired
    private MerchantsServiceImpl service;*/
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        MerchantsServiceImpl service=context.getBean(MerchantsServiceImpl.class);
        Gson gson = new Gson();
        String uri = request.getRequestURI();
        PrintWriter out = response.getWriter();
        String address = uri.substring(uri.lastIndexOf("/") + 1);
        resultMap.clear();
        if ("queryByMcName".equals(address)){
            String mcName=request.getParameter("mcName");
            Merchants merchants=service.queryByMcName(mcName);
            if (merchants==null){
                resultMap.put("code",200);
                resultMap.put("msg1","可以使用");
                resultMap.put("msg2","账号不存在");
            }else {
                resultMap.put("code",201);
                resultMap.put("msg1","该账号已被使用");
                resultMap.put("msg2","注册成功");
                resultMap.put("result",merchants);
            }
            out.print(gson.toJson(resultMap));
        }else if ("mcRegister".equals(address)){
            String mcName=request.getParameter("mcName");
            String mcPhone=request.getParameter("mcPhone");
            String mcPassword=request.getParameter("mcPassword");
            String mcLicense=request.getParameter("mcLicense");
            Merchants merchants=new Merchants(mcName,mcPassword,mcLicense,mcPhone);
            int index=service.mcRegister(merchants);
            if (index!=-1){
                resultMap.put("code",200);
                resultMap.put("msg","注册成功,待审核");
            }else {
                resultMap.put("code",200);
                resultMap.put("msg","注册失败");
            }
            out.print(gson.toJson(resultMap));
        }else if ("queryAllMerchants".equals(address)){
            List<Map<String,Object>> merchants=service.queryAllMerchants();
            if (merchants.size()>0){
                resultMap.put("code",200);
                resultMap.put("msg","查询成功");
                resultMap.put("result",merchants);
            }else {
                resultMap.put("code",201);
                resultMap.put("msg","没有相关信息");
            }
            out.print(gson.toJson(resultMap));
        }else if ("reject".equals(address)){
            int mcid=Integer.parseInt(request.getParameter("mcid"));
            int index=service.reject(mcid);
            if (index==1){
                resultMap.put("code",200);
                resultMap.put("msg","驳回成功");
            }else {
                resultMap.put("code",201);
                resultMap.put("msg","驳回失败");
            }
            out.print(gson.toJson(resultMap));
        }else if ("pass".equals(address)){
            int mcid=Integer.parseInt(request.getParameter("mcid"));
            int index=service.pass(mcid);
            if (index==1){
                resultMap.put("code",200);
                resultMap.put("msg","通过成功");
            }else {
                resultMap.put("code",201);
                resultMap.put("msg","通过失败");
            }
            out.print(gson.toJson(resultMap));
        }else if ("queryNoCheckedMerchants".equals(address)){
            List<Map<String,Object>> merchants=service.queryNoCheckedMerchants();
            if (merchants.size()>0){
                resultMap.put("code",200);
                resultMap.put("msg","查询成功");
                resultMap.put("result",merchants);
            }else {
                resultMap.put("code",201);
                resultMap.put("msg","没有相关信息");
            }
            out.print(gson.toJson(resultMap));
        }else if ("queryMerchantsNameById".equals(address)){
            int merchantsId=Integer.parseInt(request.getParameter("merchantsId"));
            String mcname=service.queryMerchantsNameById(merchantsId);
            resultMap.put("result",mcname);
            out.print(gson.toJson(resultMap));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
