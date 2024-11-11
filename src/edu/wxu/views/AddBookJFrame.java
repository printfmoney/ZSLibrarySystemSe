package edu.wxu.views;

import edu.wxu.pojo.Book;
import mapper.BookMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddBookJFrame extends JFrame implements ActionListener {
    //父窗口
    private WorkerJFrame father;
    //标签和文本框
    JLabel titleLabel = new JLabel("添加图书");
    JLabel noLabel = new JLabel("编号：");
    JLabel nameLabel = new JLabel("书名：");
    JLabel authorLabel = new JLabel("作者：");
    JLabel statusLabel = new JLabel("状态：");
    JLabel publisherLabel = new JLabel("出版商：");
    JTextField noTextField = new JFormattedTextField();
    JTextField nameTextField = new JFormattedTextField();
    JTextField authorText = new JFormattedTextField();
    JRadioButton zeroButton = new JRadioButton("借阅中");
    JRadioButton oneButton = new JRadioButton("已归还");
    ButtonGroup buttonGroup = new ButtonGroup();
    JTextField publisherText = new JFormattedTextField();
    //添加按钮
    JButton addButton = new JButton("添加");
    JLabel priceLabel = new JLabel("价格：");
    JTextField priceText = new JFormattedTextField();
    public AddBookJFrame(WorkerJFrame father){
        this.father = father;
        initAddBookJFrame();
        initSwings();
        this.setVisible(true);
    }
    private void initAddBookJFrame() {
        //大小
        this.setSize(500,700);
        //标题
        this.setTitle("添加图书");
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
        noLabel.setBounds(60,170,100,50);
        noLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(noLabel);
        noTextField.setBounds(160,180,200,35);
        noTextField.setFont(new Font("宋体",Font.BOLD,25));
        this.add(noTextField);

        //书名标签和文本框
        nameLabel.setBounds(60,220,100,50);
        nameLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(nameLabel);
        nameTextField.setBounds(160,230,200,35);
        nameTextField.setFont(new Font("宋体",Font.BOLD,25));
        this.add(nameTextField);

        //作者标签和文本框
        authorLabel.setBounds(60,270,100,50);
        authorLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(authorLabel);
        authorText.setBounds(160,280,200,35);
        authorText.setFont(new Font("宋体",Font.BOLD,25));
        this.add(authorText);

        //状态标签和按钮组
        statusLabel.setBounds(60,320,100,50);
        statusLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(statusLabel);
        zeroButton.setBounds(170,320,150,60);
        zeroButton.setFont(new Font("宋体",Font.BOLD,25));
        oneButton.setBounds(320,320,150,60);
        oneButton.setFont(new Font("宋体",Font.BOLD,25));
        buttonGroup.add(zeroButton);
        buttonGroup.add(oneButton);
        zeroButton.setSelected(true);
        this.getContentPane().add(zeroButton);
        this.getContentPane().add(oneButton);


        //出版商标签和文本框
        publisherLabel.setBounds(60,370,150,50);
        publisherLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(publisherLabel);
        publisherText.setBounds(160,380,200,35);
        publisherText.setFont(new Font("宋体",Font.BOLD,25));
        this.add(publisherText);
        //出版商标签和文本框
        priceLabel.setBounds(60,420,150,50);
        priceLabel.setFont(new Font("宋体",Font.BOLD,25));
        this.add(priceLabel);
        priceText.setBounds(160,430,200,35);
        priceText.setFont(new Font("宋体",Font.BOLD,25));
        this.add(priceText);
        //添加按钮
        addButton.setBounds(170,500,150,60);
        addButton.setFont(new Font("宋体",Font.BOLD,25));
        addButton.addActionListener(this);
        this.add(addButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addButton)){
            //添加图书
            int no = Integer.parseInt(noTextField.getText());
            String name = nameTextField.getText();
            String author = authorText.getText();
            String publisher = publisherText.getText();
            String status;
            if (zeroButton.isSelected()){
                status = "借阅中";
            }else {
                status = "已归还";
            }
            int price = Integer.parseInt(priceText.getText());

            //查询是否存在
            try {
                BookMapper bookMapper = new BookMapper();
                Book book = bookMapper.selectByNo(no);
                if (book != null){
                    //已经存在
                    JOptionPane.showMessageDialog(this,"该编号已存在！");
                }else {
                    //添加图书
                    bookMapper.insert(no,name,author,publisher,status,price);
                    father.clearTextAndLabel();
                    father.remove(father.jScrollPane);
                    father.initBooksTable();
                    JOptionPane.showMessageDialog(this,"添加成功！");
                    this.dispose();
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
