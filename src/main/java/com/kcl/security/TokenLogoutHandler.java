package com.kcl.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目名： learn_spring_security
 * 包名:    com.kcl.security
 * 文件名   TokenLogoutHandler
 * 创建者
 * 创建时间: 2021/5/27 5:53 PM
 * 描述  退出登录，删除redis里面的内容
 */
public class TokenLogoutHandler implements LogoutHandler {
    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {

    }
}

