package mapper;

import edu.wxu.pojo.Record;
import jdk.nashorn.internal.objects.annotations.Where;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecordsMapper {
    Connection conn = null;
    public RecordsMapper() throws Exception {
        //获取连接
        String url = "jdbc:mysql://127.0.0.1/librarysystem";
        String name = "root";
        String password = "159668";
        conn = DriverManager.getConnection(url,name,password);
    }

    //查询全部
    public List<Record> selectAll() throws Exception {
        String sql = "select * from records";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Record> records = new ArrayList<>();
        Record record = null;
        while (resultSet.next()){
            //封装
            record = new Record();
            record.setBookNo(resultSet.getInt("book_no"));
            record.setBookName(resultSet.getString("book_name"));
            record.setStudentNo(resultSet.getInt("student_no"));
            record.setStudentName(resultSet.getString("student_name"));
            record.setStatus(resultSet.getShort("status"));
            records.add(record);
        }
        return records;
    }

    //根据图书编号和学生编号修改记录
    public int updateByNo(int bookNo, int studentNo,String status) throws Exception {
        int numStatus = 0;
        if ("借阅中".equals(status)){
            numStatus = 0;
        }else {
            numStatus = 1;
        }
        String sql = "update records set status = ? where book_no = ? and student_no = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,numStatus);
        pstmt.setInt(2,bookNo);
        pstmt.setInt(3,studentNo);
        int result = pstmt.executeUpdate();
        return result;
    }

    //根据图书编号和学生编号删除记录
    public int deleteByNo(int bookNo, int studentNo) throws Exception {
        String sql = "delete from records where book_no = ? and student_no = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,bookNo);
        pstmt.setInt(2,studentNo);
        int result = pstmt.executeUpdate();
        return result;
    }

    //根据图书编号删除记录
    public int deleteByBookNo(int bookNo) throws Exception {
        String sql = "delete from records where book_no = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,bookNo);
        int result = pstmt.executeUpdate();
        return result;
    }

    //根据学生编号查询
    public List<Record> selectByStudentNo(int studentNo) throws Exception {
        String sql = "select * from records where student_no = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,studentNo);
        ResultSet resultSet = pstmt.executeQuery();
        Record record = null;
        List<Record> list = new ArrayList<>();
        while (resultSet.next()){
            //封装
            record = new Record();
            record.setStudentNo(resultSet.getInt("student_no"));
            record.setStudentName(resultSet.getString("student_name"));
            record.setBookNo(resultSet.getInt("book_no"));
            record.setBookName(resultSet.getString("book_name"));
            record.setStatus(resultSet.getShort("status"));

            list.add(record);
        }
        return list;
    }

    //根据图书编号查询
    public Record selectByBookNo(int bookNo) throws Exception {
        String sql = "select * from records where book_no = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,bookNo);
        ResultSet resultSet = pstmt.executeQuery();
        Record record = null;
        if (resultSet.next()){
            //封装
            record = new Record();
            record.setStudentNo(resultSet.getInt("student_no"));
            record.setStudentName(resultSet.getString("student_name"));
            record.setBookNo(resultSet.getInt("book_no"));
            record.setBookName(resultSet.getString("book_name"));
            record.setStatus(resultSet.getShort("status"));
        }
        return record;
    }

    //添加
    public int insert(int bookNo,String bookName,int studentNo, String studentName,String status) throws Exception {
        String sql = "insert into records values (?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,bookNo);
        pstmt.setString(2,bookName);
        pstmt.setInt(3,studentNo);
        pstmt.setString(4,studentName);
        int numStatus = 0;
        if ("借阅中".equals(status)){
            numStatus = 0;
        }else {
            numStatus = 1;
        }
        pstmt.setInt(5,numStatus);
        int result = pstmt.executeUpdate();
        return result;
    }
}
