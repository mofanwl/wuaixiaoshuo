package com.wn.service;

import com.wn.pojo.*;

import java.util.List;

public interface BtypeService {

    List<Btype> selectBookType();

    List<Books> selectAll();

    List<Books> selectAllMoney();

    List<Books> selectAllByType(Integer type);

    List<Books> selectAllByName(String name);

    BooksPage findAll(Integer page, Integer size);

    BooksPage findAllByBooksType(Integer type, Integer page, Integer size);

    Books selectAllById(Integer id);

    List<Books> selectAllVIP();

    List<Books> selectAllCount();

    BooksPage findAllByBooksStatus(Integer status, Integer page, Integer size);

    List<BookStatus> selectAllStatus();

    List<BooksVip> selectAllBookVip();

    BooksPage findAllByBooksVip(Integer vip, Integer page, Integer size);

}
