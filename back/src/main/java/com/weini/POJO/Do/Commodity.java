package com.weini.POJO.Do;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;


@Accessors(chain = true)
@Data
public class Commodity {    //商品实体类

    public String id;

    public String shop_id;

    public String name;

    public String introduce;

    public int price;

    public int inventory;

    public String tag;      //最多添加三个标签，中文"，"分隔

    @TableField(exist = false)
    public List<CommodityMedia> mediaUrlList;

    @TableField(exist = false)
    public List<Type> typeList;

    @TableField(fill = FieldFill.INSERT)
    private Date create_time;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date update_time;

    private String del_flag;
}
