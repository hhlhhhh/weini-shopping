package com.weini.common.response;

public enum State {
    SUCCESS(200),ERR(400),UNAUTHORIZED(401),LACKOFAUTHORITY(403),ERRORPROCESS(452),ERROROPERATE(453);
    private final Integer code;

    State(Integer code){
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
