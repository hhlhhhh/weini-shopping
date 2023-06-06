package com.weini.POJO.Do;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

//商品的各种种类
@Data
@Accessors(chain = true)
public class Type {

    private String id;

    private String commodity_id;

    private String name;

    @TableField(fill = FieldFill.INSERT)
    private Date create_time;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date update_time;

    private String del_flag;

    @TableField(exist = false)
    private List<TypeChoice> typeChoiceList;

}
