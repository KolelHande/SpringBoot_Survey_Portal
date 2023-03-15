package com.company.portal.demo.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PortalConstant {

    public static final String BEARER_TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_NAME = "Authorization";

    public static final String DEFAULT_PAGE_NUMBER = "0";
    public  static final String DEFAULT_PAGE_SIZE = "12";
    public static final String DEFAULT_SORT_BY_PUBLISH_DATE = "publishDate";
    public static final String DEFAULT_SORT_BY_RESPONSE_DATE = "responseDate";
    public static final String DEFAULT_SORT_DIRECTION = "desc";

    
    public static final String[] AUTH_SWAGGER_WHITE_LIST = {
            "/v3/api-docs/**",
            "/v2/api-docs/**",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/swagger-ui/index.html",
            "/webjars/**"
    };
}