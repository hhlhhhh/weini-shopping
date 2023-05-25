package com.weini.POJO.Do;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
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
    private String crate_time;
    private String update_time;
    private String del_time;
    private String del_flag;
}
