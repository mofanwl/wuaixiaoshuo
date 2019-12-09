package com.wn.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wn.pojo.Collect;
import com.wn.pojo.Msg;
import com.wn.service.impl.CollectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/12/6.
 */
@Controller
@RequestMapping("/collect")
@ResponseBody
public class CollectController {

    @Autowired
    private CollectServiceImpl collectService;

    /*
用户加入收藏
     */
    @RequestMapping("/insUserOne")
    public Map<String,String> insUserOne(@RequestBody Collect collect){
        Map<String,String> map=new HashMap<>();
        int i = collectService.insUserOne(collect);
        if(i==1){
            System.out.println("收藏加入成功");
            map.put("msg","ok");
        }else{
            System.out.println("收藏加入失败");
            map.put("msg","no");
        }
        return map;
    }
    /*
    用户收藏查询
     */
    @RequestMapping("/selListByuser/{user_id}/{page}/{size}")
    public Msg<Collect> selListByuser(@PathVariable("user_id")Integer user_id,@PathVariable("page")Integer page,@PathVariable("size")Integer size){
        Msg<Collect> msg = new Msg<>();
        
        PageHelper.startPage(page,size);
        List<Collect> collects = collectService.selListByuser(user_id);
        PageInfo<Collect> collectPageInfo = new PageInfo<>(collects);
        msg.setTotal(collectPageInfo.getTotal());
        msg.setList(collects);
        System.out.println(user_id);

        return msg;
    }
   /*
    用户的收藏删除
     */
   @RequestMapping("/delByBid")
   public Map<String,String> delByBid(@RequestParam("collect_bid")Integer collect_bid){
       System.out.println(collect_bid+"+++++++++++++++++++++");
       Map<String,String> map=new HashMap<>();
       int i = collectService.delByBid(collect_bid);
       if(i==1){
           map.put("msg","ok");
       }else {
           map.put("msg","no");
       }
       return map;
   }


}
