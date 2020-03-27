package com.shanzuwang.config;//package com.shanzuwang.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * spring security配置
// *
// * @author hsx
// * @since 2020/2/1
// */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http.csrf().disable()
////                .authorizeRequests()
////                //swagger 允许访问
////                .regexMatchers("/doc/.*")
////                .permitAll()
////
////                //登录接口权限 允许
////                .antMatchers("/user/login")
////                //.mvcMatchers(HttpMethod.POST,"/user/login")
////                .permitAll()
////                //其余接口 访问受限
////                .anyRequest().authenticated()
////                .and()
////
////                //token 访问,关闭session
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                .and()
////                .exceptionHandling()
////                //自定义401返回
//////                .authenticationEntryPoint(new TokenAuthenticationEntryPoint())
////                .and()
////                .logout().permitAll()
//////                .and()
////                //token filter 根据header 生成认证信息
//////                .addFilterBefore(new AuthFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
////                ;
//    }
//}
