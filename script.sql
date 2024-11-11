create table book
(
    no        int auto_increment
        primary key,
    name      varchar(50) not null,
    author    varchar(20) null,
    publisher varchar(20) null,
    status    smallint    not null,
    price     int         null
);

create table manager
(
    no       int         not null comment '账号'
        primary key,
    password varchar(20) not null comment '密码'
)
    comment '系统管理员';

create table records
(
    book_no      int         null comment '书的编号',
    book_name    varchar(50) not null,
    student_no   int         null,
    student_name varchar(20) null,
    status       smallint    null comment '0借阅中 1已归还'
)
    comment '借阅记录';

create table student
(
    no       int auto_increment comment '学号'
        primary key,
    name     varchar(20) not null comment '姓名',
    password varchar(20) not null comment '密码',
    gender   tinyint     null comment '男1 女2',
    age      int         null
)
    comment '学生表';

create table worker
(
    no       int         not null comment '账号'
        primary key,
    name     varchar(20) not null comment '姓名',
    password varchar(20) not null comment '密码'
);


