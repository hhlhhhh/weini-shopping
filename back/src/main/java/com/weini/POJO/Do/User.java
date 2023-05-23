package com.weini.POJO.Do;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
    private Integer balance = null;
    private String sex;
    private String phone;
    private String email;
    private String avatar;
    private String signature;
    private String role;

    @TableField(fill = FieldFill.INSERT)
    private Date create_time;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date update_time;

    private Date del_time;
    private String del_flag;
}
