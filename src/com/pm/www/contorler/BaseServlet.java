package com.pm.www.contorler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Controller
public class BaseServlet extends HttpServlet {
        protected Map<String,Object> result=new HashMap<>();
        protected Map<String,Object> resultMap=new HashMap<>();
        protected Map<String,Object> goodTypeMap=new HashMap<>();
        protected Map<String,Object> goodsMap=new HashMap<>();
        protected Map<String,Object> orderMap=new HashMap<>();
}
