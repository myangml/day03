package com.hp.dao;

import com.hp.entity.Customer;
import com.hp.entity.User;
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

public class CustomerDao {
    //全查
    public List<Map> selectAll(Map map) {
        System.out.println(" Customer map dao = " + map);
        for (Object o : map.keySet()) {
            System.out.println("o = " + o);
        }
        String page = (String) map.get("page");
        String limit = (String) map.get("limit");
        String cust_name = (String) map.get("cust_name");
        System.out.println("dao cust_name = " + cust_name);
        String cust_phone = (String) map.get("cust_phone");
        String modify_time = (String) map.get("modify_time");
        String username = (String) map.get("username");
        String cust_sex = (String) map.get("cust_sex");
        List<Map> lists = new ArrayList<>();
        Connection connection = DBHelper.getConnection();
        //String sql = "select c.*,u.username from t_customer c,t_user u where c.user_id=u.id; ";
        String sql = "select c.* , t.username as username , t.password as password ,  t.real_name as real_name , t.type as type   from t_customer c  join t_user  t  on c.user_id  = t.id  where 1=1   ";
//        sql = sql + " and t.is_del=1   ";
        if (null!=cust_name&&cust_name.length()>0){
            sql = sql + " and c.cust_name like  '%"+cust_name+"%'  ";
        }
        if (null!=cust_phone&&cust_phone.length()>0){
            sql = sql + " and c.cust_phone   like  '%"+cust_phone+"%'   ";
        }
        if (null!=modify_time&&modify_time.length()>0){
            sql = sql + " and c.modify_time   like  '%"+modify_time+"%'   ";
        }
        if (null!=cust_sex&&cust_sex.length()>0){
            sql = sql + " and c.cust_sex  = "+cust_sex+"   ";
        }
        if (null!=username&&username.length()>0){
            sql = sql + " and username   like  '%"+username+"%'   ";
        }
        sql = sql + " limit  ? ,  ?";
        System.out.println(" dao de sql = " + sql);
        PreparedStatement ps = null;
        ResultSet rs = null;
        PageBeanUtil pageBeanUtil = new PageBeanUtil(Integer.parseInt(page),Integer.parseInt(limit));
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,pageBeanUtil.getStart());
            ps.setInt(2,Integer.parseInt(limit));

//            System.out.println("ps = " + ps);
            rs = ps.executeQuery();
            while(rs.next()) {
                Map datamap = new HashMap();
                datamap.put("id",rs.getInt("id"));
                datamap.put("cust_name",rs.getString("cust_name"));
                datamap.put("cust_company",rs.getString("cust_company"));
                datamap.put("cust_birth",rs.getString("cust_birth"));
                datamap.put("cust_sex",rs.getInt("cust_sex"));
                datamap.put("cust_phone",rs.getString("cust_phone"));
                datamap.put("cust_position",rs.getString("cust_position"));
                datamap.put("create_time",rs.getString("create_time"));
                datamap.put("modify_time",rs.getString("modify_time"));
                datamap.put("username",rs.getString("username"));
                datamap.put("password",rs.getString("password"));
                datamap.put("real_name",rs.getString("real_name"));
                datamap.put("type",rs.getString("type"));
                lists.add(datamap);



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

        return lists;
    }
    //查询总条数
    public int selectCount(Map map){
        String cust_name = (String) map.get("cust_name");
        String cust_phone = (String) map.get("cust_phone");
        String modify_time = (String) map.get("modify_time");
        String username = (String) map.get("username");
        String cust_sex = (String) map.get("cust_sex");

        Connection connection = DBHelper.getConnection();
        //2. 写sql
        String  sql = "  select count(*) total  from t_customer c  join t_user  t  on c.user_id  = t.id  where 1=1 ";
        if (null!=cust_name&&cust_name.length()>0){
            sql = sql + " and c.cust_name like  '%"+cust_name+"%'  ";
        }
        if (null!=cust_phone&&cust_phone.length()>0){
            sql = sql + " and c.cust_phone   like  '%"+cust_phone+"%'   ";
        }
        if (null!=modify_time&&modify_time.length()>0){
            sql = sql + " and c.modify_time   like  '%"+modify_time+"%'   ";
        }
        if (null!=cust_sex&&cust_sex.length()>0){
            sql = sql + " and c.cust_sex   = "+cust_sex+"   ";
        }
        if (null!=username&&username.length()>0){
            sql = sql + " and username   like  '%"+username+"%'   ";
        }
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
    //添加
    public int addCustomer(Customer customer) {

        Connection conn = null;
        PreparedStatement ps = null;
        int i = 0;

        try {
            //开链接
            conn = DBHelper.getConnection();
            //写sql
            String sql = "insert into t_customer values(null,?,?,?,?,?,?,?,?,?,?)";
            System.out.println("insert sql = " + sql);
            ps = conn.prepareStatement(sql);
            ps.setString(1,customer.getCust_name());
            ps.setString(2,customer.getCust_company());
            ps.setString(3,customer.getCust_position());
            ps.setString(4,customer.getCust_phone());
            ps.setString(5,customer.getCust_birth());
            ps.setInt(6,customer.getCust_sex());
            ps.setString(7,customer.getCust_desc());
            ps.setInt(8,customer.getUser_id());
            ps.setString(9,customer.getCreate_time());
            ps.setString(10,customer.getModify_time());
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
    //按id删除
    public int delectIdCustomer(Integer id){
        PreparedStatement pstm = null;
        int i = 0;

        Connection conn=DBHelper.getConnection();
        String sql = "delete from t_customer where id=?";


        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            i = pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }
    public static void main(String[] args) {
//        Customer customer = new Customer();
//        customer.setCust_name("yingying");
//        customer.setCust_company("jituan");
//        customer.setCust_position("cc");
//        customer.setCust_phone("15617245676");
//        customer.setCust_birth("2007-03-07");
//        customer.setCust_sex(2);
//        customer.setCust_desc("dgfbv");
//        customer.setUser_id(25);
//        customer.setCreate_time("2007-03-07");
//        customer.setModify_time("2007-03-08");



//        Map paramMap =new HashMap();
//        paramMap.put("page","1");
//        paramMap.put("limit","5");

//          CustomerDao dao = new CustomerDao();
//          int i = dao.delectIdCustomer(18);
//        System.out.println("i = " + i);
//          int i = dao.addCustomer(customer);
//        List users = dao.selectAll();
//        for (Object customer:users) {
//            System.out.println("Customer = " + customer);
//         }
//        int i = dao.selectCount(paramMap);
//        System.out.println("i = " + i);

//        List<Map> customer = dao.selectAll(paramMap);
//        System.out.println("customer = " + customer);
//        System.out.println("customer = " + customer.size());


    }
}
