package com.leesh.inflpick.common.adapter.in.web.swagger;

import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiErrorCodeSwaggerDocs {
    Class<? extends ApiErrorCode>[] values();
    String httpMethod() default "";
    String apiPath() default "";
}
