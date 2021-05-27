package com.kcl.config;

import com.kcl.handler.MyAccessDeniedHandler;
import com.kcl.handler.MyAuthenticationFailureHandler;
import com.kcl.handler.MyAuthenticationSuccessHandler;
import com.kcl.security.JwtAuthenticationEntryPoint;
import com.kcl.security.JwtAuthenticationFilter;
import com.kcl.service.UserDetailServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 项目名： learn_spring_security
 * 包名:    com.kcl.config
 * 文件名   SecurityConfig
 * 创建者
 * 创建时间: 2021/5/26 3:27 PM
 * 描述  ${TODO}
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Autowired
    MyAccessDeniedHandler mMyAccessDeniedHandler; //403
    @Autowired
    JwtAuthenticationEntryPoint mJwtAuthenticationEntryPoint;

    @Autowired
    UserDetailServiceImpl mUserDetailsService; //自定义认证逻辑
    @Autowired
    PasswordEncoder mPasswordEncoder;

    @Bean
    public PasswordEncoder getPw(){
        return new BCryptPasswordEncoder();
    }


    @Bean //授权过滤器
    public JwtAuthenticationFilter mJwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManager()); //父类 WebSecurityConfigurerAdapter的方法
    }

    //认证配置
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(mUserDetailsService).passwordEncoder(mPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //关掉csrf才可以表单接口和UserDetailService方法对接

        http.formLogin()
                .usernameParameter("username123")
                .passwordParameter("password123")
                .loginPage("/login") //跳转至登录页，界面
                .loginProcessingUrl("/user/login") //表单处理接口，SpringSecurity来调用，不需要自己实现该接口
//                .successForwardUrl("/index")
                .successHandler(new MyAuthenticationSuccessHandler("/index")) //自定义登录成功处理器
//                .failureForwardUrl("/loginError");
                .failureHandler(new MyAuthenticationFailureHandler("/loginError")); //自定义登录失败树立起



        //认证
        http.authorizeRequests()
                .antMatchers("/login").permitAll() //登录页放行
                .antMatchers("/loginError").permitAll() //登录失败页放行
                .antMatchers("/404").permitAll()
                .antMatchers("/css/**","/js/**", "/img/**").permitAll() //static目录下放行
//                .antMatchers("/vip").hasAuthority("vip")   //权限控制
//                .antMatchers("/admin").hasRole("admin") //角色控制
                .anyRequest().authenticated(); //所有界面都需要认证

        //403权限不足，自定义处理
        http.exceptionHandling()
                .accessDeniedHandler(mMyAccessDeniedHandler)
                .authenticationEntryPoint(mJwtAuthenticationEntryPoint);


        //退出登录
        http.logout()
                .logoutUrl("/user/logout") //自定义退出接口
                .logoutSuccessUrl("/login"); //退出成功后要跳转的界面

        //配置自定义过滤器
        http.addFilter(mJwtAuthenticationFilter());

    }



}

