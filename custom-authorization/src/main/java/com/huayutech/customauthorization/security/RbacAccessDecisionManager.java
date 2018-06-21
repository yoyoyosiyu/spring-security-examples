package com.huayutech.customauthorization.security;

import com.huayutech.customauthorization.config.SecuredTarget;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;

import java.util.Collection;
import java.util.List;

public class RbacAccessDecisionManager implements AccessDecisionManager {

    /**
     * 将会被FilterSecurityInterceptor过滤器调用来决定是否通过授权，抛出异常表示未通过，否则为通过
     * @param authentication 认证信息
     * @param o FilterInterceptor, 可以转型为HttpServletRequest
     * @param collection 由SecurityMetaSource提供的安全配置属性
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {

        System.out.println(authentication);

    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
