package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDB {
    public static void main(String[] args) throws Exception {
        //1.注册驱动
        //Class.forName("com.mysql.cj.jdbc.Driver");

        //2.获取连接
        String url = "jdbc:mysql://127.0.0.1/students";
        String name = "sherber";
        String password = "123456";
        Connection conn = DriverManager.getConnection(url,name,password);

        //3.定义sql
        String sql = "update manager set password = '123456' where no = 22308220;";

        //4.获取执行sql的对象
        Statement stmt = conn.createStatement();

        //5.执行sql
        stmt.executeUpdate(sql);
        //6.处理结果
        //7.释放资源
        stmt.close();
        conn.close();
    }
}
