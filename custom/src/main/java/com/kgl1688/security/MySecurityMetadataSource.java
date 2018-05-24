package com.kgl1688.security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.Collection;

public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    // 每当有请求的时候，都会调用这个方法获取权限信息
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        System.out.println("CustomSecurityMetadataSource:"+object.toString());

        FilterInvocation filterInvocation = (FilterInvocation)object;
        filterInvocation.getHttpRequest();

        AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher("/userinfo");

        if (antPathRequestMatcher.matches(filterInvocation.getHttpRequest())) {

            Collection<ConfigAttribute> configAttributes = new ArrayList<>();

            ConfigAttribute configAttribute = new SecurityConfig("ROLE_ADMIN");
            configAttributes.add(configAttribute);

            return configAttributes;

        }

        // 返回 null， 表示不需要权限
        return null;
    }

    // 当 Spring Security 启动时会校验每个ConfigAttributes, 如果不需要校验的化，返回 null
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        System.out.println("CustomSecurityMetadataSource getAllConfigAttributes");
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
