package com.kcl.security;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.StrUtil;

/**
 * 项目名： learn_spring_security
 * 包名:    com.kcl.security
 * 文件名   JwtAuthenticationFilter
 * 创建者
 * 创建时间: 2021/5/27 8:27 PM
 * 描述  security授权过滤器
 */


public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    

    @Autowired
    JwtUtils mJwtUtils;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String headerToken = request.getHeader(JwtUtils.HEADER);
        if(StrUtil.isBlankOrUndefined(headerToken)){
            chain.doFilter(request,response);
            return;
        }

        System.out.println("headerToken : " + headerToken);

        // SignatureVerificationException      token值不对,签名不一致，更改了
        // TokenExpiredException               过期异常
        // AlgorithmMismatchException:		   算法不匹配异常
        // InvalidClaimException:			   失效的payload异常
        String username;
        String password;
        try {
            DecodedJWT decodedJWT = JwtUtils.verify(headerToken);
            Map<String, Claim> claims = decodedJWT.getClaims();
            username = claims.get("username").asString();
            System.out.println("username : " + username);

        } catch (SignatureVerificationException e) {
            throw new RuntimeException("token值不对,签名不一致，更改了");
        }catch (TokenExpiredException e) {
            throw new RuntimeException("过期异常");
        }catch (AlgorithmMismatchException e) {
            throw new RuntimeException("算法不匹配异常");
        }catch (InvalidClaimException e) {
            throw new RuntimeException("失效的payload异常");
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException("未知异常");
        }

        //从数据库获取当前用户
        if("admin".equals(username)){
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken("admin", null, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admin"));
            //"强制" 增加 用户认证信息, 认证成功
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        chain.doFilter(request,response);

    }
}

