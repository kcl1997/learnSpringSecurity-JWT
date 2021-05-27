package com.kcl.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import cn.hutool.json.JSONUtil;

/**
 * 项目名： learn_spring_security
 * 包名:    com.kcl.security
 * 文件名   ResponseUtil
 * 创建者
 * 创建时间: 2021/5/27 6:10 PM
 * 描述  ${TODO}
 */
public class ResponseUtil {


    public static void out(HttpServletResponse response,Result result) throws IOException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN); //403
        //返回json格式
        response.setHeader("Content-Type","application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSONUtil.toJsonStr(result));
        writer.flush();
        writer.close();
    }
}

