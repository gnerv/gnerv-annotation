package com.gnerv.boot.annotation.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.gnerv.boot.annotation.ResultFilter;
import org.aspectj.lang.JoinPoint;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 注解工具类
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019/8/13
 */
public class AnnotationUtil {

    /**
     * 过滤指定字段
     *
     * @param joinPoint
     * @param o
     * @return
     */
    public static Object doResultFilter(JoinPoint joinPoint, Object o) {
        ResultFilter resultFilter = getResultFilter(joinPoint);
        String[] fields = resultFilter.exclude();
        PropertyFilter profilter = new PropertyFilter() {
            @Override
            public boolean apply(Object object, String name, Object value) {
                for (String field : fields) {
                    if (name.equalsIgnoreCase(field)) {
                        //false表示字段将被排除在外
                        return false;
                    }
                }
                return true;
            }
        };
        String str = JSON.toJSONString(o, profilter);
        return JSON.parse(str);
    }

    /**
     * 获取方法上的注解对象
     *
     * @param joinPoint
     * @return
     */
    public static ResultFilter getResultFilter(JoinPoint joinPoint) {
        Class declaringType = joinPoint.getSignature().getDeclaringType();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Method method = ReflectUtil.createMethod(declaringType, methodName, args);
        ResultFilter resultFilter = method.getAnnotation(ResultFilter.class);
        return resultFilter;
    }


}
