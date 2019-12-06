package com.wn.dao;

import com.wn.pojo.Collect;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2019/12/6.
 */
@Mapper
public interface CollectDao {
    /*
    用户加入收藏
     */
    int insUserOne(Collect collect);
    /*
    用户收藏查询
     */
    List<Collect> selListByuser(Integer user_id);
}
