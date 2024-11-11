package mapper;

import edu.wxu.pojo.Manager;
import edu.wxu.pojo.Student;
import edu.wxu.pojo.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkerMapper {
    Connection conn;
    Statement stmt;
    //相关准备工作

    public WorkerMapper() throws Exception {
        //获取连接
        String url = "jdbc:mysql://127.0.0.1/librarysystem";
        String name = "root";
        String password = "159668";
        conn = DriverManager.getConnection(url,name,password);
        //获取执行sql的对象
        stmt = conn.createStatement();
    }

    //查询全部Manager
    public List<Worker> selectAll() throws Exception {
        //定义sql语句
        String sql = "select * from worker";
        //执行sql语句
        ResultSet resultSet = stmt.executeQuery(sql);
        //讲结果集封装到列表中
        List<Worker> lists = new ArrayList<>();
        Worker worker = null;
        while (resultSet.next()){
            int no = resultSet.getInt("no");
            String password = resultSet.getString("password");
            String name = resultSet.getString("name");
            worker = new Worker();
            worker.setNo(no);
            worker.setPassword(password);
            worker.setName(name);
            lists.add(worker);
        }
        return lists;
    }

    //根据no查询
    public Worker selectByNo(int No) throws SQLException {
        String sql = "select * from worker where no = "+ No;
        ResultSet resultSet = stmt.executeQuery(sql);
        Worker worker = null;
        if (resultSet.next()){
            //有该id，封装并返回
            int no = resultSet.getInt("no");
            String password = resultSet.getString("password");
            String name = resultSet.getString("name");
            worker = new Worker();
            worker.setNo(no);
            worker.setPassword(password);
            worker.setName(name);
        }
        return worker;
    }

    //根据no和password查询
    public Worker selectByNoAndPassword(int No,String Password) throws SQLException {
        String sql = "select * from worker where no = "+ No + " and password = " + Password;
        ResultSet resultSet = stmt.executeQuery(sql);
        Worker worker = null;
        if (resultSet.next()){
            //有该id，封装并返回
            int no = resultSet.getInt("no");
            String password = resultSet.getString("password");
            String name = resultSet.getString("name");
            worker = new Worker();
            worker.setNo(no);
            worker.setPassword(password);
        }
        return worker;
    }

    //根据no删除Worker
    public int deleteByNo(int no) throws SQLException {
        String sql = "delete from worker where no = " + no;
        int result = stmt.executeUpdate(sql);
        return result;
    }

    //根据no删除多个Worker
    public int deleteByNos(List<Integer> nos) throws SQLException {
        int result = 0;
        for (Integer no : nos) {
            String sql = "delete from worker where no = " + no;
            result += stmt.executeUpdate(sql);
        }
        return result;
    }

    //根据no修改Worker
    public int updateByNo(int no,String name,String password) throws SQLException {
        String sql = "update worker set name = '" + name + "',password = '" + password + "' where no = " + no;
        System.out.println(sql);
        int result = stmt.executeUpdate(sql);
        return result;
    }

    public int insert(int no,String name,String password) throws SQLException {
        String sql = "insert into worker values (" + no + ",'" + name + "','" + password + "')";
        System.out.println(sql);
        int result = stmt.executeUpdate(sql);
        return result;
    }
}
