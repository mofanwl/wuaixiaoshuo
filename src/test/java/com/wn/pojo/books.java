package com.wn.pojo;

import lombok.Data;

/**
 * Created by Administrator on 2019/12/5.
 */
@Data
public class books {
    //books

    private Integer books_id;
    private String books_name;
    private Integer books_type;
    private Integer books_vip;

    //wn_book_xs
    private Integer xs_id;
    private String url;
    private Integer xs_type;
}
