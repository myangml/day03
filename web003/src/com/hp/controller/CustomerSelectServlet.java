package com.hp.controller;

import com.alibaba.fastjson.JSONObject;
import com.hp.service.CustomerService;
import com.hp.service.UserService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CustomerSelectServlet",urlPatterns = "/CustomerSelectServlet")
public class CustomerSelectServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req ,HttpServletResponse resp) throws ServletException, IOException {
        //super.service(req ,resp);
        //修正编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8"); // 注意 , 别写错
        //接收两个参数
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");

        String cust_name = req.getParameter("cust_name");
        System.out.println("cust_name = " + cust_name);
        String cust_phone = req.getParameter("cust_phone");
        System.out.println("cust_phone = " + cust_phone);
        String modify_time = req.getParameter("modify_time");
        System.out.println("modify_time = " + modify_time);
        String username = req.getParameter("username");
        System.out.println("username = " + username);
        String cust_sex = req.getParameter("cust_sex");
        System.out.println("cust_sex = " + cust_sex);


        Map paramMap = new HashMap();
        paramMap.put("page",page);
        paramMap.put("limit",limit);
        paramMap.put("cust_name",cust_name);
        paramMap.put("cust_phone",cust_phone);
        paramMap.put("modify_time",modify_time);
        paramMap.put("username",username);
        paramMap.put("cust_sex",cust_sex);

        CustomerService customerService = new CustomerService();
        Map map = customerService.PageBeanUtil(paramMap);
        String jsonString = JSONObject.toJSONString(map);
        PrintWriter writer = resp.getWriter();
        writer.print(jsonString);
        writer.close();
    }

}
