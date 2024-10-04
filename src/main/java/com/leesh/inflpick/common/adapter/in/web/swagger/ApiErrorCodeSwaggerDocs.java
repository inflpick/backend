package com.leesh.inflpick.common.adapter.in.web.swagger;

import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorCode;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ApiErrorCodeSwaggerDocs {
    Class<? extends ApiErrorCode>[] values();
    String httpMethod() default "";
    String apiPath() default "";
}
