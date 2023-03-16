package com.company.portal.demo.security;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomRoleVoter implements AccessDecisionVoter<Object> {

    private static final String ADMIN_ROLE = "ADMIN";

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        for (ConfigAttribute attribute : attributes) {
            if (attribute.getAttribute().equals(ADMIN_ROLE)) {
                for (GrantedAuthority authority : authentication.getAuthorities()) {
                    if (authority.getAuthority().equals(ADMIN_ROLE)) {
                        return ACCESS_GRANTED;
                    }
                }
                return ACCESS_DENIED;
            }
        }
        return ACCESS_ABSTAIN;
    }
}