package com.pm.www.contorler;

import com.google.gson.Gson;
import com.pm.www.domain.Goods;
import com.pm.www.service.impl.GoodsServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

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
@Controller
@WebServlet({"/addGood","/queryAllGoodsByMcid","/queryGoodById","/editGood","/deleteGood","/queryAllGoods","/queryGoodMerchantsByGoodId"})
public class GoodsServlet extends BaseServlet {
    /*@Autowired
    private GoodsServiceImpl service;*/

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        GoodsServiceImpl service=context.getBean(GoodsServiceImpl.class);
        Gson gson =new Gson();
        Goods goods = new Goods();
        String uri = request.getRequestURI();
        PrintWriter out = response.getWriter();
        String address = uri.substring(uri.lastIndexOf("/") + 1);
        goodsMap.clear();
        if ("addGood".equals(address)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            try {
                List<FileItem> items = upload.parseRequest(request);
                Map<String, Object> map = new HashMap<>();
                for (FileItem item : items) {
                    if (item.isFormField()) {
                        map.put(item.getFieldName(), new String(item.getString().getBytes("iso-8859-1"), "utf-8"));
                    } else {
                        String oldName = item.getName();
                        String newName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(System.currentTimeMillis()) + oldName.substring(oldName.lastIndexOf("."));
                        String path = getServletContext().getRealPath("upload");
                        if (!new File(path).exists()) {
                            new File(path).mkdirs();
                        }
                        File file = new File(path, newName);
                        InputStream is = item.getInputStream();
                        FileOutputStream fos = new FileOutputStream(file);
                        byte[] b = new byte[1024 * 8];
                        int len;
                        while ((len = is.read(b)) != -1) {
                            fos.write(b, 0, len);
                            fos.flush();
                        }
                        is.close();
                        fos.close();
                        map.put("goodPhoto", "/upload/" + newName);
                    }
                }
                goods.setGoodName(map.get("goodName").toString());
                goods.setGoodPrice(Double.parseDouble(map.get("goodPrice").toString()));
                goods.setGoodPhoto(map.get("goodPhoto").toString());
                goods.setGoodType(Integer.parseInt(map.get("goodType").toString()));
                goods.setGoodDescribe(map.get("goodDescribe").toString());
                goods.setMerchantsId(Integer.parseInt(map.get("mcid").toString()));
                int index=service.addGood(goods);
                if (index==1){
                    goodsMap.put("code",200);
                    goodsMap.put("msg","添加成功");
                }else {
                    goodsMap.put("code",201);
                    goodsMap.put("msg","添加失败");
                }
                out.print(gson.toJson(goodsMap));
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }else if ("queryAllGoodsByMcid".equals(address)){
            int mcid= Integer.parseInt(request.getParameter("mcid"));
            List<Map<String,Object>> goodsOfMerchants=service.queryAllGoodsByMcid(mcid);
            if (goodsOfMerchants.size()>0){
                goodsMap.put("code",200);
                goodsMap.put("msg","查询成功");
                goodsMap.put("result",goodsOfMerchants);
            }else {
                goodsMap.put("code",201);
                goodsMap.put("msg","查询失败");
            }
            out.print(gson.toJson(goodsMap));
        }else if ("queryGoodById".equals(address)){
            int gid=Integer.parseInt(request.getParameter("gid"));
            Goods good=service.queryGoodById(gid);
            if (good!=null){
                goodsMap.put("code",200);
                goodsMap.put("msg","查询成功");
                goodsMap.put("result",good);
            }else {
                goodsMap.put("code",201);
                goodsMap.put("msg","查询失败");
            }
            out.print(gson.toJson(goodsMap));
        }else if ("editGood".equals(address)){
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            try {
                List<FileItem> items = upload.parseRequest(request);
                Map<String, Object> map = new HashMap<>();
                for (FileItem item : items) {
                    if (item.isFormField()) {
                        map.put(item.getFieldName(), new String(item.getString().getBytes("iso-8859-1"), "utf-8"));
                    } else {
                        String oldName = item.getName();
                        String newName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(System.currentTimeMillis()) + oldName.substring(oldName.lastIndexOf("."));
                        String path = getServletContext().getRealPath("upload");
                        if (!new File(path).exists()) {
                            new File(path).mkdirs();
                        }
                        File file = new File(path, newName);
                        InputStream is = item.getInputStream();
                        FileOutputStream fos = new FileOutputStream(file);
                        byte[] b = new byte[1024 * 8];
                        int len;
                        while ((len = is.read(b)) != -1) {
                            fos.write(b, 0, len);
                            fos.flush();
                        }
                        is.close();
                        fos.close();
                        map.put("goodPhoto", "/upload/" + newName);
                    }
                }
                goods.setGoodName(map.get("goodName").toString());
                goods.setGoodPrice(Double.parseDouble(map.get("goodPrice").toString()));
                goods.setGoodPhoto(map.get("goodPhoto").toString());
                goods.setGoodType(Integer.parseInt(map.get("goodType").toString()));
                goods.setGoodDescribe(map.get("goodDescribe").toString());
                goods.setGoodId(Integer.parseInt(map.get("goodId").toString()));
                int index=service.editGoodById(goods);
                if (index==1){
                    goodsMap.put("code",200);
                    goodsMap.put("msg","编辑成功");
                }else {
                    goodsMap.put("code",201);
                    goodsMap.put("msg","编辑失败");
                }
                out.print(gson.toJson(goodsMap));
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }else if ("deleteGood".equals(address)){
            int goodId=Integer.parseInt(request.getParameter("gid"));
            int index=service.deleteGood(goodId);
            if (index==1){
                goodsMap.put("code",200);
                goodsMap.put("msg","删除成功");
            }else {
                goodsMap.put("code",201);
                goodsMap.put("msg","删除失败");
            }
            out.print(gson.toJson(goodsMap));
        }else if ("queryAllGoods".equals(address)){
            List<Map<String,Object>> displayGoods=service.queryAllGoods();
            if (displayGoods.size()>0){
                goodsMap.put("code",200);
                goodsMap.put("msg","查询成功");
                goodsMap.put("result",displayGoods);
            }else {
                goodsMap.put("code",201);
                goodsMap.put("msg","查询失败");
            }
            out.print(gson.toJson(goodsMap));
        }else if ("queryGoodMerchantsByGoodId".equals(address)){
            int gid=Integer.parseInt(request.getParameter("gid"));
            Map<String,Object> good=service.queryGoodMerchantsByGoodId(gid);
            if (good.size()>0){
                goodsMap.put("code",200);
                goodsMap.put("msg","查询成功");
                goodsMap.put("result",good);
            }else {
                goodsMap.put("code",201);
                goodsMap.put("msg","查询失败");
            }
            out.print(gson.toJson(good));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
