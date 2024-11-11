package edu.wxu.pojo;

public class Book {
    private int no;
    private String name;
    private String author;
    private String publisher;  //出版社
    private short status;  //0借阅中  1已归还
    private int price;

    public Book() {
    }

    public Book(int no,String name, String author, String publisher, short status, int price, String classfication) {
        this.no = no;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.status = status;
        this.price = price;
    }

    public int getNO(){
        return no;
    }

    public void setNo(int no){
        this.no = no;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public short isStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public String toString() {
        String stat;
        if (status == 0){
            stat = "借阅中";
        }else {
            stat = "已归还";
        }
        return "Book{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", status=" + stat +
                ", price=" + price +
                '}';
    }
}
