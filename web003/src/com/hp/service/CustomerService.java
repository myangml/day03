package com.hp.service;

import com.hp.dao.CustomerDao;
import com.hp.dao.UserDao;
import com.hp.entity.Customer;
import com.hp.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerService {
//    public Map PageBeanUtil(Map map){
//        CustomerDao dao = new CustomerDao();
////        List customer = dao.selectAllByParam(page,limit);
//        List<Map> maps = dao.selectAll(map);
//        int i = dao.selectCount(map);
//        Map codemap = new HashMap();
//        codemap.put("code111",200);  //  返回的数据不符合规范，正确的成功状态码应为："code": 0
//        codemap.put("msg111","查询成功");
//        codemap.put("count111",i);// 把死的写活
//        codemap.put("data111",maps);
//        // 根据layui的 返回的 json 格式 去 返回给你数据. 如果不一样, 需要 layui解析.
//        //   {         code: 0,
//        //            msg: "",
//        //            count: 1000,
//        //            data:  [每条数据]
//        //  }
//        Map  map2 = new HashMap();
//        map2.put("number",20001); // 在某一家公司种  规范这是这个
//        map2.put("message","数据查询成功");
//        map2.put("object",map);
//
//        return map2;
//    }
        public Map PageBeanUtil(Map map){
            CustomerDao dao = new CustomerDao();
//        List customer = dao.selectAllByParam(page,limit);
        List<Map> maps = dao.selectAll(map);
        //int i = dao.selectCount(map);
        Map codemap = new HashMap();
        codemap.put("code",0);  //  返回的数据不符合规范，正确的成功状态码应为："code": 0
        codemap.put("msg","查询成功");
        codemap.put("data",maps);
            Map countMap = selectCount(map);
        int count = (int)countMap.get("data");
        codemap.put("count",count);
        return codemap;
        }

    public Map selectCount(Map map){
        Map codemap = new HashMap();
        CustomerDao dao = new CustomerDao();
        int i = dao.selectCount(map);
        codemap.put("code",0);  //  返回的数据不符合规范，正确的成功状态码应为："code": 0
        codemap.put("msg","查询成功");
        codemap.put("data",i);

        return codemap;
    }
    //添加
    public Map addCustomer(Customer customer){
        Map codeMap=new HashMap();
        CustomerDao dao =new CustomerDao();
        int i = dao.addCustomer(customer);
        if (i==1){
            codeMap.put("code",0);
            codeMap.put("msg","添加成功");
        }else{
            codeMap.put("code",400);
            codeMap.put("msg","添加失败");
        }
        return codeMap;
    }
    public Map delectIdCustomer(Integer id){
        Map codeMap=new HashMap();
        CustomerDao dao =new CustomerDao();
        int i = dao.delectIdCustomer(id);
        if (i==1){
            codeMap.put("code",0);
            codeMap.put("msg","删除成功");
        }else{
            codeMap.put("code",400);
            codeMap.put("msg","删除失败");
        }
        return codeMap;
    }
}
