package com.company.portal.demo.constant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
/**
 *
 * This file contains constant values for exceptions.
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionsConstant {
    public static final String EXCEPTION_TYPE_RUNTIME = "RUNTIME";
    public static final String EXCEPTION_TYPE_BUSINESS_RUNTIME = "BUSINESS_RUNTIME";
    public static final String EXCEPTION_TYPE_VALIDATION = "VALIDATION";
    public static final String EXCEPTION_TYPE_UNCATEGORIZED = "UNCATEGORIZED";
    public static final boolean STATUS_FAILURE = false;
}