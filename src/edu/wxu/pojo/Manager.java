package edu.wxu.pojo;

public class Manager {
    private Integer no;
    private String password;

    public Manager() {
    }

    public Manager(Integer no, String password) {
        this.no = no;
        this.password = password;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "no=" + no +
                ", password='" + password + '\'' +
                '}';
    }
}
