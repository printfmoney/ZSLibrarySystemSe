package edu.wxu.pojo;

public class Record {
    private int bookNo;
    private String bookName;
    private int studentNo;
    private String studentName;
    //0 借阅中  1 已归还
    private short status;

    public Record() {
    }

    public Record(int bookNo, String bookName, int studentNo, String studentName) {
        this.bookNo = bookNo;
        this.bookName = bookName;
        this.studentNo = studentNo;
        this.studentName = studentName;
    }

    public int getBookNo() {
        return bookNo;
    }

    public void setBookNo(int bookNo) {
        this.bookNo = bookNo;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }
}
