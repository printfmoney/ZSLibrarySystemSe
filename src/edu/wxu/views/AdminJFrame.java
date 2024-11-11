package edu.wxu.views;

import edu.wxu.pojo.Manager;
import edu.wxu.pojo.Student;
import edu.wxu.pojo.Worker;
import mapper.StudentMapper;
import mapper.WorkerMapper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminJFrame extends JFrame implements ActionListener{
    //界面是正在管理图书管理员还是学生 1管理员 2学生
    private int status = 1;
    //菜单的搭建
    private JMenuBar menuBar;
    private JMenu menu;
    //退出
    private JMenuItem exitMenu;
    private JMenuItem manageWorker;
    private JMenuItem manageStudent;
    //表格组件
    private DefaultTableModel dtm;
    private JTable table;
    public JScrollPane jScrollPane;
    //左边选中人信息的标签及文本框
    private JLabel noLabel = new JLabel("账号：");
    private JLabel nameLabel = new JLabel("姓名：");
    private JLabel passwordLabel = new JLabel("密码：");
    private JLabel genderLabel = new JLabel("性别：");
    private JLabel ageLabel = new JLabel("年龄：");
    private JTextField noTextField = new JFormattedTextField();
    private JTextField nameTextField = new JFormattedTextField();
    private JTextField passwordTextField = new JFormattedTextField();
    private JTextField genderTextField = new JFormattedTextField();
    private JTextField ageTextField = new JFormattedTextField();
    private JLabel noLabelSearch = new JLabel("账号：");
    private JTextField noTextFieldSearch = new JFormattedTextField();
    //按钮
    private JButton modifyButton = new JButton("修改");
    private JButton deleteButton = new JButton("删除");
    private JButton addButton = new JButton("添加");
    private JButton batchDeleteButton = new JButton("批量删除");
    private JButton searchButton = new JButton("查询");
    private JButton searchAllButton = new JButton("查看全部");

    public AdminJFrame(Manager manager) throws Exception {
        initAdminJFrame();
        initWorkerTable();
        this.setVisible(true);
    }

    //默认登录后打开Worker管理界面
    public void initWorkerTable() throws Exception {
        //标签及文本框设置
        noLabel.setBounds(50,270,100,50);
        noLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(noLabel);
        noTextField.setBounds(150,280,200,35);
        noTextField.setFont(new Font("宋体",Font.BOLD,25));
        noTextField.setEnabled(false);
        this.add(noTextField);

        nameLabel.setBounds(50,470,100,50);
        nameLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(nameLabel);
        nameTextField.setBounds(150,480,200,35);
        nameTextField.setFont(new Font("宋体",Font.BOLD,25));
        this.add(nameTextField);

        passwordLabel.setBounds(50,670,100,50);
        passwordLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(passwordLabel);
        passwordTextField.setBounds(150,680,200,35);
        passwordTextField.setFont(new Font("宋体",Font.BOLD,25));
        this.add(passwordTextField);

        //表格设置
        WorkerMapper workerMapper = new WorkerMapper();
        dtm = new DefaultTableModel();
        String[] workerHeaders = {"账号","姓名","密码"};
        //添加表头
        for (String workerHeader : workerHeaders) {
            dtm.addColumn(workerHeader);
        }
        //获取worker数据
        List<Worker> workers = workerMapper.selectAll();
        //给表格添加worker数据
        for (Worker worker : workers) {
            dtm.addRow(new Object[]{worker.getNo(),worker.getName(),worker.getPassword()});
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
                //左边文本框只展示第一个被点到的
                noTextField.setText(table.getValueAt(rows[0],0) + "");
                nameTextField.setText(table.getValueAt(rows[0],1) + "");
                passwordTextField.setText(table.getValueAt(rows[0],2) + "");
            }
        });
    }

    //加载学生管理
    public void initStudentTable() throws Exception {
        //标签及文本框设置
        noLabel.setBounds(50,270,100,50);
        noLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(noLabel);
        noTextField.setBounds(150,280,200,35);
        noTextField.setFont(new Font("宋体",Font.BOLD,25));
        noTextField.setEnabled(false);
        this.add(noTextField);

        nameLabel.setBounds(50,370,100,50);
        nameLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(nameLabel);
        nameTextField.setBounds(150,380,200,35);
        nameTextField.setFont(new Font("宋体",Font.BOLD,25));
        this.add(nameTextField);

        passwordLabel.setBounds(50,470,100,50);
        passwordLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(passwordLabel);
        passwordTextField.setBounds(150,480,200,35);
        passwordTextField.setFont(new Font("宋体",Font.BOLD,25));
        this.add(passwordTextField);

        genderLabel.setBounds(50,570,100,50);
        genderLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(genderLabel);
        genderTextField.setBounds(150,580,200,35);
        genderTextField.setFont(new Font("宋体",Font.BOLD,25));
        this.add(genderTextField);

        ageLabel.setBounds(50,670,100,50);
        ageLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(ageLabel);
        ageTextField.setBounds(150,680,200,35);
        ageTextField.setFont(new Font("宋体",Font.BOLD,25));
        this.add(ageTextField);

        //表格设置
        StudentMapper studentMapper = new StudentMapper();
        dtm = new DefaultTableModel();
        String[] studentHeaders = {"账号","姓名","密码","gender","age"};
        //添加表头
        for (String studentHeader : studentHeaders) {
            dtm.addColumn(studentHeader);
        }
        //获取Student数据
        List<Student> students = studentMapper.selectAll();
        //给表格添加worker数据
        for (Student student : students) {
            String gender;
            if (student.getGender() == 1){
                gender = "男";
            }else {
                gender = "女";
            }
            dtm.addRow(new Object[]{student.getNo(),student.getName(),student.getPassword(),gender,student.getAge()});
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
                //左边文本框只展示第一个被点到的
                noTextField.setText(table.getValueAt(rows[0],0) + "");
                nameTextField.setText(table.getValueAt(rows[0],1) + "");
                passwordTextField.setText(table.getValueAt(rows[0],2) + "");
                genderTextField.setText(table.getValueAt(rows[0],3)+"");
                ageTextField.setText(table.getValueAt(rows[0],4)+"");
            }
        });
    }

    private void initAdminJFrame() {
        //大小
        this.setSize(1500,1000);
        //标题
        this.setTitle("图书管理系统 v1.0 -系统管理员界面");
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

        //管理菜单设置
        menuBar = new JMenuBar();
        //menuBar.setPreferredSize(new Dimension(100,50));
        menu = new JMenu("管理");
        menu.setFont(new Font("宋体",Font.BOLD,30));
        manageWorker = new JMenuItem("管理图书管理员");
        manageWorker.setFont(new Font("宋体",Font.BOLD,30));
        manageStudent = new JMenuItem("管理学生");
        manageStudent.setFont(new Font("宋体",Font.BOLD,30));
        //退出
        exitMenu = new JMenuItem("退出");
        exitMenu.setFont(new Font("宋体",Font.BOLD,30));
        //添加事件
        manageWorker.addActionListener(this);
        manageStudent.addActionListener(this);
        exitMenu.addActionListener(this);
        //封装
        menu.add(manageWorker);
        menu.add(manageStudent);
        menuBar.add(menu);
        menuBar.add(exitMenu);
        this.setJMenuBar(menuBar);

        //按钮设置
        modifyButton.setBounds(10,780,150,60);
        modifyButton.setFont(new Font("宋体",Font.BOLD,25));
        modifyButton.addActionListener(this);
        this.add(modifyButton);
        deleteButton.setBounds(240,780,150,60);
        deleteButton.setFont(new Font("宋体",Font.BOLD,25));
        deleteButton.addActionListener(this);
        this.add(deleteButton);
        addButton.setBounds(1050,150,150,60);
        addButton.setFont(new Font("宋体",Font.BOLD,25));
        addButton.addActionListener(this);
        this.add(addButton);
        batchDeleteButton.setBounds(1250,150,150,60);
        batchDeleteButton.setFont(new Font("宋体",Font.BOLD,25));
        batchDeleteButton.addActionListener(this);
        this.add(batchDeleteButton);
        searchButton.setBounds(750,150,150,60);
        searchButton.setFont(new Font("宋体",Font.BOLD,25));
        searchButton.addActionListener(this);
        this.add(searchButton);
        //查询标签和文本框
        noLabelSearch.setBounds(420,150,100,50);
        noLabelSearch.setFont(new Font("宋体",Font.BOLD,25));
        this.add(noLabelSearch);
        noTextFieldSearch.setBounds(520,160,200,35);
        noTextFieldSearch.setFont(new Font("宋体",Font.BOLD,25));
        this.add(noTextFieldSearch);
        //查询全部按钮
        searchAllButton.setBounds(750,50,150,60);
        searchAllButton.setFont(new Font("宋体",Font.BOLD,25));
        searchAllButton.addActionListener(this);
        this.add(searchAllButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(exitMenu)) {
            //退出功能
            System.out.println("退出按钮被点击");
            this.dispose();
            new LoginJFrame();
        } else if (e.getSource().equals(manageWorker)) {
            //管理Worker
            if (status == 1){
                //已经在管理worker，不做回应
                return;
            }else {
                //从管理Student改成管理Worker
                clearTextField();
                clearLabelTextScroll();
                status = 1;
                try {
                    initWorkerTable();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else if (e.getSource().equals(manageStudent)) {
            //管理Student
            if (status == 2){
                //已经是管理学生，不作回应
                return;
            }else {
                //从管理Worker变为管理Student
                clearTextField();
                clearLabelTextScroll();
                status = 2;
                try {
                    initStudentTable();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else if (e.getSource().equals(deleteButton)) {
            //删除功能
            WorkerMapper workerMapper;
            StudentMapper studentMapper;
            try {
                workerMapper = new WorkerMapper();
                studentMapper = new StudentMapper();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            int result = 0;
            if (status == 1){
                //删除管理员
                try {
                    result = workerMapper.deleteByNo(Integer.parseInt(noTextField.getText()));
                    this.remove(jScrollPane);
                    initWorkerTable();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                if (result > 0){
                    clearTextField();
                    JOptionPane.showMessageDialog(this,"删除成功！");
                }
            } else if (status == 2) {
                //删除学生
                try {
                    studentMapper = new StudentMapper();
                    result = studentMapper.deleteByNo(Integer.parseInt(noTextField.getText()));
                    this.remove(jScrollPane);
                    initStudentTable();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                if (result > 0){
                    clearTextField();
                    JOptionPane.showMessageDialog(this,"删除成功！");
                }
            }
        }else if (e.getSource().equals(modifyButton)){
            System.out.println("修改按钮被点击了");
            WorkerMapper workerMapper;
            StudentMapper studentMapper;
            try {
                workerMapper = new WorkerMapper();
                studentMapper = new StudentMapper();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            int result = 0;
            //修改功能
            if (status == 1){
                //修改Worker
                try {
                    result = workerMapper.updateByNo(Integer.parseInt(noTextField.getText()),nameTextField.getText(),passwordTextField.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (result > 0){
                    this.remove(jScrollPane);
                    try {
                        initWorkerTable();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    //清空文本框
                    clearTextField();
                    JOptionPane.showMessageDialog(this,"修改成功！");
                }else {
                    JOptionPane.showMessageDialog(this,"修改失败！");
                }
            }else if (status == 2){
                //修改Student
                try {
                    result = studentMapper.updateByNo(Integer.parseInt(noTextField.getText()),nameTextField.getText(),passwordTextField.getText(),genderTextField.getText(),Integer.parseInt(ageTextField.getText()));
                    System.out.println(result);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (result > 0){
                    this.remove(jScrollPane);
                    try {
                        initStudentTable();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    //清空文本框
                    clearTextField();
                    JOptionPane.showMessageDialog(this,"修改成功！");
                }else {
                    JOptionPane.showMessageDialog(this,"修改失败！");
                }
            }
        }else if (e.getSource().equals(batchDeleteButton)){
            System.out.println("批量删除按钮被点击了");
            //批量删除
            StudentMapper studentMapper;
            WorkerMapper workerMapper;
            try {
                studentMapper = new StudentMapper();
                workerMapper = new WorkerMapper();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            //批量删除
            int[] selectedRows = table.getSelectedRows();
            List<Integer> nos = new ArrayList<>();
            for (int selectedRow : selectedRows) {
                nos.add(Integer.parseInt(table.getValueAt(selectedRow,0)+""));
            }
            int result = 0;
            if (status == 1){
                //删除worker
                try {
                    result = workerMapper.deleteByNos(nos);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (result > 0){
                    clearTextField();
                    clearLabelTextScroll();
                    try {
                        initWorkerTable();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    JOptionPane.showMessageDialog(this,"删除成功！");
                }else {
                    JOptionPane.showMessageDialog(this,"删除失败！");
                }
            }else if (status == 2){
                try {
                    result = studentMapper.deleteByNos(nos);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (result > 0) {
                    clearTextField();
                    clearLabelTextScroll();
                    try {
                        initStudentTable();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    JOptionPane.showMessageDialog(this,"删除成功！");
                }else {
                    JOptionPane.showMessageDialog(this,"删除失败！");
                }
            }
        }else if (e.getSource().equals(addButton)){
            System.out.println("添加按钮被点击了！");
            //添加功能
            if (status == 1){
                //添加Worker
                new AddWorkerJFrame(this);
            }else if (status == 2){
                //添加学生
                new AddStudentJFrame(this);
            }
        } else if (e.getSource().equals(searchButton)) {
            //获取账号
            int no = Integer.parseInt(noTextFieldSearch.getText());
            //查询
            if (status == 1){
                //查询worker
                WorkerMapper workerMapper;
                try {
                    workerMapper = new WorkerMapper();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                Worker worker = null;
                try {
                    worker = workerMapper.selectByNo(no);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (worker == null){
                    //没找到这个人
                    JOptionPane.showMessageDialog(this,"该账号不存在！");
                }else {
                    //找到了，添加到表格中
                    //清空文本框
                    clearTextField();
                    //清空表格
                    dtm.setRowCount(0);
                    //添加数据
                    dtm.addRow(new Object[]{worker.getNo(),worker.getName(),worker.getPassword()});
                }
            }else {
                //查询student
                StudentMapper studentMapper;
                try {
                    studentMapper = new StudentMapper();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

                Student student = null;
                try {
                    student = studentMapper.selectByNo(no);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (student == null){
                    //没找到
                    JOptionPane.showMessageDialog(this,"该账号不存在！");
                }else {
                    //找到了
                    //清空文本框
                    clearTextField();
                    //清空表格
                    dtm.setRowCount(0);
                    String gender = "";
                    if (student.getGender() == 1){
                        gender = "男";
                    }else {
                        gender = "女";
                    }
                    dtm.addRow(new Object[]{student.getNo(),student.getName(),student.getPassword(),gender,student.getAge()});
                }
            }
        } else if (e.getSource().equals(searchAllButton)) {
            //查询全部
            if (status == 1){
                remove(jScrollPane);
                //查询worker
                try {
                    initWorkerTable();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }else {
                //查询student
                remove(jScrollPane);
                try {
                    initStudentTable();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

        }
    }

    public void clearTextField(){
        //清空文本框
        noTextField.setText("");
        nameTextField.setText("");
        passwordTextField.setText("");
        genderTextField.setText("");
        ageTextField.setText("");
    }

    public void clearLabelTextScroll(){
        if (status == 1){
            this.remove(noLabel);
            this.remove(noTextField);
            this.remove(nameLabel);
            this.remove(nameTextField);
            this.remove(passwordLabel);
            this.remove(passwordTextField);
            this.remove(jScrollPane);
        }else {
            this.remove(noLabel);
            this.remove(noTextField);
            this.remove(nameLabel);
            this.remove(nameTextField);
            this.remove(passwordLabel);
            this.remove(passwordTextField);
            this.remove(genderLabel);
            this.remove(genderTextField);
            this.remove(ageLabel);
            this.remove(ageTextField);
            this.remove(jScrollPane);
        }
    }
}
