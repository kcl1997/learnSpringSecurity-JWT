package com.kcl.inteceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目名： learn_spring_security
 * 包名:    com.kcl.inteceptor
 * 文件名   ErrorPageInterceptor
 * 创建者
 * 创建时间: 2021/5/26 7:48 PM
 * 描述  ${TODO}
 */

public class ErrorPageInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(response.getStatus() == 404){
            response.sendRedirect("/404");
            return false;
        }
        return true; //其他的放行
    }
}

