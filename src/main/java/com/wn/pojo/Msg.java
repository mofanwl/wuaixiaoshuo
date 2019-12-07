package com.wn.pojo;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2019/12/6.
 */
@Data
public class Msg<T> {
    private Long total;
    private List<T> list;
}
