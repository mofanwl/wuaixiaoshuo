package com.wn.controller;

import com.wn.pojo.*;
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

    @RequestMapping("findAllByBooksType/{type}/{page}/{size}")
    public BooksPage findAllByBooksType(@PathVariable("type") Integer type,@PathVariable("page") Integer page , @PathVariable("size") Integer size){
        BooksPage list = btypeService.findAllByBooksType(type, page, size);
        return list;
    }

    @RequestMapping("/selectAllById/{id}")
    public Books selectAllById(@PathVariable("id") Integer id){
        return btypeService.selectAllById(id);
    }

    @RequestMapping("/selectAllVIP")
    public List<Books> selectAllVIP(){
        return btypeService.selectAllVIP();
    }

    @RequestMapping("/selectAllCount")
    public List<Books> selectAllCount(){
        return btypeService.selectAllCount();
    }


    @RequestMapping("/selectAllStatus")
    public List<BookStatus> selectAllStatus(){
        return btypeService.selectAllStatus();
    }

    @RequestMapping("/selectAllBookVip")
    public List<BooksVip> selectAllBookVip(){
        return btypeService.selectAllBookVip();
    }


    @RequestMapping("findAllByBooksStatus/{status}/{page}/{size}")
    public BooksPage findAllByBooksStatus(@PathVariable("status") Integer status,@PathVariable("page") Integer page , @PathVariable("size") Integer size){
        BooksPage list = btypeService.findAllByBooksStatus(status, page, size);
        return list;
    }

    @RequestMapping("findAllByBooksVip/{vip}/{page}/{size}")
    public BooksPage findAllByBooksVip(@PathVariable("vip") Integer vip,@PathVariable("page") Integer page , @PathVariable("size") Integer size){
        BooksPage list = btypeService.findAllByBooksVip(vip, page, size);
        return list;
    }

}
