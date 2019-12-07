package com.wn.config;

import com.wn.pojo.Admin;
import com.wn.pojo.UserPermission;
import com.wn.service.AdminPermissionService;
import com.wn.service.AdminService;
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

public class MyRealmAdmin extends AuthorizingRealm {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminPermissionService adminPermissionService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        List<UserPermission> userPermissions = adminPermissionService.selectByRoler(primaryPrincipal);
        if (userPermissions != null && userPermissions.size() > 0) {
            //去重
            Collection list = new HashSet<>();
            for (UserPermission per : userPermissions) {
                list.add(per.getPer_name());
            }
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addStringPermissions(list);
            return simpleAuthorizationInfo;
        }
        return null;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username=(String)authenticationToken.getPrincipal();
        Admin admin=new Admin();
        admin.setAdmin_name(username);
        List<Admin> admins = adminService.selectAll(admin);

        if(!admins.isEmpty()){
            System.out.println(admins.get(0).getAdmin_pwd()+"--------------------------------");
            SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(admins.get(0).getAdmin_name(),admins.get(0).getAdmin_pwd(),getName());
            return  simpleAuthenticationInfo;
        }
        return null;
    }
}
