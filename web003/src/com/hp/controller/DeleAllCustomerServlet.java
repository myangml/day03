package com.hp.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleAllCustomerServlet",urlPatterns ="/DeleAllCustomerServlet" )
public class DeleAllCustomerServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req ,HttpServletResponse resp) throws ServletException, IOException {
        //super.service(req ,resp);
        //修正编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8"); // 注意 , 别写错
        String[]  parameterValues = req.getParameterValues("ids[]");
        System.out.println("parameterValues = " + parameterValues);
        for (String parameterValue:parameterValues) {
            System.out.println("parameterValue = " + parameterValue);

        }
    }   //重点，servlet 收取 数组






}
