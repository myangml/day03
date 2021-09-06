package com.hp.controller;

import com.alibaba.fastjson.JSONObject;
import com.hp.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "DelectIdCustomerServlet",urlPatterns = "/DelectIdCustomerServlet")
public class DelectIdCustomerServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req ,HttpServletResponse resp) throws ServletException, IOException {
        //super.service(req ,resp);
        //修正编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8"); // 注意 , 别写错
        String id = req.getParameter("id");
        System.out.println("id = " + id);

        CustomerService customerService = new CustomerService();
        Map map = customerService.delectIdCustomer(Integer.parseInt(id));
        String jsonString = JSONObject.toJSONString(map);
        PrintWriter writer = resp.getWriter();
        writer.print(jsonString);
        writer.close();
    }
}
