package com.weini.POJO.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Accessors(chain = true)
@Data
@NoArgsConstructor
public class UserDTO implements Serializable {
    private String id;
    private String nickname;
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
}
