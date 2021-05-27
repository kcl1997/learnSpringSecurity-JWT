package com.kcl.config;

import com.kcl.inteceptor.ErrorPageInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 项目名： learn_spring_security
 * 包名:    com.kcl.config
 * 文件名   MyWebMvcConfiger
 * 创建者
 * 创建时间: 2021/5/26 7:52 PM
 * 描述  ${TODO}
 */
@Configuration
public class MyWebMvcConfiger implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ErrorPageInterceptor()).addPathPatterns("/**");
    }
}

