# learnSpringSecurity-JWTs
# 尚硅谷SpringSecurity+JWT
SpringSecurity基础部分

JWT操作

拿出了谷粒学院中的认证授权的功能来讲

网关配置，工具类没怎么用

重写了认证的过滤 `UsernamePasswordAuthenticationFilter`,重写这个的好处是添加了Redis缓存，在Redis中的数据，用户不需要重复认证

`SecurityConfig` extends `WebSecurityConfigurerAdapter` 总的配置类

`MyAccessDeniedHandler` implements `AccessDeniedHandler` 403过滤器(已认证)

`JwtAuthenticationEntryPoint `implements` AuthenticationEntryPoint`权限过滤器(未认证)

`MyAuthenticationSuccessHandler `implements` AuthenticationSuccessHandler`登录成功过滤器

`MyAuthenticationFailureHandler` implements `AuthenticationFailureHandler`登录失败过滤器

`JwtAuthenticationFilter `extends` BasicAuthenticationFilter`自定义认证过滤器，实现token验证，登录

自定义403拦截

` ErrorPageInterceptor` implements` HandlerInterceptor`

` MyWebMvcConfiger implements WebMvcConfigurer`

`UsernamePasswordAuthenticationFilter`整个认证的过程(表单接受,生成token,成功失败处理)

用户认证+授权(认证的中间部分)

`UserDetailServiceImpl implements UserDetailsService`

# 玛歌的SpringSecurity + JWT
SpringSecurity中的基础部分
SpringCloud Oauth2 之后就没有听了
