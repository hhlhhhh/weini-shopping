package com.weini.POJO.Do;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@Data
public class ShoppingCartCommodity {

    private String id;

    private String shopping_cart_id;

    private String commodity_id;

    private Integer count = null;

    @TableField(fill = FieldFill.INSERT)
    private Date create_time;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date update_time;

    private String del_flag;

    @TableField(exist = false)
    private Commodity commodity;
}
