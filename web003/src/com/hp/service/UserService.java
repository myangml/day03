package com.hp.service;

import com.hp.entity.User;
import com.hp.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {


    public Map login(String username,String password,HttpServletRequest request){
        Map map = new HashMap();
        // service 层要调用dao层
        int count=0;
        UserDao dao = new UserDao();
        User userFromDB = dao.login(username, password);

        if (  null == userFromDB  ) {
            // 没查不出, 即:账户名或者密码不正确
            map.put("code",4001);
            map.put("msg","账户名或者密码不正确");
            count+=1;
            return map;
        }else{
            // 当登陆成功后,需要把 登陆的用户的信息放入到  session中去
            HttpSession session = request.getSession();
            session.setAttribute("user",userFromDB);
            map.put("code",0);
            map.put("msg","登陆成功");
            map.put("username",username);
            return map;
        }
    }

    public Map PageBeanUtil(Map map1){
        UserDao dao = new UserDao();
        List<User> users = dao.selectAllByParam(map1);
        int i = dao.selectCount(map1);
        Map map = new HashMap();
        map.put("code111",200);  //  返回的数据不符合规范，正确的成功状态码应为："code": 0
        map.put("msg111","查询成功");
        map.put("count111",i);// 把死的写活
        map.put("data111",users);
        // 根据layui的 返回的 json 格式 去 返回给你数据. 如果不一样, 需要 layui解析.
        //   {         code: 0,
        //            msg: "",
        //            count: 1000,
        //            data:  [每条数据]
        //  }
        Map  map2 = new HashMap();
        map2.put("number",20001); // 在某一家公司种  规范这是这个
        map2.put("message","数据查询成功");
        map2.put("object",map);

        return map2;
    }
    // 修改 是否可用
    public Map updateUserById (Integer is_del , Integer userId){
        UserDao dao =new UserDao();
        int i = dao.updateUserId(is_del,userId);
        Map map = new HashMap();
        if (i==1){
            map.put("code",0);
            map.put("msg","修改成功");
        }else{
            map.put("code",400);
            map.put("msg","修改不成功");
        }
        return map;
    }

    // 修改全部
    public Map updateUser(User user){
        Map codeMap=new HashMap();
        UserDao dao =new UserDao();
        int i = dao.updateUser(user);
        if (i==1){
            codeMap.put("code",0);
            codeMap.put("msg","请求成功");
        }else{
            codeMap.put("code",400);
            codeMap.put("msg","请求失败");
        }
        return codeMap;
    }

    public Map selectUserById(Integer id) {
        Map codeMap = new HashMap();
        UserDao dao = new UserDao();
        User user = dao.selectId(id);

            codeMap.put("code" ,0);  //  返回的数据不符合规范，正确的成功状态码应为："code": 0
            codeMap.put("msg" ,"ok");
            codeMap.put("data" ,user);  //  返回的数据不符合规范，正确的成功状态码应为："code":
        return codeMap;
    }
    //
    public Map selectAllByService(){
        Map codeMap = new HashMap();
        UserDao dao = new UserDao();
        List<User> users = dao.selectAllByService();
        codeMap.put("code" ,0);  //  返回的数据不符合规范，正确的成功状态码应为："code": 0
        codeMap.put("msg" ,"ok");
        codeMap.put("data" ,users);  //  返回的数据不符合规范，正确的成功状态码应为："code":
        return codeMap;

    }

}
