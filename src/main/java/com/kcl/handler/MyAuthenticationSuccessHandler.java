package com.kcl.handler;

import com.kcl.security.JwtUtils;
import com.kcl.security.ResponseUtil;
import com.kcl.security.Result;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目名： learn_spring_security
 * 包名:    com.kcl.handler
 * 文件名   My
 * 创建者
 * 创建时间: 2021/5/26 4:57 PM
 * 描述  自定义登录成功处理器
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private String url;

    public MyAuthenticationSuccessHandler(String url) {
        this.url = url;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//        httpServletResponse.sendRedirect(url);
//        User user = (User) authentication.getPrincipal();
//        System.out.println(user.getUsername());
//        System.out.println(user.getPassword()); //null
//        System.out.println(user.getAuthorities());

        //前后端分离模式 --> 返回jwt的三段token
        if(authentication != null){
            //登录成功就在服务器端退出，清理资源
            new SecurityContextLogoutHandler().logout(httpServletRequest,httpServletResponse,authentication);
        }
        Map<String,String> map = new HashMap<>();

        map.put("username",authentication.getName());
        System.out.println("登录成功： map.put username: " + authentication.getName());
        ResponseUtil.out(httpServletResponse, Result.success(JwtUtils.createToken(map)));

    }
}

