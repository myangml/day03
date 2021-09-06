package com.hp.controller;

import com.alibaba.fastjson.JSONObject;
import com.hp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "UserUpdateDelServlet",urlPatterns = "/UserUpdateDelServlet")
public class UserUpdateDelServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req ,HttpServletResponse resp) throws ServletException, IOException {
       // super.service(req ,resp);
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8"); // 注意 , 别写错

        //2. 接收 前端过来的 3个参数
        String is_del = req.getParameter("is_del");
        String userId = req.getParameter("userId");

        System.out.println("is_del = " + is_del);
        System.out.println("userId = " + userId);

        UserService userService = new UserService();
        Map map = userService.updateUserById(Integer.parseInt(is_del),Integer.parseInt(userId));
        String jsonString = JSONObject.toJSONString(map);
        PrintWriter writer = resp.getWriter();
        writer.print(jsonString);
        writer.close();
    }
}
