package com.kcl.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目名： learn_spring_security
 * 包名:    com.kcl.security
 * 文件名   Result
 * 创建者
 * 创建时间: 2021/5/27 6:10 PM
 * 描述  ${TODO}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private Integer code; //200 ok
    private String msg;
    private Object obj;

    public static Result success(String msg, Object obj){
        Result result = new Result(200,msg,obj);
        return  result;
    }

    public static Result success(Object obj){
        return  success("请求成功",obj);
    }


    public static Result error(Integer code,String msg,Object obj){
        Result result = new Result(code,msg,obj);
        return  result;
    }
    public static Result error(Integer code,String msg){
        return error(code,msg,null);
    }
    public static Result error(Integer code){
        return error(code,"请求失败");
    }

}

