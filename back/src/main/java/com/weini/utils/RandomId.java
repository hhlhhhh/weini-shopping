package com.weini.utils;

import java.util.Random;

public class RandomId {
    public static int idConstruct(){
        Random random=new Random();
        int randomId=random.nextInt(900000000)+100000000;
        return randomId;
    }

    public static int verifyCode(){
        Random random=new Random();
        return random.nextInt(9000)+1000;
    }
}
