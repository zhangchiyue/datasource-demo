package com.hitman_zhang.datasource.common.datasource.annotation;

import java.lang.annotation.*;

/**
 * @description 事务注解
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TransactionSource {
}