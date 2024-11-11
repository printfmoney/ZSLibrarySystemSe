package edu.wxu.views;

import edu.wxu.pojo.Student;
import mapper.StudentMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.NotYetBoundException;
import java.sql.SQLException;

public class AddStudentJFrame extends JFrame implements ActionListener {
    //父窗口
    private AdminJFrame father;
    //标签和文本框
    JLabel titleLabel = new JLabel("添加学生");
    JLabel noLabel = new JLabel("账号：");
    JLabel nameLabel = new JLabel("姓名：");
    JLabel passwordLabel = new JLabel("密码：");
    JLabel genderLabel = new JLabel("性别：");
    JLabel ageLabel = new JLabel("年龄：");
    JTextField noTextField = new JFormattedTextField();
    JTextField nameTextField = new JFormattedTextField();
    JTextField passwordTextField = new JFormattedTextField();
    JRadioButton boyButton = new JRadioButton("男");
    JRadioButton girlButton = new JRadioButton("女");
    ButtonGroup buttonGroup = new ButtonGroup();
    JTextField ageTextField = new JFormattedTextField();
    //添加按钮
    JButton addButton = new JButton("添加");
    public AddStudentJFrame(AdminJFrame father){
        this.father = father;
        initAddStudentJFrame();
        initSwings();
        this.setVisible(true);
    }
    private void initAddStudentJFrame() {
        //大小
        this.setSize(500,700);
        //标题
        this.setTitle("添加学生");
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
        titleLabel.setBounds(130,30,500,200);
        this.add(titleLabel);
        //账号标签和文本框
        noLabel.setBounds(60,170,100,50);
        noLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(noLabel);
        noTextField.setBounds(160,180,200,35);
        noTextField.setFont(new Font("宋体",Font.BOLD,25));
        this.add(noTextField);

        //姓名标签和文本框
        nameLabel.setBounds(60,220,100,50);
        nameLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(nameLabel);
        nameTextField.setBounds(160,230,200,35);
        nameTextField.setFont(new Font("宋体",Font.BOLD,25));
        this.add(nameTextField);

        //密码标签和文本框
        passwordLabel.setBounds(60,270,100,50);
        passwordLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(passwordLabel);
        passwordTextField.setBounds(160,280,200,35);
        passwordTextField.setFont(new Font("宋体",Font.BOLD,25));
        this.add(passwordTextField);

        //性别标签和按钮组
        genderLabel.setBounds(60,320,100,50);
        genderLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(genderLabel);
        boyButton.setBounds(170,320,100,60);
        boyButton.setFont(new Font("宋体",Font.BOLD,25));
        girlButton.setBounds(290,320,100,60);
        girlButton.setFont(new Font("宋体",Font.BOLD,25));
        buttonGroup.add(boyButton);
        buttonGroup.add(girlButton);
        boyButton.setSelected(true);
        this.getContentPane().add(boyButton);
        this.getContentPane().add(girlButton);


        //年龄标签和文本框
        ageLabel.setBounds(60,370,100,50);
        ageLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(ageLabel);
        ageTextField.setBounds(160,380,200,35);
        ageTextField.setFont(new Font("宋体",Font.BOLD,25));
        this.add(ageTextField);
        //添加按钮
        addButton.setBounds(170,500,150,60);
        addButton.setFont(new Font("宋体",Font.BOLD,25));
        addButton.addActionListener(this);
        this.add(addButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StudentMapper studentMapper;
        try {
            studentMapper = new StudentMapper();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        if (e.getSource().equals(addButton)){
            //获取数据
            int no = Integer.parseInt(noTextField.getText());
            String name = nameTextField.getText();
            String password = passwordTextField.getText();
            int gender = 1;
            if (boyButton.isSelected()){
                gender = 1;
            } else if (girlButton.isSelected()) {
                gender = 2;
            }
            int age = Integer.parseInt(ageTextField.getText());
            //查询用户是否存在
            Student student = null;
            int result = 0;
            try {
                student = studentMapper.selectByNo(no);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if (student != null){
                JOptionPane.showMessageDialog(this,"该用户已存在！");
                return;
            }else {
                try {
                    result = studentMapper.insert(no,name,password,gender,age);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (result > 0){
                father.clearTextField();
                father.clearLabelTextScroll();
                try {
                    father.initStudentTable();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(this,"添加成功！");
                this.dispose();
            }else {
                JOptionPane.showMessageDialog(this,"添加失败！");
            }
        }
    }
}
