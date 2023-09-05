package example.customerAnnotation;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangwei_david
 * @version $Id: CustomerValidatorProcesser.java, v 0.1 2015年5月30日 下午12:38:33 zhangwei_david Exp $
 */
public class CustomerValidatorConfig implements ApplicationContextAware {

    private Map<Annotation, CustomerValidatorRule> rules = new ConcurrentHashMap<Annotation, CustomerValidatorRule>();

    Map<String, Object> customerValidationRules = null;

    /**
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        customerValidationRules = applicationContext
                .getBeansWithAnnotation(CustomerRule.class);
        System.out.println(customerValidationRules);
    }

    private CustomerValidatorRule findFormMap(Annotation annotation) {
        for (Map.Entry<String, Object> entry : customerValidationRules.entrySet()) {
            if (entry.getValue() != null
                    && ((CustomerValidatorRule) entry.getValue()).support(annotation)) {
                return (CustomerValidatorRule) entry.getValue();
            }
        }
        return null;
    }

    public CustomerValidatorRule findRule(Annotation annotation) {
        CustomerValidatorRule customerValidatorRule = null;
        if (!rules.containsKey(annotation)) {
            CustomerValidatorRule cvr = findFormMap(annotation);
            if (cvr != null) {
                rules.put(annotation, cvr);
            }
            customerValidatorRule = cvr;
        }
        customerValidatorRule = rules.get(annotation);
        return customerValidatorRule;
    }
}
