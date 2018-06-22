package com.huayutech.customauthorization.security;

import com.huayutech.customauthorization.config.SecuredTarget;
import com.huayutech.customauthorization.domain.Permission;
import com.huayutech.customauthorization.domain.Role;
import com.huayutech.customauthorization.domain.RolePermission;
import com.huayutech.customauthorization.repository.RolePermissionRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class RbacAccessDecisionManager implements AccessDecisionManager {

    @Autowired
    RolePermissionRespository rolePermissionRespository;

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

        Set<Permission> permissions = new HashSet<>();

        for (ConfigAttribute configAttribute: collection) {

            if (configAttribute instanceof RbacSecurityMetadataSource.SecuredTargetConfigAttribute) {

                RbacSecurityMetadataSource.SecuredTargetConfigAttribute securedTargetConfigAttribute
                        = (RbacSecurityMetadataSource.SecuredTargetConfigAttribute) configAttribute;

                SecuredTarget securedTarget = securedTargetConfigAttribute.getSecuredTarget();

                Permission permission= new Permission();
                permission.setId(securedTarget.getId());

                permissions.add(permission);
            }
        }

        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setId("a760e2bb-75cd-11e8-b70d-f832e4887124");
        roles.add(role);

        Collection<RolePermission> rolePermission = rolePermissionRespository.findAllByRoleInAndPermissionIn(roles, permissions);



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
