package com.hp.controller;

import com.alibaba.fastjson.JSONObject;
import com.hp.service.CustomerService;
import com.hp.service.VisitService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "VisitServiceServlet",urlPatterns = "/VisitServiceServlet")
public class VisitServiceServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req ,HttpServletResponse resp) throws ServletException, IOException {
        //super.service(req ,resp);
        //修正编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8"); // 注意 , 别写错
        //接收两个参数
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        System.out.println("page1 = " + page);
        System.out.println("limit1 = " + limit);
        Map paramMap = new HashMap();
        paramMap.put("page",page);
        paramMap.put("limit",limit);

        VisitService visitService = new VisitService();
        Map map =visitService.PageBeanUtil(paramMap);
        String jsonString = JSONObject.toJSONString(map);
        PrintWriter writer = resp.getWriter();
        writer.print(jsonString);
        writer.close();
    }
}
