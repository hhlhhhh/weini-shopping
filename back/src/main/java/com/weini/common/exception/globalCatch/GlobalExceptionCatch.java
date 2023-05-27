package com.weini.common.exception.globalCatch;

import com.weini.common.exception.MissedParameterException;
import com.weini.common.response.Result;
import com.weini.common.response.State;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionCatch {

    @ExceptionHandler(value = MissedParameterException.class)
    public Result missedParameterExceptionHandler(MissedParameterException e){
        return Result.fail(State.ERR,e.getMessage());
    }

}
