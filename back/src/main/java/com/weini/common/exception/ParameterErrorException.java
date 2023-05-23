package com.weini.common.exception;

public class ParameterErrorException extends RuntimeException {
    private ParameterErrorException(String msg){
        super(msg);
    }

    public static ParameterErrorException Builder(String msg){  //构造方法获取对象
        return new ParameterErrorException(msg);
    }
}
