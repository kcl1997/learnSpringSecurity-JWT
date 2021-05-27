package com.kcl.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 项目名： learn_spring_security
 * 包名:    com.kcl.controller
 * 文件名   BaseController
 * 创建者
 * 创建时间: 2021/5/26 4:02 PM
 * 描述  thymeleaf跳转
 */

@Controller
public class BaseController {


    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }


    @RequestMapping("/loginError")
    public String loginError(){
        return "loginError";
    }



    @PreAuthorize("hasAuthority('vip')") //vip权限
    @RequestMapping("/vip")
    public String vip(){
        BasicAuthenticationFilter basicAuthenticationFilter;
        UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter;
        return "vip";

    }


   // @Secured("ROLE_admin") //value值表示角色名
    @PreAuthorize("hasRole('ROLE_admin')") //ROLE_开头或者不用都可以
    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }


    @RequestMapping("/404")
    public String notFound(){
        return "404";
    }
}

