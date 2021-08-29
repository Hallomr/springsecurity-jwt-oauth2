package com.zxk.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetalisService userDetalisService;

    /**
     * 指定加密方式
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 支持password模式
     *
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //用户登陆，会被AuthenticationProcessingFilter拦截，调用AuthenticationManager的实现
        //而AuthenticationManager调用Provider，provider调用userDetaisService（customUserService）来根据username获取真实的数据库信息。
        auth.userDetailsService(userDetalisService); //user Details Service验证
    }

    /**
     * 配置URL访问授权,必须配置authorizeRequests(),否则启动报错
     * 注意:在这里的身份进行认证与授权没有涉及到OAuth的技术：当访问要授权的URL时,请求会被DelegatingFilterProxy拦截,
     * 如果还没有授权,请求就会被重定向到登录界面。在登录成功(身份认证并授权)后,请求被重定向至之前访问的URL。
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() //登陆界面，默认是permit All
                .and()
                .authorizeRequests().antMatchers("/", "/home").permitAll() //不用身份认证可以访问
                .and()
                .authorizeRequests().anyRequest().authenticated() //其它的请求要求必须有身份认证
                .and()
                .csrf() //防止CSRF（跨站请求伪造）配置
                .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize")).disable();
    }
}
