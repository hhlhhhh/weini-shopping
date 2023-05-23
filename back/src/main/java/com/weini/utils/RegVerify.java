package com.weini.utils;

import java.util.Objects;
import java.util.regex.Pattern;

public class RegVerify {

    public static boolean verifyEmail(String email){
        return !Objects.isNull(email)&&Pattern.matches("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$",email);  //匹配邮箱
    }
    public static boolean verifyPhone(String phone){
        return !Objects.isNull(phone)&&Pattern.matches("^(13\\d|14[579]|15[012356789]|166|17[0135678]|18\\d|19[89])\\d{8}$",phone); //匹配大陆手机号
    }
    public static boolean verifyNickname(String nickname){
        return !Objects.isNull(nickname)&&Pattern.matches("^\\S{6,30}$",nickname);  //匹配一个6到30位的非空字符串
    }
    public static boolean verifyPassword(String password){
        return !Objects.isNull(password)&&Pattern.matches("^(?=.*[0-9])(?=.*[a-zA-Z])[0-9a-zA-Z]{6,16}$",password); //匹配一个6到16位的由数字和英文字母组成的字符串
    }
}
