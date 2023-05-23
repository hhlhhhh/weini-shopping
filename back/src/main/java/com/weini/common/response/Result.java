package com.weini.common.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据返回值封装
 */
@Data
public class Result implements Serializable {
    //200正常
    //400请求错误
    //401未认证
    //403权限不够
    //452处理过程失败
    //453操作失败

    private  int code;  //200为正常
    private String msg;
    private Object data;

    public static Result succ(Object data){
        return succ(State.SUCCESS,"操作成功",data);
    }

    public static Result succ(State code,String msg ,Object data){
        Result result = new Result();
        result.setCode(code.getCode());
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result fail(String msg) {
        Result m = new Result();
        m.setCode(State.ERR.getCode());
        m.setData(null);
        m.setMsg(msg);
        return m;
    }

    public static Result fail(State code,String msg){
     Result m = new Result();
     m.setCode(code.getCode());
     m.setMsg(msg);
     return m;
    }

    public static Result fail(State code,String msg, Object data) {
        Result m = new Result();
        m.setCode(code.getCode());
        m.setData(data);
        m.setMsg(msg);
        return m;
    }

}
