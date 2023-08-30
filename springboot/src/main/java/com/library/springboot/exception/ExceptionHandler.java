package com.library.springboot.exception;

import com.library.springboot.common.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = ServiceException.class)
    public Result serviceExceptionError(ServiceException e){
        String code = e.getCode();
        if(!StringUtils.isEmpty(code)){
            return Result.error(code, e.getMessage());
        }
        return Result.error(e.getMessage());
    }

}
