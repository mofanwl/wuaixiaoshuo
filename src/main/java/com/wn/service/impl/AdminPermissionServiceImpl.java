package com.wn.service.impl;

import com.wn.dao.AdminPermissionDao;
import com.wn.pojo.Group;
import com.wn.pojo.UserPermission;
import com.wn.service.AdminPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminPermissionServiceImpl implements AdminPermissionService {
    @Autowired
    private AdminPermissionDao adminPermissionDao;
    @Override
    public List<UserPermission> selectByRoler(String name) {
        return adminPermissionDao.selectByRoler(name);
    }

    @Override
    public List<Group> selectByGroup(String name) {
        return adminPermissionDao.selectByGroup(name);
    }
}
