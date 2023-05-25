package com.weini.POJO.Do;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
@Accessors(chain = true)
@Data
public class Commodity {
    public String id;
    public String shopId;

    public String name;
    public String introduce;
    public int price;
    public int inventory;
    public String tag;
}
