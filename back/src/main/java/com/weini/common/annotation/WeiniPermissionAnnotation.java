package com.weini.common.annotation;

import com.weini.POJO.Do.User;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WeiniPermissionAnnotation {
    String param();
    Class<?> cla() default User.class;
}
