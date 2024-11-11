package test;

import edu.wxu.pojo.Manager;
import edu.wxu.pojo.Worker;
import mapper.WorkerMapper;

import java.sql.SQLException;
import java.util.List;

public class TestWorkerMapper {
    static WorkerMapper workerMapper;

    static {
        try {
            workerMapper = new WorkerMapper();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TestWorkerMapper() throws Exception {
    }

    public static void main(String[] args) throws Exception {
        //Test01();
        //Test02();
        Test03();
    }

    public static void Test01() throws Exception {
        List<Worker> workers = workerMapper.selectAll();
        System.out.println(workers);
    }

    public static void Test02() throws SQLException {
        Worker worker = workerMapper.selectByNoAndPassword(1, "123456");
        System.out.println(worker);
    }

    public static void Test03() throws SQLException {
        int i = workerMapper.deleteByNo(2);
        if (i > 0){
            System.out.println("删除成功");
        }
    }
}
