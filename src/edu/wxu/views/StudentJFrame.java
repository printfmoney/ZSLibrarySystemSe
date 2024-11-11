package edu.wxu.views;

import edu.wxu.pojo.Book;
import edu.wxu.pojo.Record;
import edu.wxu.pojo.Student;
import edu.wxu.pojo.Worker;
import mapper.BookMapper;
import mapper.RecordsMapper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class StudentJFrame extends JFrame implements ActionListener {
    //登录的学生
    Student student = null;
    //界面是正在查看图书还是预约记录 1图书 2预约记录
    private int status = 1;
    //菜单的搭建
    private JMenuBar menuBar;
    private JMenu menu;
    //退出
    private JMenuItem exitMenu;
    private JMenuItem selectBooks;
    private JMenuItem selectRecords;
    //表格组件
    private DefaultTableModel dtm;
    private JTable table;
    public JScrollPane jScrollPane;
    //按钮
    private JLabel noLabelSearch = new JLabel("图书编号：");
    private JTextField noTextFieldSearch = new JFormattedTextField();
    private JButton searchButton = new JButton("查询");
    private JButton searchAllButton = new JButton("查看全部");
    public StudentJFrame(Student student) throws Exception {
        this.student = student;
        initJFrame();
        initMenu();
        initButtons();
        initBooksTable();
        this.setVisible(true);
    }

    private void initMenu() {
        //管理菜单设置
        menuBar = new JMenuBar();
        //menuBar.setPreferredSize(new Dimension(100,50));
        menu = new JMenu("管理");
        menu.setFont(new Font("宋体",Font.BOLD,30));
        selectBooks = new JMenuItem("查看图书");
        selectBooks.setFont(new Font("宋体",Font.BOLD,30));
        selectRecords = new JMenuItem("查看预约记录");
        selectRecords.setFont(new Font("宋体",Font.BOLD,30));
        //退出
        exitMenu = new JMenuItem("退出");
        exitMenu.setFont(new Font("宋体",Font.BOLD,30));
        //添加事件
        selectBooks.addActionListener(this);
        selectRecords.addActionListener(this);
        exitMenu.addActionListener(this);
        //封装
        menu.add(selectBooks);
        menu.add(selectRecords);
        menuBar.add(menu);
        menuBar.add(exitMenu);
        this.setJMenuBar(menuBar);
    }

    private void initButtons() {
        //标签和文本框设置
        noLabelSearch.setBounds(100,150,150,60);
        noLabelSearch.setFont(new Font("宋体",Font.BOLD,25));
        this.add(noLabelSearch);
        noTextFieldSearch.setBounds(220,165,150,30);
        noTextFieldSearch.setFont(new Font("宋体",Font.BOLD,25));
        this.add(noTextFieldSearch);
        //按钮设置
        //查找
        searchButton.setBounds(380,150,150,60);
        searchButton.setFont(new Font("宋体",Font.BOLD,25));
        searchButton.addActionListener(this);
        this.add(searchButton);
        //查询全部按钮
        searchAllButton.setBounds(580,150,150,60);
        searchAllButton.setFont(new Font("宋体",Font.BOLD,25));
        searchAllButton.addActionListener(this);
        this.add(searchAllButton);
    }

    public void initBooksTable() throws Exception {
        //表格设置
        BookMapper bookMapper = new BookMapper();
        dtm = new DefaultTableModel();
        String[] booksHeaders = {"编号","书名","作者","出版商","状态","价格"};
        //添加表头
        for (String bookHeader : booksHeaders) {
            dtm.addColumn(bookHeader);
        }
        //获取Student数据
        java.util.List<Book> books = bookMapper.selectAll();
        //给表格添加worker数据
        for (Book book : books) {
            String status;
            if (book.isStatus() == 0){
                status = "借阅中";
            }else {
                status = "已归还";
            }
            dtm.addRow(new Object[]{book.getNO(),book.getName(),book.getAuthor(),book.getPublisher(),status,book.getPrice()});
        }
        //创建表格
        table = new MyTable(dtm);
        //表头和表格大小字体设置
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(),30));
        tableHeader.setFont(new Font("宋体",Font.BOLD,25));

        table.setRowHeight(30);
        table.setFont(new Font("宋体",Font.BOLD,25));
        //将表格放到容器中
        jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(100,250,1000,600);
        this.add(jScrollPane);
    }
    public void initRecordsTable() throws Exception {
        //表格设置
        RecordsMapper recordsMapper = new RecordsMapper();
        dtm = new DefaultTableModel();
        String[] recordsHeaders = {"图书编号","书名","学生编号","学生姓名","状态"};
        //添加表头
        for (String recordHeader : recordsHeaders) {
            dtm.addColumn(recordHeader);
        }
        //获取Record数据
        List<Record> records = recordsMapper.selectByStudentNo(student.getNo());
        //给表格添加数据
        for (Record record : records) {
            String status;
            if (record.getStatus() == 0){
                status = "借阅中";
            }else {
                status = "已归还";
            }
            dtm.addRow(new Object[]{record.getBookNo(),record.getBookName(),record.getStudentNo(),record.getStudentName(),status});
        }
        //创建表格
        table = new MyTable(dtm);
        //表头和表格大小字体设置
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(),30));
        tableHeader.setFont(new Font("宋体",Font.BOLD,25));

        table.setRowHeight(30);
        table.setFont(new Font("宋体",Font.BOLD,25));
        //将表格放到容器中
        jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(100,250,1000,600);
        this.add(jScrollPane);
    }

    private void initJFrame() {
        //大小
        this.setSize(1200,1000);
        //标题
        this.setTitle("图书管理系统 v1.0 -图书管理员界面");
        //置顶
        this.setAlwaysOnTop(true);
        //居中
        this.setLocationRelativeTo(null);
        //关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //默认组件设置
        this.setLayout(null);
        //不能最大化
        this.setResizable(false);
        //设置背景为白色
        this.getContentPane().setBackground(Color.WHITE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //查看图书
        if (e.getSource().equals(selectBooks)){
            if (status == 1){
                //已经在看图书了
                return;
            }else {
                //从看记录换成看图书
                remove(jScrollPane);
                try {
                    initBooksTable();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                status = 1;
            }
        }
        //查看记录
        if (e.getSource().equals(selectRecords)){
            //查看记录
            if (status == 2){
                //已经在看记录
                return;
            }else {
                //从图书转为记录
                remove(jScrollPane);
                try {
                    initRecordsTable();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                status = 2;
            }
        }
        //退出
        if (e.getSource().equals(exitMenu)){
            //退出
            this.dispose();
            new LoginJFrame();
        }
        //查询
        if (e.getSource().equals(searchButton)){
            if (status == 1){
                //查询图书
                int no = Integer.parseInt(noTextFieldSearch.getText());
                try {
                    BookMapper bookMapper = new BookMapper();
                    Book book = bookMapper.selectByNo(no);
                    dtm.setRowCount(0);
                    String status;
                    if (book.isStatus() == 0){
                        status = "借阅中";
                    }else {
                        status = "已归还";
                    }
                    dtm.addRow(new Object[]{book.getNO(),book.getName(),book.getAuthor(),book.getPublisher(),status,book.getPrice()});
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }else {
                //查询记录
                try {
                    RecordsMapper recordsMapper = new RecordsMapper();
                    int no = Integer.parseInt(noTextFieldSearch.getText());
                    Record record = recordsMapper.selectByBookNo(no);
                    dtm.setRowCount(0);
                    dtm.addRow(new Object[]{record.getBookNo(),record.getBookName(),record.getStudentNo(),record.getStudentName(),record.getStatus()});
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }


        //查看全部
        if (e.getSource().equals(searchAllButton)) {
            if (status == 1) {
                //查看全部图书
                remove(jScrollPane);
                try {
                    initBooksTable();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                //查看全部记录
                remove(jScrollPane);
                try {
                    initRecordsTable();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
