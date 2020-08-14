package com.hitman_zhang.datasource.common.datasource.annotation;


import java.lang.annotation.*;

/**
 * @description 自定义数据源切换注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {

    String name() default "";

}
