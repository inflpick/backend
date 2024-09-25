package com.leesh.inflpick.common.adapter.in.web;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class ControllerConfig {

    /**
     * Controller 에서 @RequestParam 으로 받는 String[] 타입의 값이 "created,asc" 와 같은 경우 배열로 분리하지 않고 그대로 받기 위한 설정
     * @param webDataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(
                String[].class,
                new StringArrayPropertyEditor("\\|"));
    }

}
