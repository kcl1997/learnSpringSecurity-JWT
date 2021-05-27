package com.kcl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名： learn_spring_security
 * 包名:    com.kcl.service
 * 文件名   UserDetailServiceImpl
 * 创建者
 * 创建时间: 2021/5/26 3:38 PM
 * 描述  ${TODO}
 */

@Service
public class UserDetailServiceImpl implements UserDetailsService {



    @Autowired
    PasswordEncoder mPasswordEncoder;
    //自定义登录逻辑
    //security自带登录界面 会 和该方法进行对接
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查数据库，如果不存在就抛出UserNotFoundException
        if(!"admin".equals(username) && !"zhangsan".equals(username)) throw new UsernameNotFoundException("用户名不存在");

        if("admin".equals(username)){
            //数据库中已经进行加密的密码
            String dbpassword = mPasswordEncoder.encode("123456");
            // 用户名 + 密码 + 授权权限
            List<GrantedAuthority> list = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admin");
            return new User(username,dbpassword, list); //用户输入的pass 和 dbpassword进行比较，security会自动校验是否成功
        }
        if("zhangsan".equals(username)){
            //数据库中已经进行加密的密码
            String dbpassword = mPasswordEncoder.encode("123456");
            // 用户名 + 密码 + 授权权限
            return new User(username,dbpassword, AuthorityUtils.commaSeparatedStringToAuthorityList("vip,ROLE_user"));
        }
        return null;
    }
}

