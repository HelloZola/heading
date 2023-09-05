package example.customerAnnotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 *
 * @author zhangwei_david
 * @version $Id: CustomerValidatorRule.java, v 0.1 2015年5月30日 下午12:51:20 zhangwei_david Exp $
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface CustomerRule {

}
