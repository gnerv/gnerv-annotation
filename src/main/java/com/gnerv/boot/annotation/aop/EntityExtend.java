package com.gnerv.boot.annotation.aop;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ligen
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EntityExtend {

    // 被注入的对象
    Class clazz();

    // 对应的接口方法 完整地址
    String method();

    // 对应被注入对象的属性名称 一般为关联主键属性 如 b_id
    String property();

    String column();

    // 返回参数类型
    Class type();
}
