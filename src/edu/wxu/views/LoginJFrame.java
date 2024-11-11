package edu.wxu.views;

import edu.wxu.pojo.Manager;
import edu.wxu.pojo.Student;
import edu.wxu.pojo.Worker;
import mapper.ManagerMapper;
import mapper.StudentMapper;
import mapper.WorkerMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginJFrame extends JFrame implements ActionListener{
    //标题标签
    //标题logo
    JLabel logoLabel = new JLabel();
    JLabel titleLabel = new JLabel("图书管理系统");
    //用户名标签\密码标签\身份标签
    JLabel noLabel = new JLabel("账号：");
    JLabel passwordLabel = new JLabel("密码：");
    JLabel identityLabel = new JLabel("身份：");
    //文本框
    JTextField noTextField = new JFormattedTextField();
    JPasswordField passwordTextField = new JPasswordField();
    //身份下拉表
    JComboBox<String> identityBox = new JComboBox<>();
    //登录按钮
    JButton loginJButton = new JButton("登录");
    //清空按钮
    JButton clearJButton = new JButton("清空");
    public LoginJFrame(){
        initLoginJFrame();
        this.setVisible(true);
    }


    private void initLoginJFrame() {
        //大小
        this.setSize(700,600);
        //标题
        this.setTitle("图书管理系统 v1.0 -登录界面");
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


        //标题logo
        logoLabel.setBounds(60,25,170,170);
        ImageIcon logoIcon = new ImageIcon("image/书本.png");
        ImageIcon resizableImage = new ImageIcon(logoIcon.getImage().getScaledInstance(logoLabel.getWidth(), logoLabel.getHeight(), Image.SCALE_DEFAULT));
        logoLabel.setIcon(resizableImage);
        this.getContentPane().add(logoLabel);
        //标题标签
        titleLabel.setFont(new Font("微软雅黑",Font.BOLD,50));
        titleLabel.setBounds(250,30,500,200);
        this.add(titleLabel);
        //标签设置
        noLabel.setBounds(120,175,120,70);
        passwordLabel.setBounds(120,235,120,70);
        identityLabel.setBounds(120,295,120,70);
        noLabel.setFont(new Font("宋体",Font.BOLD,30));
        passwordLabel.setFont(new Font("宋体",Font.BOLD,30));
        identityLabel.setFont(new Font("宋体",Font.BOLD,30));
        this.getContentPane().add(noLabel);
        this.getContentPane().add(passwordLabel);
        this.getContentPane().add(identityLabel);
        //文本框设置
        noTextField.setBounds(220,195,300,35);
        noTextField.setFont(new Font("宋体",Font.BOLD,20));
        passwordTextField.setBounds(220,255,300,35);
        passwordTextField.setFont(new Font("宋体",Font.BOLD,20));
        this.getContentPane().add(noTextField);
        this.getContentPane().add(passwordTextField);
        //身份下拉框设置
        identityBox.addItem("系统管理员");
        identityBox.addItem("图书管理员");
        identityBox.addItem("学生");
        identityBox.setFont(new Font("宋体",Font.BOLD,25));
        identityBox.setBounds(220,315,300,35);
        this.getContentPane().add(identityBox);
        //按钮设置
        loginJButton.setBounds(120,380,150,60);
        loginJButton.setFont(new Font("宋体",Font.BOLD,30));
        clearJButton.setBounds(380,380,150,60);
        clearJButton.setFont(new Font("宋体",Font.BOLD,30));

        //登录事件
        loginJButton.addActionListener(this);
        //清空事件
        clearJButton.addActionListener(this);
        //添加按钮
        this.getContentPane().add(loginJButton);
        this.getContentPane().add(clearJButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(loginJButton)){
            System.out.println("登录按钮被点击");
            //登录事件
            //获取输入的账号和密码
            if ("".equals(noTextField.getText()) || "".equals(passwordTextField)){
                JOptionPane.showMessageDialog(this,"请输入账号和密码！");
            }
            Integer no = Integer.parseInt(noTextField.getText());
            String password = passwordTextField.getText();
            //根据身份登录
            try {
                if (identityBox.getSelectedItem().equals("系统管理员")){
                    //系统管理员登录
                    login(no,password,"系统管理员");
                }else if (identityBox.getSelectedItem().equals("图书管理员")){
                    //图书管理员登录
                    login(no,password,"图书管理员");
                }else if (identityBox.getSelectedItem().equals("学生")){
                    //学生登录
                    login(no,password,"学生");
                }else {

                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }else if (e.getSource().equals(clearJButton)){
            //清空事件
            noTextField.setText("");
            passwordTextField.setText("");
        }
    }

    //根据身份登录功能
    private void login(Integer no, String password, String identify) throws Exception {
        if ("系统管理员".equals(identify)){
            //根据no查询对象
            ManagerMapper managerMapper = new ManagerMapper();
            Manager manager = managerMapper.selectByNoAndPassword(no,password);
            System.out.println(manager);
            if (manager != null){
                //找到了对应的manager
                JOptionPane.showMessageDialog(this,"登陆成功！");
                noTextField.setText("");
                passwordTextField.setText("");
                this.dispose();
                new AdminJFrame(manager);
            }else {
                //没找到对应的manager
                JOptionPane.showMessageDialog(this,"账号或密码错误！");
            }
        }else if ("图书管理员".equals(identify)){
            //根据no查询对象
            WorkerMapper workerMapper = new WorkerMapper();
            Worker worker = workerMapper.selectByNoAndPassword(no,password);
            System.out.println(worker);
            if (worker != null){
                //找到了对应的worker
                JOptionPane.showMessageDialog(this,"登陆成功！");
                noTextField.setText("");
                passwordTextField.setText("");
                this.dispose();
                new WorkerJFrame(worker);
            }else {
                //没找到对应的manager
                JOptionPane.showMessageDialog(this,"账号或密码错误！");
            }
        }else if("学生".equals(identify)){
            //根据no查询对象
            StudentMapper studentMapper = new StudentMapper();
            Student student = studentMapper.selectByNoAndPassword(no, password);
            System.out.println(student);
            if (student != null){
                //找到了对应的manager
                JOptionPane.showMessageDialog(this,"登陆成功！");
                noTextField.setText("");
                passwordTextField.setText("");
                this.dispose();
                new StudentJFrame(student);
            }else {
                //没找到对应的manager
                JOptionPane.showMessageDialog(this,"账号或密码错误！");
            }
        }else {
            System.out.println("身份错误");
        }
    }
}