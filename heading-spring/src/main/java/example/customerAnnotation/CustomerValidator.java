package example.customerAnnotation;

import java.lang.annotation.*;

/**
 * 这个注解中没有任何内容，属于标记注解
 * @author zhangwei_david
 * @version $Id: CustomerRule.java, v 0.1 2015年5月29日 下午10:12:16 zhangwei_david Exp $
 */
@Documented
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomerValidator {

}
