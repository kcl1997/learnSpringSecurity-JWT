package com.kcl.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import lombok.Data;

/**
 * 项目名： learn_spring_security
 * 包名:    com.kcl.security
 * 文件名   TokenManager
 * 创建者
 * 创建时间: 2021/5/27 11:47 AM
 * 描述  ${TODO}
 */

@Data
@Component
public class JwtUtils {

     public static final long TOKEN_EXPIRATION_SECOND = 60*60*24*7; //7天
     public static final String MIYAO = "miyao"; //密钥
     public static final String HEADER = "token";


    //1.创建token
    public static String createToken(Map<String,String> map){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,7); // 7天

        JWTCreator.Builder builder = JWT.create();

        Set<String> keySet = map.keySet();
        for(String key : keySet)  builder.withClaim(key,map.get(key));

        String token = builder.withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(MIYAO));

        return token;
    }


    //2.解析token,获取
    public static DecodedJWT verify(String token){
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(MIYAO)).build().verify(token);
        return decodedJWT;
    }


    public static void main(String[] args) {
        //1.创建token
        Map<String,String> map = new HashMap<>();
        map.put("username","kcl");
        map.put("password","123abc");
        System.out.println(createToken(map));

        //2.解析token
        // SignatureVerificationException      token值不对,签名不一致，更改了
        // TokenExpiredException               过期异常
        // AlgorithmMismatchException:		   算法不匹配异常
        // InvalidClaimException:			   失效的payload异常
        DecodedJWT decodedJWT = verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEyM2FiYyIsImV4cCI6MTYyMjcxMzA3NiwidXNlcm5hbWUiOiJrY2wifQ.gik42X92-8ojidc0NsfhHlGuG41Z6TFYyu9RrqtoTec");
        Map<String, Claim> claims = decodedJWT.getClaims();
        String username = claims.get("username").asString();
        String password = claims.get("password").asString();
        Long exp = claims.get("exp").asLong();
        System.out.println(username);
        System.out.println(password);
        System.out.println(exp);


    }
}

