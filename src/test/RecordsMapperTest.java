package test;

import mapper.RecordsMapper;

public class RecordsMapperTest {
    public static void main(String[] args) throws Exception {
        //Test01();
        //Test02();
        Test03();
    }

    public static void Test01() throws Exception {
        RecordsMapper recordsMapper = new RecordsMapper();
        int result = recordsMapper.updateByNo(1, 1, "借阅中");
        if (result > 0){
            System.out.println("修改完成");
        }
    }

    public static void Test02() throws Exception {
        RecordsMapper recordsMapper = new RecordsMapper();
        int result = recordsMapper.deleteByNo(1, 1);
        if (result > 0){
            System.out.println("删除成功！");
        }
    }

    public static void Test03() throws Exception {
        RecordsMapper recordsMapper = new RecordsMapper();
        int insert = recordsMapper.insert(1, "java", 1, "吕林", "借阅中");
        System.out.println(insert);
    }
}
