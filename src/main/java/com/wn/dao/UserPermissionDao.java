package com.wn.dao;

import com.wn.pojo.UserPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2019/12/1.
 */
@Mapper
public interface UserPermissionDao {
    List<UserPermission> selectByRoler(String name);
}
