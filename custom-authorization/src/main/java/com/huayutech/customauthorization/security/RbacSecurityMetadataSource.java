package com.huayutech.customauthorization.security;

import com.huayutech.customauthorization.config.SecuredTarget;
import lombok.Data;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class RbacSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private List<SecuredTarget> securedTargets;

    public void setSecuredTargets(List<SecuredTarget> securedTargets) {
        this.securedTargets = securedTargets;
    }

    /**
     *
     * @param object 被保护的对象
     * @return 需要应用到被保护对象的属性，如果没有的话，应该返回一个空的集合（不是null）
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        final HttpServletRequest request = ((FilterInvocation) object).getRequest();

        List<ConfigAttribute> configAttributes = new ArrayList<>();

        if (securedTargets != null && securedTargets.size() > 0) {

            AntPathMatcher antPathMatcher = new AntPathMatcher();

            String path = request.getServletPath();

            for(SecuredTarget securedTarget: securedTargets) {

                for (String pattern: securedTarget.patterns) {



                    if (antPathMatcher.match(pattern, path)) {

                        ObjectIdConfigAttribute configAttribute = new ObjectIdConfigAttribute();

                        configAttribute.setObjectId(securedTarget.id);

                        Map templateVariables = antPathMatcher.extractUriTemplateVariables(pattern, path);
                        if (templateVariables.size()>0) {
                            configAttribute.setTemplateVariables(templateVariables);
                        }

                        configAttributes.add(configAttribute);
                    }


                }

            }

        }


        return null;
    }

    /**
     * Spring Security 启动时会校验每个ConfigAttributes, 如果不需要校验的化，返回 null
     * @return
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    @Data
    public static class ObjectIdConfigAttribute implements ConfigAttribute {

        protected Map templateVariables;

        protected String objectId;

        @Override
        public String getAttribute() {
            return null;
        }
    }

}
