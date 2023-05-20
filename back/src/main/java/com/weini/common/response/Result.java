package com.weini.common.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据返回值封装
 */
@Data
public class Result implements Serializable {

    private  int code;  //200为正常
    private String msg;
    private Object data;

    public static Result succ(Object data){
        return succ(200,"操作成功",data);
    }

    public static Result succ(int code,String msg ,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result fail(String msg) {
        Result m = new Result();
        m.setCode(400);
        m.setData(null);
        m.setMsg(msg);
        return m;
    }
    public static Result fail(int code,String msg, Object data) {
        Result m = new Result();
        m.setCode(code);
        m.setData(data);
        m.setMsg(msg);
        return m;
    }

}
