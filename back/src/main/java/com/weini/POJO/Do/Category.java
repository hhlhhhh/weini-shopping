package com.weini.POJO.Do;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
@Accessors(chain = true)
@Data
public class Category {
    public String id;
    public String name;
    public Date createTime;
    public Date updateTime;
    @TableField(fill = FieldFill.INSERT)
    private Date create_time;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date update_time;
    public Date delTime;
    public String delFlag;


}
