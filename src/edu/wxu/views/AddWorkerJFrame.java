package edu.wxu.views;

import edu.wxu.pojo.Worker;
import mapper.WorkerMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddWorkerJFrame extends JFrame implements ActionListener {
    //父级窗口
    private AdminJFrame father;
    //标签和文本框
    JLabel titleLabel = new JLabel("添加图书管理员");
    JLabel noLabel = new JLabel("账号：");
    JLabel nameLabel = new JLabel("姓名：");
    JLabel passwordLabel = new JLabel("密码：");
    JTextField noTextField = new JFormattedTextField();
    JTextField nameTextField = new JFormattedTextField();
    JTextField passwordTextField = new JFormattedTextField();
    //添加按钮
    JButton addButton = new JButton("添加");
    public AddWorkerJFrame(AdminJFrame father){
        this.father = father;
        initAddWorkerJFrame();
        initSwings();
        this.setVisible(true);
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
        nameLabel.setBounds(60,270,100,50);
        nameLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(nameLabel);
        nameTextField.setBounds(160,280,200,35);
        nameTextField.setFont(new Font("宋体",Font.BOLD,25));
        this.add(nameTextField);

        //密码标签和文本框
        passwordLabel.setBounds(60,370,100,50);
        passwordLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(passwordLabel);
        passwordTextField.setBounds(160,380,200,35);
        passwordTextField.setFont(new Font("宋体",Font.BOLD,25));
        this.add(passwordTextField);
        //添加按钮
        addButton.setBounds(170,500,150,60);
        addButton.setFont(new Font("宋体",Font.BOLD,25));
        addButton.addActionListener(this);
        this.add(addButton);
    }

    private void initAddWorkerJFrame() {
        //大小
        this.setSize(500,700);
        //标题
        this.setTitle("添加图书管理员");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addButton)){
            //获取文本
            String noText = noTextField.getText();
            String nameText = nameTextField.getText();
            String passwordText = passwordTextField.getText();

            //查询该账号是否存在
            WorkerMapper workerMapper;
            try {
                workerMapper = new WorkerMapper();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            Worker worker = null;
            int result = 0;
            try {
                worker = workerMapper.selectByNo(Integer.parseInt(noText));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            if (null != worker){
                //已经存在该用户
                JOptionPane.showMessageDialog(this,"该账号已存在！");
            }else {
                //没有该用户，添加
                try {
                    result = workerMapper.insert(Integer.parseInt(noText),nameText,passwordText);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if (result > 0){
              //添加成功
                father.clearTextField();
                father.clearLabelTextScroll();
                try {
                    father.initWorkerTable();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(this,"添加成功！");
                this.dispose();
            }
        }
    }
}
