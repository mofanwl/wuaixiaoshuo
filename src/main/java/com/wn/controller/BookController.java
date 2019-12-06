package com.wn.controller;

import com.wn.pojo.Books;
import com.wn.pojo.BooksPage;
import com.wn.pojo.Btype;
import com.wn.service.BtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BtypeService btypeService;


    @RequestMapping("/booktype")
    public List<Btype> selectBookType(){
        return btypeService.selectBookType();
    }

    @RequestMapping("/selectAll")
    public List<Books> selectAll(){
        return btypeService.selectAll();
    }

    @RequestMapping("/selectAllMoney")
    public List<Books> selectAllMoney(){
        return btypeService.selectAllMoney();
    }

    @RequestMapping("/selectAllByType")
    public List<Books> selectAllByType(@RequestParam("type") Integer type){
        if(type == null){
            type = 8;
        }
        return btypeService.selectAllByType(type);
    }

    @RequestMapping("/selectAllByName")
    public List<Books> selectAllByName(@RequestParam("name") String name){
        System.out.println(name);
        if(name.equals("")){
            name = "小";
        }
        return btypeService.selectAllByName(name);
    }

    @RequestMapping("findAll/{page}/{size}")
    public BooksPage findAll(@PathVariable("page") Integer page , @PathVariable("size") Integer size){
        BooksPage list = btypeService.findAll(page,size);
        return list;
    }

}
