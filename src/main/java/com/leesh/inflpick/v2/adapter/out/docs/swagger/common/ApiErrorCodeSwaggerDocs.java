package com.leesh.inflpick.v2.adapter.out.docs.swagger.common;

import com.leesh.inflpick.v2.adapter.in.web.common.error.ApiErrorCode;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ApiErrorCodeSwaggerDocs {
    Class<? extends ApiErrorCode>[] values();
    String httpMethod() default "";
    String apiPath() default "";
}
