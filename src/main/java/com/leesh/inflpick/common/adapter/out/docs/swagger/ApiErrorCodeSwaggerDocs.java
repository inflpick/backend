package com.leesh.inflpick.common.adapter.out.docs.swagger;

import com.leesh.inflpick.common.domain.ErrorCode;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ApiErrorCodeSwaggerDocs {
    Class<? extends ErrorCode>[] values();
    String httpMethod() default "";
    String apiPath() default "";
}
