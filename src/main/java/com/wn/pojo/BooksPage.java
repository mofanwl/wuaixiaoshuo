package com.wn.pojo;

import lombok.Data;

import java.util.List;

@Data
public class BooksPage {

    private List<Books> list;
    private Integer page;
    private Long total;

}
