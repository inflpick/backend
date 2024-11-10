package com.leesh.inflpick.v2.review.adapter.in.web.exception;

import com.leesh.inflpick.v2.review.adapter.in.web.controller.CreateReviewController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order(0)
@RestControllerAdvice(basePackageClasses = CreateReviewController.class)
public class CreateReviewExceptionHandler {

}
