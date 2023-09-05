package example.customerAnnotation;

import org.springframework.validation.Errors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 自定义注解处理器接口
 *
 * @author zhangwei_david
 * @version $Id: CustomerValidatorRule.java, v 0.1 2015年5月30日 上午8:51:52 zhangwei_david Exp $
 */
public interface CustomerValidatorRule {

    /**
     * 判断是否支持该注解
     *
     * @param annotation
     * @return
     */
    public boolean support(Annotation annotation);

    /**
     *  校验处理
     *
     *
     * @param annotation
     * @param field
     * @param errors
     */
    public void valid(Annotation annotation, Object object, Field field, Errors errors)
            throws Exception;
}
