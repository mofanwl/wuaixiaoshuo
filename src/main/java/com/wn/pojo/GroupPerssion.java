package com.wn.pojo;

import lombok.Data;

/*
角色权限关联表
 */
@Data
public class GroupPerssion {
    private Integer group_id;
    private Integer permission_id;
}
