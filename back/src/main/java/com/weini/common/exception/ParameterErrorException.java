package com.weini.common.exception;

//参数错误异常
public class ParameterErrorException extends RuntimeException {
    private ParameterErrorException(String msg){
        super(msg);
    }

    public static ParameterErrorException Builder(String msg){  //构造方法获取对象
        return new ParameterErrorException(msg);
    }
}
