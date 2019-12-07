package com.wn.pojo;

import lombok.Data;

import java.util.List;

@Data
public class BooksPage<T> {

    private List<T> list;
    private Integer page;
    private Long total;

}
