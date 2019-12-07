package com.wn.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "wn_books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer books_id;
    private String books_name;
    @Column(name = "books_vip")
    private Integer booksVip;
    private String books_pic;
    @Column(name = "books_type")
    private Integer booksType;
    private String books_url;
    @Column(name = "books_status")
    private Integer booksStatus;
    private String books_author;
    private String books_describe;
    private String books_count;
    @Transient
    private String vip_name;
    @Transient
    private String status_name;
    @Transient
    private String type_name;


}
