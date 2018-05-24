package com.kgl1688.config;

import com.kgl1688.security.MyAccessDecisionManager;
import com.kgl1688.security.MySecurityMetadataSource;
import com.kgl1688.security.MyPasswordEncoder;
import com.kgl1688.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;


//@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication()
        //        .withUser("user").password("password").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.userDetailsService(new MyUserDetailsService())
                .authorizeRequests()
                    .antMatchers("/login", "/", "/signup").permitAll()
                    .and().
                formLogin()
                    .and()
                .authorizeRequests()
                    .anyRequest()
                    .authenticated()
                    // 加入我们自己的权限控制器
                    .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                        @Override
                        public <O extends FilterSecurityInterceptor> O postProcess(O filterSecurityInterceptor) {
                            filterSecurityInterceptor.setSecurityMetadataSource(securityMetadataSource());
                            filterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager());
                            return filterSecurityInterceptor;
                        }
                    })
        ;

    }

    // 注册一个自己的实现UserDetailsService接口的类,前提是没有显示的配置AuthenticationManagerBuilder 或者存在一个AuthenticationProviderBean
    @Bean
    public MyUserDetailsService springDataUserDetailsService() {
        return new MyUserDetailsService();
    }


    // 注册一个密码转换器，只要实现PasswordEncoder接口即会被发现
    @Bean
    public MyPasswordEncoder  passwordEncoder() {
        return new MyPasswordEncoder();
    }

    @Bean
    public FilterInvocationSecurityMetadataSource securityMetadataSource() {
        return new MySecurityMetadataSource();
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        return new MyAccessDecisionManager();
    }

}
