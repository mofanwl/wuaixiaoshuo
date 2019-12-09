package com.wn.pojo;

import lombok.Data;

/**
 * Created by Administrator on 2019/12/6.
 * 用户收藏
 */
@Data
public class Collect {
    //collect
    private Integer books_Id;
    private Integer collect_id;
    private Integer collect_uid;
    private Integer collect_bid;
    //guanlian
    private Integer books_id;
    private String books_name;
    private String books_author;
    private String books_count;
    private String books_pic;
    private String books_url;
    private Integer books_status;

    private String type_name;

    private String vip_name;

}
