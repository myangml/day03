package com.hp.dao;

import com.hp.entity.User;
import com.hp.util.DBHelper;
import com.hp.util.PageBeanUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class  UserDao {
    //登录
    public User login(String username,String password){
        User user =null;
        // 1. 创建链接
        Connection connection = DBHelper.getConnection();
        // 2. 建出 sql 语句
        String sql = " select * from t_user where username = ? and password = ?  ";
        // 3. 使用链接对象 获取 预编译对象
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            // 4. 执行 预编译对象,得出结果集
            rs = ps.executeQuery();
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setCreate_time(rs.getString("create_time"));
                user.setImg(rs.getString("img"));
                user.setIs_del(rs.getInt("is_del"));
                user.setModify_time(rs.getString("modify_time"));
                user.setPassword(rs.getString("password"));
                user.setReal_name(rs.getString("real_name"));
                user.setType(rs.getInt("type"));
                user.setUsername(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    //全查
    public List<User> selectAllByService(){
        List<User> users = new ArrayList<>();
        Connection connection = DBHelper.getConnection();
        String sql="select * from t_user where type=2";
        PreparedStatement ps = null;
        ResultSet rs =null;
        try {
            ps = connection.prepareStatement(sql);
            System.out.println("ps = " + ps);

            rs = ps.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setCreate_time(rs.getString("create_time"));
                user.setImg(rs.getString("img"));
                user.setIs_del(rs.getInt("is_del"));
                user.setModify_time(rs.getString("modify_time"));
                user.setReal_name(rs.getString("real_name"));
                user.setType(rs.getInt("type"));
                users.add(user);
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
        return users;
    }
//添加
    public int addUser(User user) {
        // TODO Auto-generated method stub
        Connection conn = null;
        PreparedStatement ps = null;
        int i = 0;

        try {
            conn = DBHelper.getConnection();
            String sql = "insert into t_user values(null,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getReal_name());
            ps.setString(4,user.getImg());
            ps.setInt(5,user.getType());
            ps.setInt(6,user.getIs_del());
            ps.setString(7,user.getCreate_time());
            ps.setString(8,user.getModify_time());
            i = ps.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                conn.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return i;
    }
    //删除
    public int deleteUser(int id) {
        // TODO Auto-generated method stub
        Connection conn = null;
        PreparedStatement pstm = null;
        int i = 0;
        try {
            conn = DBHelper.getConnection();
            String sql = "delete from t_user where id=?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            i = pstm.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return i;
    }
//修改
// 全部的修改
public int updateUser(User user){
    // 开链接
    Connection connection = DBHelper.getConnection();
    // 写sql
    String sql = " update t_user set create_time=? , img=? , is_del= ? , modify_time=? , password=? , real_name=? , type=? , username=?  where id = ?  ";
    // 编译
    PreparedStatement ps=null;
    int i =0;
    try {
        ps = connection.prepareStatement(sql);
        ps.setString(1,user.getCreate_time());
        ps.setString(2,user.getImg());
        ps.setInt(3,user.getIs_del());
        ps.setString(4,user.getModify_time());
        ps.setString(5,user.getPassword());
        ps.setString(6,user.getReal_name());
        ps.setInt(7,user.getType());
        ps.setString(8,user.getUsername());
        ps.setInt(9,user.getId());
        // 执行
        i = ps.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return i;
}
    // 动态的带参数的分页查询
    //    //  页数 page
    //    //  条数 limit
    public List<User> selectAllByParam( Map map){
        System.out.println("   jinlaiel    dao  " );
        System.out.println("map dao  = " + map);
        for (Object o : map.keySet()) {
            System.out.println("o = " + o);
        }
        String page = (String) map.get("page");
        String limit = (String) map.get("limit");
        String real_name = (String) map.get("real_name");
        String type = (String) map.get("type");
        String username = (String) map.get("username");

    List<User> lists = new ArrayList<>();
        Connection conn = DBHelper.getConnection();
        String  sql ="  select  *  from t_user  where 1=1  "; // where 1=1  因为 有多余的 and
        if (null!=username&&username.length()>0){
            sql = sql + " and username   like  '%"+username+"%'   ";
        }
        if (null!=type&&type.length()>0){
            sql = sql + " and type   =  "+type+"   ";
        }
        if (null!=real_name&&real_name.length()>0){
            sql = sql + " and real_name   like  '%"+real_name+"%'   ";
        }
        sql = sql + " limit  ? ,  ?";
        System.out.println(" dao de  sql = " + sql);
        PreparedStatement ps = null;
        ResultSet rs = null;
       PageBeanUtil pageBeanUtil = new PageBeanUtil(Integer.parseInt(page),Integer.parseInt(limit));
        User user = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,pageBeanUtil.getStart());
            ps.setInt(2,Integer.parseInt(limit));
            rs = ps.executeQuery();

            while(rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setCreate_time(rs.getString("create_time"));
                user.setImg(rs.getString("img"));
                user.setIs_del(rs.getInt("is_del"));
                user.setModify_time(rs.getString("modify_time"));
                user.setReal_name(rs.getString("real_name"));
                user.setType(rs.getInt("type"));
                lists.add(user);
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
    //总条数
    public int selectCount(Map map1){
        String real_name = (String) map1.get("real_name");
        String type = (String) map1.get("type");
        String username = (String) map1.get("username");

        Connection connection = DBHelper.getConnection();
        //2. 写sql
        String  sql = "  select count(*) total  from t_user  where 1=1  ";
        if (null!=real_name&&real_name.length()>0){
            sql = sql + " and real_name   like  '%"+real_name+"%'   ";
        }
        if (null!=type&&type.length()>0){
            sql = sql + " and type   =  "+type+"   ";
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
    //根据id查询
    public User selectId(Integer id){
        User user = null;
        Connection connection = DBHelper.getConnection();
        String sql="select * from t_user where id=?";
        PreparedStatement ps = null;
        ResultSet rs =null;
        try {
            ps = connection.prepareStatement(sql);
            System.out.println("ps = " + ps);
            ps.setInt(1,id);

            rs = ps.executeQuery();
            while(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setCreate_time(rs.getString("create_time"));
                user.setImg(rs.getString("img"));
                user.setIs_del(rs.getInt("is_del"));
                user.setModify_time(rs.getString("modify_time"));
                user.setReal_name(rs.getString("real_name"));
                user.setType(rs.getInt("type"));

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
        return user;
    }
    //修改
    public int updateUserId(Integer is_del,Integer userId){
        Connection connection = DBHelper.getConnection();
        String sql = "update t_user set is_del=? where id = ?";
        System.out.println("update sql = " + sql);
        PreparedStatement ps=null;
        ResultSet rs=null;
        int i = 0;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,is_del);
            ps.setInt(2,userId);

            i=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    //按照id删除

    public static void main(String[] args) {
        UserDao dao = new UserDao();
        //List<User> users = dao.selectAll();
        //for (User user:users) {
        //    System.out.println("user = " + user);
       // }
        //User user = new User();
       // user.setUsername("lulu");
        //user.setType(1);
        //user.setReal_name("璐璐");
        //user.setPassword("123456");
        //user.setModify_time("2013-09-07");
        //user.setIs_del(1);
        //user.setImg("xxxxx");
        //user.setCreate_time("2013-09-07");
        //int i = dao.addUser(user);
        //System.out.println("i = " + i);
        //int i = dao.deleteUser(62);
        //System.out.println("i = " + i);
//        User user = new User();
//        user.setId(11);
//        user.setUsername("xialu");
//        user.setType(1);
//        user.setReal_name("璐璐");
//        user.setPassword("123456");
//        user.setModify_time("2013-09-07");
//        user.setIs_del(1);
//        user.setImg("xxxxx");
//        user.setCreate_time("2013-09-07");
//        int i = dao.updateUser(user);
//        System.out.println("i = " + i);
//        User abc = dao.login("abc","123456");
//        System.out.println("abc = " + abc);
//        List<User> users = dao.selectAllByParam(map);
//        System.out.println("users = " + users);
//        System.out.println("users = " + users.size());
//        int i = dao.selectCount();
//        System.out.println("i = " + i);

    }

}
