package mapper;

import edu.wxu.pojo.Manager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerMapper {
    Connection conn;
    Statement stmt;
    //相关准备工作

    public ManagerMapper() throws Exception {
        //获取连接
        String url = "jdbc:mysql://127.0.0.1/librarysystem";
        String name = "root";
        String password = "159668";
        conn = DriverManager.getConnection(url,name,password);
        //获取执行sql的对象
        stmt = conn.createStatement();
    }

    //查询全部Manager
    public List<Manager> selectAll() throws Exception {
        //定义sql语句
        String sql = "select * from manager";
        //执行sql语句
        ResultSet resultSet = stmt.executeQuery(sql);
        //讲结果集封装到列表中
        List<Manager> lists = new ArrayList<>();
        Manager manager = null;
        while (resultSet.next()){
            int no = resultSet.getInt("no");
            String password = resultSet.getString("password");
            manager = new Manager();
            manager.setNo(no);
            manager.setPassword(password);
            lists.add(manager);
        }
        return lists;
    }

    //根据no和password查询
    public Manager selectByNoAndPassword(int No,String Password) throws SQLException {
        String sql = "select * from manager where no = "+ No + " and password = " + Password;
        ResultSet resultSet = stmt.executeQuery(sql);
        Manager manager = null;
        if (resultSet.next()){
            //有该id，封装并返回
            int no = resultSet.getInt("no");
            String password = resultSet.getString("password");
            manager = new Manager();
            manager.setNo(no);
            manager.setPassword(password);
        }
        return manager;
    }
}
