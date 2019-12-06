package com.wn.service;

import com.wn.pojo.Collect;

import java.util.List;

/**
 * Created by Administrator on 2019/12/6.
 */
public interface CollectService {
    /*
用户加入收藏
 */
    int insUserOne(Collect collect);
    /*
    用户收藏查询
     */
    List<Collect> selListByuser(Integer user_id);
}
