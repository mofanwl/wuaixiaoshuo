package com.wn.service;

import com.wn.pojo.Group;
import com.wn.pojo.UserPermission;

import java.util.List;

public interface AdminPermissionService {
    List<UserPermission> selectByRoler(String name);

    /*
    查询admin 角色
*/
    List<Group> selectByGroup(String name);
}
