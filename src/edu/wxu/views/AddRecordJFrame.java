package edu.wxu.views;

import edu.wxu.pojo.Book;
import edu.wxu.pojo.Record;
import mapper.BookMapper;
import mapper.RecordsMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRecordJFrame extends JFrame implements ActionListener {
    //父窗口
    private WorkerJFrame father;
    //标签和文本框
    JLabel titleLabel = new JLabel("添加借阅记录");
    JLabel bookNo = new JLabel("图书编号：");
    JTextField bookNoText = new JFormattedTextField();
    JLabel bookNameLabel = new JLabel("图书书名：");
    JLabel studentNoLabel = new JLabel("学生编号：");
    JLabel statusLabel = new JLabel("状  态：");
    JLabel studentName = new JLabel("学生姓名：");
    JTextField bookNameTextField = new JFormattedTextField();
    JTextField studentNoText = new JFormattedTextField();
    JRadioButton zeroButton = new JRadioButton("借阅中");
    JRadioButton oneButton = new JRadioButton("已归还");
    ButtonGroup buttonGroup = new ButtonGroup();
    JTextField studentNameText = new JFormattedTextField();
    //添加按钮
    JButton addButton = new JButton("添加");
    public AddRecordJFrame(WorkerJFrame father){
        this.father = father;
        initAddRecordJFrame();
        initSwings();
        this.setVisible(true);
    }
    private void initAddRecordJFrame() {
        //大小
        this.setSize(500,700);
        //标题
        this.setTitle("添加借阅记录");
        //置顶
        this.setAlwaysOnTop(true);
        //居中
        this.setLocationRelativeTo(null);
        //关闭模式
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //默认组件设置
        this.setLayout(null);
        //不能最大化
        this.setResizable(false);
    }
    private void initSwings() {
        //初始化组件
        //标题标签
        titleLabel.setFont(new Font("微软雅黑",Font.BOLD,30));
        titleLabel.setBounds(160,30,500,200);
        this.add(titleLabel);
        //图书编号标签和文本框
        bookNo.setBounds(40,170,150,50);
        bookNo.setFont(new Font("宋体",Font.BOLD,25));
        this.add(bookNo);
        bookNoText.setBounds(160,180,200,35);
        bookNoText.setFont(new Font("宋体",Font.BOLD,25));
        this.add(bookNoText);

        //书名标签和文本框
        bookNameLabel.setBounds(40,220,150,50);
        bookNameLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(bookNameLabel);
        bookNameTextField.setBounds(160,230,200,35);
        bookNameTextField.setFont(new Font("宋体",Font.BOLD,25));
        this.add(bookNameTextField);

        //学生编号标签和文本框
        studentNoLabel.setBounds(40,270,150,50);
        studentNoLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(studentNoLabel);
        studentNoText.setBounds(160,280,200,35);
        studentNoText.setFont(new Font("宋体",Font.BOLD,25));
        this.add(studentNoText);

        //状态标签和按钮组
        statusLabel.setBounds(40,370,150,50);
        statusLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(statusLabel);
        zeroButton.setBounds(170,370,150,60);
        zeroButton.setFont(new Font("宋体",Font.BOLD,25));
        oneButton.setBounds(320,370,150,60);
        oneButton.setFont(new Font("宋体",Font.BOLD,25));
        buttonGroup.add(zeroButton);
        buttonGroup.add(oneButton);
        zeroButton.setSelected(true);
        this.getContentPane().add(zeroButton);
        this.getContentPane().add(oneButton);


        //出版商标签和文本框
        studentName.setBounds(40,320,150,50);
        studentName.setFont(new Font("宋体",Font.BOLD,25));
        this.add(studentName);
        studentNameText.setBounds(160,330,200,35);
        studentNameText.setFont(new Font("宋体",Font.BOLD,25));
        this.add(studentNameText);
        //添加按钮
        addButton.setBounds(170,500,150,60);
        addButton.setFont(new Font("宋体",Font.BOLD,25));
        addButton.addActionListener(this);
        this.add(addButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addButton)){
            //添加记录
            int bookNo = Integer.parseInt(bookNoText.getText());
            String booName = bookNameTextField.getText();
            int studentNo = Integer.parseInt(studentNoText.getText());
            String studentName = studentNameText.getText();
            String status;
            if (zeroButton.isSelected()){
                status = "借阅中";
            }else {
                status = "已归还";
            }

            //查询是否存在
            try {
                RecordsMapper recordsMapper = new RecordsMapper();
                Record record = recordsMapper.selectByBookNo(bookNo);
                if (record != null){
                    //已经存在
                    JOptionPane.showMessageDialog(this,"该书已经借出！");
                }else {
                    //添加记录
                    int insert = recordsMapper.insert(bookNo, booName, studentNo, studentName, status);
                    if (insert > 0){
                        father.clearTextAndLabel();
                        father.remove(father.jScrollPane);
                        father.initRecordsTable();
                        JOptionPane.showMessageDialog(this,"添加成功！");
                        this.dispose();
                    }
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }}
