package com.weini.common.exception;

public class NotPermissionException extends RuntimeException{
    private NotPermissionException(String msg){
        super(msg);
    }

    public static NotPermissionException Builder(String msg){  //构造方法获取对象
        return new NotPermissionException(msg);
    }
}
