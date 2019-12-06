package com.wn.service.impl;

import com.wn.dao.BtypeDao;
import com.wn.pojo.Books;
import com.wn.pojo.BooksPage;
import com.wn.pojo.Btype;
import com.wn.repository.BooksRepository;
import com.wn.service.BtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BtypeServiceImpl implements BtypeService {

    @Autowired
    private BtypeDao btypeDao;

    @Autowired
    private BooksRepository booksRepository;

    @Override
    public List<Btype> selectBookType() {
        return btypeDao.selectBookType();
    }

    @Override
    public List<Books> selectAll() {
        return btypeDao.selectAll();
    }

    @Override
    public List<Books> selectAllMoney() {
        return btypeDao.selectAllMoney();
    }

    @Override
    public List<Books> selectAllByType(Integer type) {
        return btypeDao.selectAllByType(type);
    }

    @Override
    public List<Books> selectAllByName(String name) {
        return btypeDao.selectAllByName(name);
    }

    @Override
    public BooksPage findAll(Integer page, Integer size) {
        PageRequest of = PageRequest.of(page-1, size);
        Page<Books> all = booksRepository.findAll(of);
        List<Books> content = all.getContent();
        int totalPages = all.getTotalPages();
        long totalElements = all.getTotalElements();
        BooksPage booksPage = new BooksPage();
        booksPage.setList(content);
        booksPage.setPage(totalPages);
        booksPage.setTotal(totalElements);
        return booksPage;
    }

}
