package com.weini.utils;

import com.weini.POJO.server.ThreadLocalVar;

public class MyThreadLocal {
    private static final ThreadLocal<ThreadLocalVar> var=new ThreadLocal<>();

    public static ThreadLocalVar getVar(){
        ThreadLocalVar threadLocalVar = var.get();
        return threadLocalVar==null?new ThreadLocalVar():threadLocalVar;
    }

    public static void setVar(ThreadLocalVar val){
        var.set(val);
    }
}
