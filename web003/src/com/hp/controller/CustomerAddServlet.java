package com.hp.controller;

import com.alibaba.fastjson.JSONObject;
import com.hp.entity.Customer;
import com.hp.entity.User;
import com.hp.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CustomerAddServlet",urlPatterns = "/CustomerAddServlet")
public class CustomerAddServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req ,HttpServletResponse resp) throws ServletException, IOException {
        //super.service(req ,resp);
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8"); // 注意 , 别写错

        String cust_name = req.getParameter("cust_name");
        String cust_company = req.getParameter("cust_company");
        String cust_birth = req.getParameter("cust_birth");
        String cust_position = req.getParameter("cust_position");
        String cust_phone = req.getParameter("cust_phone");
        String cust_desc = req.getParameter("cust_desc");
        String user_id = req.getParameter("user_id");
        String modify_time = req.getParameter("modify_time");
//        String username = req.getParameter("username");
        String cust_sex = req.getParameter("cust_sex");

        System.out.println("cust_name = " + cust_name);
        System.out.println("cust_phone = " + cust_phone);
        System.out.println("modify_time = " + modify_time);
        System.out.println("user_id = " + user_id);
        System.out.println("cust_desc = " + cust_desc);
        System.out.println("cust_position = " + cust_position);
        System.out.println("cust_birth = " + cust_birth);
        System.out.println("cust_company = " + cust_company);
        System.out.println("cust_sex = " + cust_sex);

        Customer customer = new Customer();
        customer.setCreate_time(cust_name);
        customer.setCust_company(cust_company);
        customer.setCust_birth(cust_birth);
        customer.setModify_time(modify_time);
        customer.setCust_position(cust_position);
        customer.setCust_phone(cust_phone);
        customer.setCust_desc(cust_desc);
        customer.setUser_id(Integer.parseInt(user_id));
        customer.setCust_sex(Integer.parseInt(cust_sex));
        customer.setCust_desc(modify_time);


        CustomerService customerService = new CustomerService();
        Map map = customerService.addCustomer(customer);
        String s = JSONObject.toJSONString(map);
        PrintWriter writer = resp.getWriter();
        writer.println(s);//  jjjjj
        writer.close();
    }
}
