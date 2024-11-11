package test;

import edu.wxu.pojo.Book;
import mapper.BookMapper;

import java.sql.SQLException;
import java.util.List;


public class BookMapperTest {
    public static void main(String[] args) throws Exception {
        //Test01();
        //Test02();
        //Test03();
        //Test04();
        Test05();
    }

    public static void Test01() throws Exception {
        BookMapper bookMapper = new BookMapper();
        List<Book> books = bookMapper.selectAll();
        System.out.println(books);
    }

    public static void Test02() throws Exception {
        BookMapper bookMapper = new BookMapper();
        bookMapper.updateByNo(1,"钢铁是怎样练成的","吕林","新华书社","已借出",30);
    }

    public static void Test03() throws Exception {
        BookMapper bookMapper = new BookMapper();
        bookMapper.deleteByNo(4);
    }

    public static void Test04() throws Exception {
        BookMapper bookMapper = new BookMapper();
        Book book = bookMapper.selectByNo(1);
        if (book != null){
            System.out.println("找到了");
        }else {
            System.out.println("没找到");
        }
    }

    public static void Test05() throws Exception {
        BookMapper bookMapper = new BookMapper();
        int insert = bookMapper.insert(3, "红星照耀中国", "吕林", "新华书社", "借阅中", 100);
        if (insert > 0){
            System.out.println("添加成功");
        }
    }
}
