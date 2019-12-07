package com.wn.repository;

import com.wn.pojo.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books,Integer> {


    //根据小说类型查询小说（分页）
    Page<Books> findAllByBooksType(Integer type, Pageable pageable);

    //根据小说进度查询小说（分页）
    Page<Books> findAllByBooksStatus(Integer status,Pageable pageable);

    //根据小说VIP查询小说（分页）
    Page<Books> findAllByBooksVip(Integer vip,Pageable pageable);



}
