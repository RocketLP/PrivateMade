package com.pm.www.contorler;

import com.google.gson.Gson;
import com.pm.www.domain.GoodType;
import com.pm.www.domain.Goods;
import com.pm.www.service.GoodTypeService;
import com.pm.www.service.impl.GoodTypeServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet({"/queryAllGoodType","/addGoodType"})
public class GoodTypeServlet extends BaseServlet {
    /*@Autowired
    private GoodTypeServiceImpl service;*/

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        GoodTypeServiceImpl service=context.getBean(GoodTypeServiceImpl.class);
        Gson gson = new Gson();
        String uri = request.getRequestURI();
        PrintWriter out = response.getWriter();
        String address = uri.substring(uri.lastIndexOf("/") + 1);
        goodTypeMap.clear();
        if ("queryAllGoodType".equals(address)) {
            List<Map<String, Object>> goodType = service.queryAllGoodType();
            goodTypeMap.put("result", goodType);
            out.print(gson.toJson(goodTypeMap));
        }else if ("addGoodType".equals(address)){
            String goodTypeName=request.getParameter("goodTypeName");
            int index=service.addGoodType(goodTypeName);
            out.print(index);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
