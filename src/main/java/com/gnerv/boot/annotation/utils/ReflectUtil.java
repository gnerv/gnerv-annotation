package com.gnerv.boot.annotation.utils;

import java.lang.reflect.Method;

/**
 * <p>
 * 反射工具
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019/8/13
 */
public class ReflectUtil {

    public static Method createMethod(Class c, String methodName, Object[] args) {
        try {
            Class[] clazz = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                clazz[i] = args[i].getClass();
            }
            Method m = c.getMethod(methodName, clazz);
            return m;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }


}
