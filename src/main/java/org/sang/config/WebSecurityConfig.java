package org.sang.config;

import org.sang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by albert on 2019/12/20.
 */
// WebSecurity(WEB安全)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    // AuthenticationManagerBuilder(身份验证管理生成器)
    @Override
    // 重写了configure参数为AuthenticationManagerBuilder的方法
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //并根据传入的AuthenticationManagerBuilder中的userDetailsService方法来接收我们自定义的认证方法。
        //且该方法必须要实现UserDetailsService这个接口。
        auth.userDetailsService(userService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
            }

            /**
             * @param charSequence 明文
             * @param s 密文
             * @return
             */
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(DigestUtils.md5DigestAsHex(charSequence.toString().getBytes()));
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http);
        // 定制请求的授权规则
        // http.authorizeRequests()其中这里的意思是指通过authorizeRequests()方法来开始请求权限配置。
        //而接着的.anyRequest().authenticated()是对http所有的请求必须通过授权认证才可以访问

        // 每个matcher按照他们的声明顺序执行

        http.authorizeRequests()
                // .antMatchers("/register").permitAll() // 用户可任意访问
                .antMatchers("/admin/category/all").authenticated() // 用户登录后可以访问
               // .antMatchers("/admin/**","/reg").hasRole("超级管理员")///admin/**的URL都需要有超级管理员角色，如果使用.hasAuthority()方法来配置，需要在参数中加上ROLE_,如下.hasAuthority("ROLE_超级管理员")
                .antMatchers("/admin/user/**","/admin/roles","/admin/category/").hasRole("超级管理员")
                .anyRequest().authenticated()  // 其他的路径都是登录后即可访问
                .and()
                .formLogin()
              //  .loginPage("/login")
                  .loginPage("/login_page") // 指定登录页的路径
                // 必须允许所有用户访问我们的登录页(例如为验证的用户，否则验证流程就会进入死循环)
                // 这个formLogin().permitAll()方法允许所有用户基于表单登录访问/login这个page
                // 登录成功
                .successHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter out = httpServletResponse.getWriter();
                out.write("{\"status\":\"success\",\"msg\":\"登录成功\"}");
                out.flush();
                out.close();
            }
        })
                // 登录失败
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter out = httpServletResponse.getWriter();
                        out.write("{\"status\":\"error\",\"msg\":\"登录失败\"}");
                        out.flush();
                        out.close();
                    }
                }).loginProcessingUrl("/login")
                /**
                 * 1）HttpSecurity支持cors。
                 *                  * 2）默认会启用CRSF，此处因为没有使用thymeleaf模板（会自动注入_csrf参数），
                 *                  * 要先禁用csrf，否则登录时需要_csrf参数，而导致登录失败。
                 *                  * 3）antMatchers：匹配 "/" 路径，不需要权限即可访问，匹配 "/user" 及其以下所有路径，
                 *                  *  都需要 "USER" 权限
                 *                  * 4）配置登录地址和退出地址
                 */
                .usernameParameter("username").passwordParameter("password").permitAll()
                .and()
                .logout() // 登出
                .permitAll()
                .and()
                .csrf().disable() // 禁用csrf，否则登录时需要_csrf参数，而导致登录失败。
                .exceptionHandling().accessDeniedHandler(getAccessDeniedHandler());
    }

    // WebSecurity(WEB安全)
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/blogimg/**","/index.html","/static/**","/register");
    }

    @Bean
    AccessDeniedHandler getAccessDeniedHandler() {
        return new AuthenticationAccessDeniedHandler();
    }
}