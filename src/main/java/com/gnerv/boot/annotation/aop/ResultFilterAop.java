package com.gnerv.boot.annotation.aop;

import com.gnerv.boot.annotation.utils.AnnotationUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 返回参数过滤器aop
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019/8/13
 */

@Aspect
@Component
public class ResultFilterAop {

    @Pointcut("@annotation(com.gnerv.boot.annotation.ResultFilter)")
    public void result() {
    }

    @AfterReturning(returning = "object", pointcut = "result()")
    public void doReturnList(JoinPoint joinPoint, Object object) {
        // 基本数据类型默认不处理
        if (object instanceof Boolean) {
            return;
        }
        if (object instanceof Byte) {
            return;
        }
        if (object instanceof Short) {
            return;
        }
        if (object instanceof Integer) {
            return;
        }
        if (object instanceof Long) {
            return;
        }
        if (object instanceof Float) {
            return;
        }
        if (object instanceof Double) {
            return;
        }
        if (object instanceof Character) {
            return;
        }
        if (object instanceof String) {
            return;
        }

        if (object instanceof List) {
            List list = (List) object;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Object o = list.get(i);
                o = AnnotationUtil.doResultFilter(joinPoint, o);
                list.set(i, o);
            }
            return;
        }

        if (object instanceof Map) {
            Map map = (Map) object;
            for (Object o : map.keySet()) {
                Object obj = map.get(o);
                obj = AnnotationUtil.doResultFilter(joinPoint, obj);
                map.put(o, obj);
            }
            return;
        }

        AnnotationUtil.doResultFilter(joinPoint, object);
        return;
    }

}
