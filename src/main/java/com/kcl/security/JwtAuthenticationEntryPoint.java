package com.kcl.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目名： learn_spring_security
 * 包名:    com.kcl.security
 * 文件名   JwtAuthenticationEntryPoint
 * 创建者
 * 创建时间: 2021/5/27 6:07 PM
 * 描述  必须要写这个，当用户未登录时访问其他界面，如果不重写类，系统自动返回至登录界面！
 *      如果是前后端不分离， 就没有必要写这个类
 *  * AuthenticationEntryPoint 用来解决匿名用户访问无权限资源时的异常
 *  * AccessDeineHandler       用来解决认证过的用户访问无权限资源时的异常
 */

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        Result result = Result.error(403,"未登录，权限不足，请先登录");
        ResponseUtil.out(httpServletResponse,result);
    }
}

