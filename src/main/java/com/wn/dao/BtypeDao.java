package com.wn.dao;

import com.wn.pojo.BookStatus;
import com.wn.pojo.Books;
import com.wn.pojo.BooksVip;
import com.wn.pojo.Btype;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BtypeDao {

    List<Btype> selectBookType();

    List<Books> selectAll();

    List<Books> selectAllMoney();

    List<Books> selectAllByType(Integer type);

    List<Books> selectAllByName(String name);

    Books selectAllById(Integer id);

    List<Books> selectAllVIP();

    List<Books> selectAllCount();

    List<BookStatus> selectAllStatus();

    List<BooksVip> selectAllBookVip();



}
