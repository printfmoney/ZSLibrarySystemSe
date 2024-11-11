package mapper;

import edu.wxu.pojo.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookMapper {
    Connection conn = null;
    public BookMapper() throws Exception {
        //获取连接
        String url = "jdbc:mysql://127.0.0.1/librarysystem";
        String name = "root";
        String password = "159668";
        conn = DriverManager.getConnection(url,name,password);
    }

    //查询全部
    public List<Book> selectAll() throws Exception {
        //1.定义sql
        String sql = "select * from book";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Book> books = new ArrayList<>();
        Book book = null;
        while (resultSet.next()){
            //封装
            book = new Book();
            book.setNo(resultSet.getInt("no"));
            book.setName(resultSet.getString("name"));
            book.setAuthor(resultSet.getString("author"));
            book.setPrice(resultSet.getInt("price"));
            book.setPublisher(resultSet.getString("publisher"));
            book.setStatus(resultSet.getShort("status"));

            books.add(book);
        }
        return books;
    }

    //根据编号查询
    public Book selectByNo(int no) throws Exception {
        //1.定义sql
        String sql = "select * from book where no = ?";
        //2.创建一个执行sql的对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //3.给问号赋值
        pstmt.setInt(1,no);
        //执行sql
        ResultSet resultSet = pstmt.executeQuery();
        Book book = null;
        if (resultSet.next()){
            //找到了，封装
            book = new Book();
            book.setNo(resultSet.getInt("no"));
            book.setName(resultSet.getString("name"));
            book.setAuthor(resultSet.getString("author"));
            book.setPublisher(resultSet.getString("publisher"));
            book.setStatus(resultSet.getShort("status"));
            book.setPrice(resultSet.getInt("price"));
        }
        return book;
    }

    //根据编号修改
    public int updateByNo(int no,String newName, String newAuthor,String newPublish,String newStatus,int newPrice) throws Exception {
        String sql = "update book set name = ?,author = ?,publisher = ?,status = ?,price = ? where no = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,newName);
        pstmt.setString(2,newAuthor);
        pstmt.setString(3,newPublish);
        short status;
        if ("借阅中".equals(newStatus)){
            status = 0;
        }else {
            status = 1;
        }
        pstmt.setInt(4,status);
        pstmt.setInt(5,newPrice);
        pstmt.setInt(6,no);
        int result = pstmt.executeUpdate();
        return result;
    }

    //根据编号删除
    public int deleteByNo(int no) throws Exception {
        String sql = "delete from book where no = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,no);
        int result = pstmt.executeUpdate();
        return result;
    }

    //添加
    public int insert(int no,String name,String author,String publisher,String status,int price) throws Exception {
        String sql = "insert into book values (?,?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,no);
        pstmt.setString(2,name);
        pstmt.setString(3,author);
        pstmt.setString(4,publisher);
        int numStatus = 0;
        if ("借阅中".equals(status)){
            numStatus = 0;
        }else {
            numStatus = 1;
        }
        pstmt.setInt(5,numStatus);
        pstmt.setInt(6,price);
        int result = pstmt.executeUpdate();
        return result;
    }
}
