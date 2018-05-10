package example.customerAnnotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author zhangwei_david
 * @version $Id: CustomerValidatorFactory.java, v 0.1 2015年5月30日 下午1:03:56 zhangwei_david Exp $
 */
@Component
public class CustomerValidatorFactory implements Validator {

    @Autowired
    private CustomerValidatorConfig customerValidatorConfig;

    /**
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    public void validate(Object target, Errors errors) {
        Assert.notNull(target);
        Assert.notNull(errors);
        List<Field> fileds = getFields(target.getClass());
        for (Field field : fileds) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().getAnnotation(CustomerValidator.class) != null) {
                    try {
                        CustomerValidatorRule customerValidatorRule = customerValidatorConfig
                                .findRule(annotation);
                        if (customerValidatorRule != null) {
                            customerValidatorRule.valid(annotation, target, field, errors);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    /**
     * 获取class的fields。
     *
     * @param clazz bean所在的class
     * @return
     */
    private List<Field> getFields(Class<? extends Object> clazz) {
        // 声明Field数组
        List<Field> fields = new ArrayList<Field>();

        // 如果class类型不为空
        while (clazz != null) {
            // 添加属性到属性数组
            Collections.addAll(fields, clazz.getDeclaredFields());
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

}
