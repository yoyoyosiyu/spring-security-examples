package com.huayutech.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@SpringBootApplication
public class SimpleApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(SimpleApplication.class, args);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();

        http.authorizeRequests().antMatchers("/userinfo").hasAnyRole("USER", "ADMIN");
        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
        //http.authorizeRequests().anyRequest().authenticated().withObjectPostProcessor()

        // 在开启csrf的情况下使用，如果关闭csrf的情况下可以注释掉
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"));


    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
            .inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("user")).roles("USER")
            .and()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    }

    @GetMapping("/userinfo")
    protected Principal doUserInfo(Principal principal) {
        return principal;
    }

    @GetMapping("/admin/userinfo")
    protected Principal doGetAdminUserInfo(Principal principal) {
        return principal;
    }
}
