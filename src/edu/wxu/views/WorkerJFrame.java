package edu.wxu.views;

import edu.wxu.pojo.Book;
import edu.wxu.pojo.Record;
import edu.wxu.pojo.Student;
import edu.wxu.pojo.Worker;
import jdk.nashorn.internal.scripts.JO;
import mapper.BookMapper;
import mapper.RecordsMapper;
import mapper.StudentMapper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class WorkerJFrame extends JFrame implements ActionListener {
    //界面是正在管理图书还是预约记录 1图书 2预约记录
    private int status = 1;
    //菜单的搭建
    private JMenuBar menuBar;
    private JMenu menu;
    //退出
    private JMenuItem exitMenu;
    private JMenuItem manageBooks;
    private JMenuItem manageRecords;
    //表格组件
    private DefaultTableModel dtm;
    private JTable table;
    public JScrollPane jScrollPane;
    //左边选中人信息的标签及文本框
    private JLabel noLabel = new JLabel("编号：");
    private JTextField noTextField = new JFormattedTextField();
    private JLabel nameLabel = new JLabel("书名：");
    private JTextField nameTextField = new JFormattedTextField();
    private JLabel authorLabel = new JLabel("作者：");
    private JTextField authorTextField = new JFormattedTextField();
    private JLabel publisherLabel = new JLabel("出版商：");
    private JTextField publisherTextField = new JFormattedTextField();
    private JLabel statusLabel = new JLabel("状态：");
    private JTextField statusTextField = new JFormattedTextField();
    private JLabel priceLabel = new JLabel("价格：");
    private JTextField priceTextField = new JFormattedTextField();

    private JLabel bookNo = new JLabel("图书编号：");
    private JTextField bookNoText = new JFormattedTextField();
    private JLabel bookName = new JLabel("书名：");
    private JTextField bookNameText = new JFormattedTextField();
    private JLabel studentNo = new JLabel("学生编号：");
    private JTextField studentNoText = new JFormattedTextField();
    private JLabel studentName = new JLabel("学生姓名：");
    private JTextField studentNameText = new JFormattedTextField();
    //按钮
    private JButton modifyButton = new JButton("修改");
    private JButton deleteButton = new JButton("删除");
    private JButton addButton = new JButton("添加");
    private JButton batchDeleteButton = new JButton("批量删除");
    private JLabel noLabelSearch = new JLabel("编号：");
    private JTextField noTextFieldSearch = new JFormattedTextField();
    private JComboBox<String> noClassify = new JComboBox<>();
    private JButton searchButton = new JButton("查询");
    private JButton searchAllButton = new JButton("查看全部");
    //登录的图书管理员
    Worker worker = null;
    public WorkerJFrame(Worker worker) throws Exception {
        this.worker = worker;
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
        manageBooks = new JMenuItem("管理图书");
        manageBooks.setFont(new Font("宋体",Font.BOLD,30));
        manageRecords = new JMenuItem("管理预约记录");
        manageRecords.setFont(new Font("宋体",Font.BOLD,30));
        //退出
        exitMenu = new JMenuItem("退出");
        exitMenu.setFont(new Font("宋体",Font.BOLD,30));
        //添加事件
        manageBooks.addActionListener(this);
        manageRecords.addActionListener(this);
        exitMenu.addActionListener(this);
        //封装
        menu.add(manageBooks);
        menu.add(manageRecords);
        menuBar.add(menu);
        menuBar.add(exitMenu);
        this.setJMenuBar(menuBar);
    }

    private void initButtons() {
        //按钮设置
        //修改
        modifyButton.setBounds(10,780,150,60);
        modifyButton.setFont(new Font("宋体",Font.BOLD,25));
        modifyButton.addActionListener(this);
        this.add(modifyButton);
        //删除
        deleteButton.setBounds(240,780,150,60);
        deleteButton.setFont(new Font("宋体",Font.BOLD,25));
        deleteButton.addActionListener(this);
        this.add(deleteButton);
        //添加
        addButton.setBounds(1050,150,150,60);
        addButton.setFont(new Font("宋体",Font.BOLD,25));
        addButton.addActionListener(this);
        this.add(addButton);
        //批量删除
        batchDeleteButton.setBounds(1250,150,150,60);
        batchDeleteButton.setFont(new Font("宋体",Font.BOLD,25));
        batchDeleteButton.addActionListener(this);
        this.add(batchDeleteButton);
        //查找
        searchButton.setBounds(750,150,150,60);
        searchButton.setFont(new Font("宋体",Font.BOLD,25));
        searchButton.addActionListener(this);
        this.add(searchButton);
        //查询全部按钮
        searchAllButton.setBounds(750,50,150,60);
        searchAllButton.setFont(new Font("宋体",Font.BOLD,25));
        searchAllButton.addActionListener(this);
        this.add(searchAllButton);
    }

    public void initBooksTable() throws Exception {
        initBooksTableSwings();
        //表格设置
        BookMapper bookMapper = new BookMapper();
        dtm = new DefaultTableModel();
        String[] booksHeaders = {"编号","书名","作者","出版商","状态","价格"};
        //添加表头
        for (String bookHeader : booksHeaders) {
            dtm.addColumn(bookHeader);
        }
        //获取Student数据
        List<Book> books = bookMapper.selectAll();
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
        jScrollPane.setBounds(400,250,1000,600);
        this.add(jScrollPane);
        //给表格添加事件
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //获取被点到的行的数组
                int[] rows = table.getSelectedRows();
                //左边文本框只展示最后一个被点到的
                noTextField.setText(table.getValueAt(rows[0],0) + "");
                nameTextField.setText(table.getValueAt(rows[0],1) + "");
                authorTextField.setText(table.getValueAt(rows[0],2) + "");
                publisherTextField.setText(table.getValueAt(rows[0],3)+"");
                statusTextField.setText(table.getValueAt(rows[0],4)+"");
                priceTextField.setText(table.getValueAt(rows[0],5)+"");
            }
        });
    }
    public void initRecordsTable() throws Exception {
        initRecordsTableSwings();
        //表格设置
        RecordsMapper recordsMapper = new RecordsMapper();
        dtm = new DefaultTableModel();
        String[] recordsHeaders = {"图书编号","书名","学生编号","学生姓名","状态"};
        //添加表头
        for (String recordHeader : recordsHeaders) {
            dtm.addColumn(recordHeader);
        }
        //获取Student数据
        List<Record> records = recordsMapper.selectAll();
        //给表格添加worker数据
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
        jScrollPane.setBounds(400,250,1000,600);
        this.add(jScrollPane);
        //给表格添加事件
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //获取被点到的行的数组
                int[] rows = table.getSelectedRows();
                //左边文本框只展示最后一个被点到的
                bookNoText.setText(table.getValueAt(rows[0],0) + "");
                bookNameText.setText(table.getValueAt(rows[0],1) + "");
                studentNoText.setText(table.getValueAt(rows[0],2) + "");
                studentNameText.setText(table.getValueAt(rows[0],3) + "");
                statusTextField.setText(table.getValueAt(rows[0],4) + "");
            }
        });
    }

    private void initRecordsTableSwings() {
        //bookNo bookName studentNo studentName status
        //标签及文本框设置
        bookNo.setBounds(40,270,200,50);
        bookNo.setFont(new Font("宋体",Font.BOLD,25));
        this.add(bookNo);
        bookNoText.setBounds(160,280,200,35);
        bookNoText.setFont(new Font("宋体",Font.BOLD,25));
        bookNoText.setEnabled(false);
        this.add(bookNoText);

        studentNo.setBounds(40,430,200,50);
        studentNo.setFont(new Font("宋体",Font.BOLD,25));
        this.add(studentNo);
        studentNoText.setBounds(160,440,200,35);
        studentNoText.setFont(new Font("宋体",Font.BOLD,25));
        studentNoText.setEnabled(false);
        this.add(studentNoText);

        bookName.setBounds(40,350,100,50);
        bookName.setFont(new Font("宋体",Font.BOLD,25));
        this.add(bookName);
        bookNameText.setBounds(160,360,200,35);
        bookNameText.setFont(new Font("宋体",Font.BOLD,25));
        bookNameText.setEnabled(false);
        this.add(bookNameText);

        studentName.setBounds(40,510,150,50);
        studentName.setFont(new Font("宋体",Font.BOLD,25));
        this.add(studentName);
        studentNameText.setBounds(160,520,200,35);
        studentNameText.setFont(new Font("宋体",Font.BOLD,25));
        studentNameText.setEnabled(false);
        this.add(studentNameText);

        statusLabel.setBounds(40,590,100,50);
        statusLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(statusLabel);
        statusTextField.setBounds(160,600,200,35);
        statusTextField.setFont(new Font("宋体",Font.BOLD,25));
        this.add(statusTextField);

        //查询标签和文本框
        noClassify.removeAllItems();
        noClassify.addItem("学生编号");
        noClassify.addItem("图书编号");
        noClassify.setBounds(360,160,150,30);
        noClassify.setFont(new Font("宋体",Font.BOLD,25));
        this.add(noClassify);
        noTextFieldSearch.setBounds(520,160,200,35);
        noTextFieldSearch.setFont(new Font("宋体",Font.BOLD,25));
        this.add(noTextFieldSearch);
    }


    //初始化书本表格左边的数据
    private void initBooksTableSwings() {
        //no name author publisher status price
        //标签及文本框设置
        noLabel.setBounds(50,270,100,50);
        noLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(noLabel);
        noTextField.setBounds(150,280,200,35);
        noTextField.setFont(new Font("宋体",Font.BOLD,25));
        noTextField.setEnabled(false);
        this.add(noTextField);

        nameLabel.setBounds(50,350,100,50);
        nameLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(nameLabel);
        nameTextField.setBounds(150,360,200,35);
        nameTextField.setFont(new Font("宋体",Font.BOLD,25));
        this.add(nameTextField);

        authorLabel.setBounds(50,430,100,50);
        authorLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(authorLabel);
        authorTextField.setBounds(150,440,200,35);
        authorTextField.setFont(new Font("宋体",Font.BOLD,25));
        this.add(authorTextField);

        publisherLabel.setBounds(50,510,150,50);
        publisherLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(publisherLabel);
        publisherTextField.setBounds(150,520,200,35);
        publisherTextField.setFont(new Font("宋体",Font.BOLD,25));
        this.add(publisherTextField);

        statusLabel.setBounds(50,590,100,50);
        statusLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(statusLabel);
        statusTextField.setBounds(150,600,200,35);
        statusTextField.setFont(new Font("宋体",Font.BOLD,25));
        this.add(statusTextField);

        priceLabel.setBounds(50,670,100,50);
        priceLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(priceLabel);
        priceTextField.setBounds(150,680,200,35);
        priceTextField.setFont(new Font("宋体",Font.BOLD,25));
        this.add(priceTextField);

        //查询标签和文本框
        noLabelSearch.setBounds(430,160,150,30);
        noLabelSearch.setFont(new Font("宋体",Font.BOLD,25));
        this.add(noLabelSearch);
        noTextFieldSearch.setBounds(520,160,200,35);
        noTextFieldSearch.setFont(new Font("宋体",Font.BOLD,25));
        this.add(noTextFieldSearch);
    }

    private void initJFrame() {
        //大小
        this.setSize(1500,1000);
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

    public void clearTextAndLabel(){
        if (status == 1){
            //清空图书的文本框
            noTextField.setText("");
            nameTextField.setText("");
            authorTextField.setText("");
            publisherTextField.setText("");
            statusTextField.setText("");
            priceTextField.setText("");
            noTextFieldSearch.setText("");
        }else {
            //清空记录的文本框
            bookNoText.setText("");
            bookNameText.setText("");
            studentNoText.setText("");
            studentNameText.setText("");
            statusTextField.setText("");
        }
    }

    private void removeTextAndLabel(){
        if (status == 1){
            //由图书转为记录，移除图书
            remove(noLabel);
            remove(noTextField);
            remove(nameLabel);
            remove(nameTextField);
            remove(authorLabel);
            remove(authorTextField);
            remove(publisherLabel);
            remove(publisherTextField);
            remove(statusLabel);
            remove(statusTextField);
            remove(priceLabel);
            remove(priceTextField);
            remove(noLabelSearch);
            remove(noTextFieldSearch);
        }else {
            //由记录转为图书，移除记录
            remove(bookNo);
            remove(bookNoText);
            remove(studentNo);
            remove(studentNoText);
            remove(bookName);
            remove(bookNameText);
            remove(studentName);
            remove(studentNameText);
            remove(statusLabel);
            remove(statusTextField);
            remove(noClassify);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //管理图书
        if (e.getSource().equals(manageBooks)){
            if (status == 1){
                return;
            }else {
                clearTextAndLabel();
                removeTextAndLabel();
                try {
                    remove(jScrollPane);
                    initBooksTable();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                status = 1;
            }
        }
        //管理预约记录
        if (e.getSource().equals(manageRecords)) {
            if (status == 2){
                return;
            }else {
                clearTextAndLabel();
                removeTextAndLabel();
                try {
                    remove(jScrollPane);
                    initRecordsTable();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
            status = 2;
        }
        //退出
        if (e.getSource().equals(exitMenu)) {
            this.dispose();
            new LoginJFrame();
        }
        //修改
        if (e.getSource().equals(modifyButton)){
            if (status == 1){
                //修改图书
                //noTextField  nameTextField  authorTextField  publisherTextField statusTextField priceTextField
                int no = Integer.parseInt(noTextField.getText());
                String newName = nameTextField.getText();
                String newAuthor = authorTextField.getText();
                String newPublisher = publisherTextField.getText();
                String newStatus = statusTextField.getText();
                int newPrice = Integer.parseInt(priceTextField.getText());
                BookMapper bookMapper;
                try {
                    bookMapper = new BookMapper();
                    int result = bookMapper.updateByNo(no,newName,newAuthor,newPublisher,newStatus,newPrice);
                    if (result > 0){
                        remove(jScrollPane);
                        initBooksTable();
                        JOptionPane.showMessageDialog(this,"修改成功！");
                    }else {
                        JOptionPane.showMessageDialog(this,"修改失败！");
                    }
                } catch (Exception ex) {
                    System.out.println("修改失败！");
                    throw new RuntimeException(ex);
                }

            }else {
                //修改记录
                //bookNoText bookNameText studentNoText studentNameText statusTextField
                //获取数据
                int bookNo = Integer.parseInt(bookNoText.getText());
                int studentNo = Integer.parseInt(studentNoText.getText());
                String status = statusTextField.getText();
                try {
                    RecordsMapper recordsMapper = new RecordsMapper();
                    int result = recordsMapper.updateByNo(bookNo, studentNo, status);
                    if (result > 0){
                        clearTextAndLabel();
                        remove(jScrollPane);
                        initRecordsTable();
                        JOptionPane.showMessageDialog(this,"修改成功！");
                    }else {
                        JOptionPane.showMessageDialog(this,"修改失败！");
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        //删除
        if(e.getSource().equals(deleteButton)){
            if (status == 1){
                //删除图书
                int no = Integer.parseInt(noTextField.getText());
                try {
                    BookMapper bookMapper = new BookMapper();
                    int result = bookMapper.deleteByNo(no);
                    if (result > 0){
                        //删除成功
                        clearTextAndLabel();
                        remove(jScrollPane);
                        initBooksTable();
                        JOptionPane.showMessageDialog(this,"删除成功！");
                    }else {
                        JOptionPane.showMessageDialog(this,"删除失败！");
                    }
                } catch (Exception ex) {
                    System.out.println("删除失败！");
                    throw new RuntimeException(ex);
                }
            }else {
                //删除记录
                int bookNo = Integer.parseInt(bookNoText.getText());
                int studentNo = Integer.parseInt(studentNoText.getText());
                try {
                    RecordsMapper recordsMapper = new RecordsMapper();
                    int result = recordsMapper.deleteByNo(bookNo, studentNo);
                    if (result > 0){
                        clearTextAndLabel();
                        remove(jScrollPane);
                        initRecordsTable();
                        JOptionPane.showMessageDialog(this,"删除成功！");
                    }else {
                        JOptionPane.showMessageDialog(this,"删除失败！");
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        //查询
        if (e.getSource().equals(searchButton)){
            if (status == 1){
                //查询图书
                int no = Integer.parseInt(noTextFieldSearch.getText());
                Book book = null;
                try {
                    BookMapper bookMapper = new BookMapper();
                    book = bookMapper.selectByNo(no);
                    if (book == null){
                        //没有这本书
                        JOptionPane.showMessageDialog(this,"该书不存在！");
                    }else {
                        //有这本书
                        String status;
                        if (book.isStatus() == 0){
                            status = "借阅中";
                        }else {
                            status = "已归还";
                        }
                        dtm.setRowCount(0);
                        dtm.addRow(new Object[]{book.getNO(),book.getName(),book.getAuthor(),book.getPublisher(),status,book.getPrice()});
                    }
                } catch (Exception ex) {
                    System.out.println("查询失败！");
                    throw new RuntimeException(ex);
                }
            }else {
                //查询记录
                try {
                    RecordsMapper recordsMapper = new RecordsMapper();
                    if ("学生编号".equals(noClassify.getSelectedItem() + "")){
                        //根据学生编号查询
                        int studentNo = Integer.parseInt(noTextFieldSearch.getText());
                        List<Record> list = recordsMapper.selectByStudentNo(studentNo);
                        if (list.size() == 0){
                            //没找到
                            JOptionPane.showMessageDialog(this,"该学生未借书");
                        }else {
                            //找到了，添加到表格
                            dtm.setRowCount(0);
                            for (Record record : list) {
                                String status = "";
                                if (record.getStatus() == 0){
                                    status = "借阅中";
                                }else {
                                    status = "已归还";
                                }
                                dtm.addRow(new Object[]{record.getBookNo(),record.getBookName(),record.getStudentNo(),record.getStudentName(),status});
                            }
                        }
                    }else {
                        //根据图书编号查询
                        //根据学生编号查询
                        int bookNo = Integer.parseInt(noTextFieldSearch.getText());
                        Record record = null;
                        record = recordsMapper.selectByBookNo(bookNo);
                        if (record == null){
                            //没找到
                            JOptionPane.showMessageDialog(this,"该书未借出");
                        }else {
                            //找到了，添加到表格
                            dtm.setRowCount(0);
                            String status = "";
                            if (record.getStatus() == 0){
                                status = "借阅中";
                            }else {
                                status = "已归还";
                            }
                            dtm.addRow(new Object[]{record.getBookNo(),record.getBookName(),record.getStudentNo(),record.getStudentName(),status});
                        }
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        //查询全部
        if (e.getSource().equals(searchAllButton)){
            if (status == 1){
                //查询图书
                clearTextAndLabel();
                remove(jScrollPane);
                try {
                    initBooksTable();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }else {
                //查询记录
                clearTextAndLabel();
                remove(jScrollPane);
                try {
                    initRecordsTable();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        //批量删除
        if (e.getSource().equals(batchDeleteButton)){
            if (status == 1){
                //批量删除图书
                try {
                    BookMapper bookMapper = new BookMapper();
                    //获取选中的no
                    int[] selectedRows = table.getSelectedRows();
                    List<Integer> nos = new ArrayList<>();
                    for (int selectedRow : selectedRows) {
                        int no = Integer.parseInt(table.getValueAt(selectedRow,0) + "");
                        nos.add(no);
                    }
                    //删除
                    int result = 0;
                    for (Integer no : nos) {
                        result += bookMapper.deleteByNo(no);
                    }
                    if (result > 0){
                        clearTextAndLabel();
                        remove(jScrollPane);
                        initBooksTable();
                        JOptionPane.showMessageDialog(this,"删除成功！");
                    }else {
                        JOptionPane.showMessageDialog(this,"删除失败！");
                    }
                } catch (Exception ex) {
                    System.out.println("批量删除失败");
                    throw new RuntimeException(ex);
                }
            }else {
                //批量删除记录
                try {
                    RecordsMapper recordsMapper = new RecordsMapper();
                    //获取选中的no
                    int[] selectedRows = table.getSelectedRows();
                    List<Integer> nos = new ArrayList<>();
                    for (int selectedRow : selectedRows) {
                        int no = Integer.parseInt(table.getValueAt(selectedRow,0) + "");
                        nos.add(no);
                    }
                    //删除
                    int result = 0;
                    for (Integer no : nos) {
                        result += recordsMapper.deleteByBookNo(no);
                    }
                    if (result > 0){
                        clearTextAndLabel();
                        remove(jScrollPane);
                        initRecordsTable();
                        JOptionPane.showMessageDialog(this,"删除成功！");
                    }else {
                        JOptionPane.showMessageDialog(this,"删除失败！");
                    }
                } catch (Exception ex) {
                    System.out.println("批量删除失败");
                    throw new RuntimeException(ex);
                }
            }
        }
        //添加
        if (e.getSource().equals(addButton)){
            if (status == 1){
                //添加图书
                new AddBookJFrame(this);
            }else {
                //添加记录
                new AddRecordJFrame(this);
            }
        }
    }
}
