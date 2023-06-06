package com.weini.utils;

import java.security.SecureRandom;

public class RandomStringGenerator {
    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int LENGTH = 36;

    private static final SecureRandom RANDOM = new SecureRandom();

    public static synchronized char[] generate() {      //随机生成一个36位数的字符数组
        char[] strArr = new char[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            strArr[i] = CHARACTERS.charAt(randomIndex);
        }
        return strArr;
    }
    public static synchronized void add(char[] str){        //随机改变一个位置的字符
        int randomIndex = RANDOM.nextInt(CHARACTERS.length());
        int randomChangeIndex = RANDOM.nextInt(LENGTH);
        str[randomChangeIndex] = CHARACTERS.charAt(randomIndex);
    }
}
