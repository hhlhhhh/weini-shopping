package com.weini.common.exception;

//参数缺少异常
public class MissedParameterException extends RuntimeException{
    private MissedParameterException(String msg){
        super(msg);
    }

    public static MissedParameterException Builder(String msg){  //构造方法获取对象
        return new MissedParameterException(msg);
    }
}
