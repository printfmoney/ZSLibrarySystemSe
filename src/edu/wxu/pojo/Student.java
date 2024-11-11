package edu.wxu.pojo;

public class Student {
    private int no;
    private String name;
    private String password;
    private short gender;  //1男  2女
    private int age;


    public Student() {
    }

    public Student(int no, String name, String password,short gender,int age) {
        this.no = no;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.age = age;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public short getGender() {
        return gender;
    }

    public void setGender(short gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}
