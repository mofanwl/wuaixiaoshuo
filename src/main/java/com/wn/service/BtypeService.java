package com.wn.service;

import com.wn.pojo.Books;
import com.wn.pojo.BooksPage;
import com.wn.pojo.Btype;

import java.util.List;

public interface BtypeService {

    List<Btype> selectBookType();

    List<Books> selectAll();

    List<Books> selectAllMoney();

    List<Books> selectAllByType(Integer type);

    List<Books> selectAllByName(String name);

    BooksPage findAll(Integer page, Integer size);



}
