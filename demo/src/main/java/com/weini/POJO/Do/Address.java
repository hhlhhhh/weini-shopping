package com.weini.POJO.Do;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Builder
@Accessors(chain = true)
@Data
public class Address {
    private String id;
    private String user_id;
    private String province;
    private String city;
    private String town;
    private String details;
    private String state;
    @TableField(fill = FieldFill.INSERT)
    private Date create_time;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date update_time;
    private String del_time;
    private String del_flag;
}
