package com.weini.POJO.Do;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Accessors(chain = true)
@Data
public class User implements Serializable {
    private String id;
    private String nickname;
    private String password;
    private int balance;
    private String sex;
    private String phone;
    private String email;
    private String avatar;
    private String signature;
    private String role;
    private Date create_time;
    private Date update_time;
    private Date del_time;
    private String del_flag;
}
