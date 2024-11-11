package test;

import javax.naming.Name;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectSqlServer {
    public static void main(String[] args) {
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String URL = "jdbc:sqlserver://localhost:1433;DatabaseName=Test";
        String userName = "sa";
        String password = "123456";
        Connection conn = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(URL,userName,password);
            System.out.println("数据库连接成功！");
        } catch (Exception e) {
            System.out.println("连接失败");
            throw new RuntimeException(e);
        }
    }
}
