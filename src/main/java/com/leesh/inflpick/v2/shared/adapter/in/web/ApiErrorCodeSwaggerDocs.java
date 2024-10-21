package com.leesh.inflpick.v2.shared.adapter.in.web;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ApiErrorCodeSwaggerDocs {
    Class<? extends ApiErrorCode>[] values();
    String httpMethod() default "";
    String apiPath() default "";
}
