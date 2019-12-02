package com.wn.config;

import com.wn.dao.UserPermissionDao;
import com.wn.pojo.User;
import com.wn.pojo.UserPermission;
import com.wn.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Administrator on 2019/11/22.
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private UserPermissionDao userPermissionDao;

    //权限 的
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String  userename  =(String) principals.getPrimaryPrincipal();
        //用登陆名去查询该用户的权限
        List<UserPermission> userPermissions = userPermissionDao.selectByRoler(userename);
        if(userPermissions!=null&&userPermissions.size()>0){
            //去除重复权限
            Collection list=new HashSet();
            for(UserPermission p:userPermissions){
                list.add(p.getPer_name());
            }
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addStringPermissions(list);
            return simpleAuthorizationInfo;
        }
        return null;
    }
    //登陆的
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取当前登录 的用户名
        String username =(String) token.getPrincipal();
        //通过用户名去数据库查询正解的密码
        User user = userService.selectOne(username);
        if(user!=null){
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUser_name(), user.getUser_pwd(), getName());
            System.out.println(getName()+"=============================================");
            return  simpleAuthenticationInfo;
        }
        return null;
    }
}
