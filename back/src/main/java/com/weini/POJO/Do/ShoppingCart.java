package com.weini.POJO.Do;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Accessors(chain = true)
@Data
public class ShoppingCart {

    private String id;

    private String user_id;

    private Integer count;   //商品种类

    @TableField(fill = FieldFill.INSERT)
    private Date create_time;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date update_time;

    @TableField(exist = false)
    private List<ShoppingCartCommodity> commodities;
}
