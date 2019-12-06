package com.wn.controller;

import com.wn.pojo.Collect;
import com.wn.service.impl.CollectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Map<String,String> insUserOne(Collect collect){
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
    @RequestMapping("/selListByuser")
    public List<Collect> selListByuser(@RequestParam("user_id")Integer user_id){
        System.out.println(user_id);
        List<Collect> collects = collectService.selListByuser(user_id);
        return collects;
    }

}
