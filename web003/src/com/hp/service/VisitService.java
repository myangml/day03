package com.hp.service;

import com.hp.dao.CustomerDao;
import com.hp.dao.VisitDao;
import com.hp.entity.Visit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisitService {

    public Map PageBeanUtil(Map map){
        VisitDao dao = new VisitDao();
//        List customer = dao.selectAllByParam(page,limit);
        List<Map> maps = dao.selectAllByParam(map);
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
        VisitDao dao = new VisitDao();
        int i = dao.selectCount(map);
        codemap.put("code",0);  //  返回的数据不符合规范，正确的成功状态码应为："code": 0
        codemap.put("msg","查询成功");
        codemap.put("data",i);

        return codemap;
    }
}
