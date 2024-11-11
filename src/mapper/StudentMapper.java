package mapper;

import edu.wxu.pojo.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentMapper {
    Connection conn;
    Statement stmt;
    //相关准备工作

    public StudentMapper() throws Exception {
        //获取连接
        String url = "jdbc:mysql://127.0.0.1/librarysystem";
        String name = "root";
        String password = "159668";
        conn = DriverManager.getConnection(url,name,password);
        //获取执行sql的对象
        stmt = conn.createStatement();
    }

    //查询全部Student
    public List<Student> selectAll() throws Exception {
        //定义sql语句
        String sql = "select * from student";
        //执行sql语句
        ResultSet resultSet = stmt.executeQuery(sql);
        //讲结果集封装到列表中
        List<Student> lists = new ArrayList<>();
        Student student = null;
        while (resultSet.next()){
            int no = resultSet.getInt("no");
            String password = resultSet.getString("password");
            String name = resultSet.getString("name");
            short gender = resultSet.getShort("gender");
            int age = resultSet.getInt("age");
            student = new Student();
            student.setNo(no);
            student.setPassword(password);
            student.setName(name);
            student.setGender(gender);
            student.setAge(age);
            lists.add(student);
        }
        return lists;
    }

    //根据no查询
    public Student selectByNo(int No) throws SQLException {
        String sql = "select * from student where no = "+ No;
        ResultSet resultSet = stmt.executeQuery(sql);
        Student student = null;
        if (resultSet.next()){
            //有该id，封装并返回
            int no = resultSet.getInt("no");
            String password = resultSet.getString("password");
            String name = resultSet.getString("name");
            short gender = resultSet.getShort("gender");
            int age = resultSet.getInt("age");
            student = new Student();
            student.setNo(no);
            student.setPassword(password);
            student.setName(name);
            student.setGender(gender);
            student.setAge(age);
        }
        return student;
    }

    //根据no和password查询
    public Student selectByNoAndPassword(int No,String Password) throws SQLException {
        String sql = "select * from student where no = "+ No + " and password = " + Password;
        ResultSet resultSet = stmt.executeQuery(sql);
        Student student = null;
        if (resultSet.next()){
            //有该id，封装并返回
            int no = resultSet.getInt("no");
            String password = resultSet.getString("password");
            String name = resultSet.getString("name");
            short gender = resultSet.getShort("gender");
            int age = resultSet.getInt("age");
            student = new Student();
            student.setNo(no);
            student.setPassword(password);
            student.setName(name);
            student.setGender(gender);
            student.setAge(age);
        }
        return student;
    }

    //根据no删除学生
    public int deleteByNo(int no) throws SQLException {
        String sql = "delete from student where no = " + no;
        int result = stmt.executeUpdate(sql);
        return result;
    }

    //根据no删除多个学生
    public int deleteByNos(List<Integer> nos) throws SQLException {
        int result = 0;
        for (Integer no : nos) {
            String sql = "delete from student where no = " + no;
            result += stmt.executeUpdate(sql);
        }
        return result;
    }

    //根据no修改学生
    public int updateByNo(int no,String name,String password,String genderStr,int age) throws SQLException {
        int gender = 1;
        if ("男".equals(genderStr)){
            gender = 1;
        }else {
            gender = 2;
        }
        String sql = "update student set name='"+name+"',password='"+password+"',"+"gender="+gender+",age="+age+" where no = "+no;
        System.out.println(sql);
        int result = stmt.executeUpdate(sql);
        System.out.println(result);
        return result;
    }

    public int insert(int no,String name,String password,int gender,int age) throws SQLException {
        String sql = "insert into student values (" + no + ",'" + name + "','" + password + "',"+ gender + "," + age + ")";
        System.out.println(sql);
        //insert into student values(no,name,password,gender,age)
        int result = stmt.executeUpdate(sql);
        return result;
    }
}
