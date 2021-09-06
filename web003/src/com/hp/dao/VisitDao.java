package com.hp.dao;

import com.hp.entity.Customer;
import com.hp.entity.User;
import com.hp.entity.Visit;
import com.hp.util.DBHelper;
import com.hp.util.PageBeanUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisitDao {
    //添加
    public int addVisit(Visit visit) {

        Connection conn = null;
        PreparedStatement ps = null;
        int i = 0;

        try {
            //开链接
            conn = DBHelper.getConnection();
            //写sql
            String sql = "insert into t_visit values(null,?,?,?,?,?)";
            System.out.println("insert sql = " + sql);
            ps = conn.prepareStatement(sql);
            ps.setInt(1,visit.getUser_id());
            ps.setInt(2,visit.getCust_id());
            ps.setString(3,visit.getVisit_desc());
            ps.setString(4,visit.getVisit_time());
            ps.setString(5,visit.getCreate_time());

            i = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try { conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return i;
    }
    //全查

    public List<Map> selectAllByParam(Map map){
        System.out.println("   jinlaiel    dao  " );
        System.out.println("map dao  = " + map);
        for (Object o : map.keySet()) {
            System.out.println("o = " + o);
        }
        String page = (String) map.get("page");
        String limit = (String) map.get("limit");

        List<Map> lists = new ArrayList<>();
        Connection conn = DBHelper.getConnection();
        String  sql ="  select  v.*,t.username as username,c.cust_name as cust_name from t_visit v , t_user t,t_customer c  where v.user_id  = t.id and c.id=v.cust_id order by c.cust_name"; // where 1=1  因为 有多余的 and
        sql = sql + " limit  ? ,  ?";
        System.out.println(" dao de  sql = " + sql);
        PreparedStatement ps = null;
        ResultSet rs = null;
        PageBeanUtil pageBeanUtil = new PageBeanUtil(Integer.parseInt(page),Integer.parseInt(limit));

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,pageBeanUtil.getStart());
            ps.setInt(2,Integer.parseInt(limit));
            rs = ps.executeQuery();

            while(rs.next()) {
                Map datamap = new HashMap();;
                datamap.put("id",rs.getInt("id"));
                datamap.put("cust_id",rs.getInt("cust_id"));
                datamap.put("user_id",rs.getInt("user_id"));
                datamap.put("visit_desc",rs.getString("visit_desc"));
                datamap.put("visit_time",rs.getString("visit_time"));
                datamap.put("create_time",rs.getString("create_time"));
                datamap.put("cust_name",rs.getString("cust_name"));
                datamap.put("username",rs.getString("username"));
                lists.add(datamap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lists;
    }

    //查询总条数
    public int selectCount(Map map){

        Connection connection = DBHelper.getConnection();
        //2. 写sql
        String  sql = "  select count(*) total  from t_visit ";
        System.out.println("sql  count 的  = " + sql);
        PreparedStatement ps=null;
        ResultSet rs=null;
        int total = 0;
        try {
            ps = connection.prepareStatement(sql);

            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return total;
    }

    public static void main(String[] args) {
//        VisitDao dao = new VisitDao();
//        Map paramMap =new HashMap();
//        paramMap.put("page","1");
//        paramMap.put("limit","5");
//        List<Map> visits = dao.selectAllByParam(paramMap);
//        System.out.println("visits = " + visits);
//        System.out.println("visits.size() = " + visits.size());
//        int i = dao.selectCount(paramMap);
//        System.out.println("i = " + i);
    }
}
