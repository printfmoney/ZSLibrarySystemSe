package test;

import edu.wxu.pojo.Manager;
import mapper.ManagerMapper;

import java.sql.SQLException;
import java.util.List;

public class TestManagerMapper {
    static ManagerMapper managerMapper;

    static {
        try {
            managerMapper = new ManagerMapper();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws Exception {
        //Test01();
        Test02();
    }

    private static void Test01() throws Exception {
        List<Manager> managers = managerMapper.selectAll();
        System.out.println(managers);
    }

    private static void Test02() throws SQLException {
        Manager manager = managerMapper.selectByNoAndPassword(22308220,"123456");
        System.out.println(manager);
    }
}
