package com.weini.common.exception;

public class ExecutionFailureException extends RuntimeException {
    private ExecutionFailureException(String msg){
        super(msg);
    }

    public static ExecutionFailureException Builder(String msg){  //构造方法获取对象
        return new ExecutionFailureException(msg);
    }
}
