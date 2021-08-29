package com.zxk.oauth2.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxk.oauth2.entity.Permission;
import com.zxk.oauth2.service.PermissionService;
import com.zxk.oauth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component(value = "myUserDetalisService")
public class MyUserDetalisService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 在loadUserByUsername方法内校验用户名密码是否正确并返回一个UserDetails对象
     * UserDetails包含user信息
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.zxk.oauth2.entity.User user = userService.findByUsername(username);
        if (user != null) {
            List<Permission> permissions = permissionService.findByUserId(user.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList <>();
            for (Permission permission : permissions) {
                if (permission != null && permission.getName()!=null) {

                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                    //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
    }
}
