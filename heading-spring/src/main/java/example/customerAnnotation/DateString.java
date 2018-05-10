package example.customerAnnotation;

import java.lang.annotation.*;

/**
 * 自定义 日期类型校验器的注解
 * @author zhangwei_david
 * @version $Id: Date.java, v 0.1 2015年5月29日 下午10:00:20 zhangwei_david Exp $
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@CustomerValidator
public @interface DateString {
    String pattern() default "yyyy-MM-dd HH:mm:ss";

    String errorCode() default "must date";

    String message() default "must be date pattern";
}
