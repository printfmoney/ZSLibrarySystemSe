package test;

import mapper.StudentMapper;

public class TestStudentMapper {
    public static void main(String[] args) throws Exception {
        Test01();
    }

    public static void Test01() throws Exception {
        StudentMapper studentMapper;
        studentMapper = new StudentMapper();
        studentMapper.insert(5,"zhangsan","123456",1,20);
    }
}
