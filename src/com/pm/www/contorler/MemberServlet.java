package com.pm.www.contorler;

import com.google.gson.Gson;
import com.pm.www.domain.Member;
import com.pm.www.service.MemberService;
import com.pm.www.service.impl.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
@Controller
@WebServlet({"/queryByAccount", "/register"})
public class MemberServlet extends BaseServlet {
    /*@Autowired
    private MemberServiceImpl service;*/

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        MemberServiceImpl service=context.getBean(MemberServiceImpl.class);
        Gson gson = new Gson();
        String uri = request.getRequestURI();
        PrintWriter out = response.getWriter();
        String address = uri.substring(uri.lastIndexOf("/") + 1);
        if ("queryByAccount".equals(address)) {
            String mAccount = request.getParameter("mAccount");
            Member member = service.queryByAccount(mAccount);
            result.clear();
            if (member == null) {
                result.put("code", 200);
                result.put("msg", "可以使用");
            } else {
                result.put("code", 201);
                result.put("msg", "该账号已经存在");
                result.put("result",member);
            }
            out.print(gson.toJson(result));
        } else if ("register".equals(address)) {
            String mAccount = request.getParameter("mAccount");
            String mPassword = request.getParameter("mPassword");
            String mPhone = request.getParameter("mPhone");
            String mEmail = request.getParameter("mEmail");
            String mGender = request.getParameter("mGender");
            String mBrithday = request.getParameter("mBrithday");
            Member member = new Member(mPassword, mAccount, mBrithday, mGender, mEmail, mPhone);
            int index=service.registerMember(member);
            result.clear();
            if (index==-1){
                result.put("code",200);
                result.put("msg","注册失败");
            }else {
                result.put("code",201);
                result.put("msg","注册成功！");
            }
            out.print(gson.toJson(result));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
