package com.weini.shiro;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountProfile implements Serializable {
    private String id;
    private String nickname;
    private String signature;
    private String authority;
    private String avatar;
}
