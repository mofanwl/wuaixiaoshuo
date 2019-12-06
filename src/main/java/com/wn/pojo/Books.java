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
    private Integer books_vip;
    private String books_pic;
    private Integer books_type;
    private String books_url;
    private Integer books_status;
    private String books_author;
    private String books_describe;
    private String books_count;
    private String vip_name;
    private String status_name;
    private String type_name;


}
