package com.weini.POJO.server;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class ThreadLocalVar {

    private String userId;

}
