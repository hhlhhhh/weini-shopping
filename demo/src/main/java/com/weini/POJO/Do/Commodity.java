package com.weini.POJO.Do;

import lombok.Data;
import lombok.experimental.Accessors;

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
