package com.weini.POJO.Do;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class SccChoice {

    private String id;

    private String sc_commodity_id;

    private String type_id;

    private String choice_id;

    @TableField(fill = FieldFill.INSERT)
    private Date create_time;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date update_time;

    @TableField(exist = false)
    private Type type;

    @TableField(exist = false)
    private TypeChoice typeChoice;

    private String del_flag;

}
