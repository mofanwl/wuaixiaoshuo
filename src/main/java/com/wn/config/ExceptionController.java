package com.wn.config;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2019/11/22.
 */
@ControllerAdvice
public class ExceptionController {
    /*
    自定义异常
     */
    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public String Exceptiion(){
        return "unauth";
    }
}
