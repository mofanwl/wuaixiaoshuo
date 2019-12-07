package com.wn.dao;

import com.wn.pojo.Group;
import com.wn.pojo.UserPermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminPermissionDao {

    /*
    查询admin 权限selectByGroup
     */
    List<UserPermission> selectByRoler(String name);
    /*
  查询admin 角色
   */
    List<Group> selectByGroup(String name);
}
