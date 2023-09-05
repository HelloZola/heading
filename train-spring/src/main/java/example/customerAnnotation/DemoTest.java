package example.customerAnnotation;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.Validator;

/**
 * @author zhangwei_david
 * @version $Id: MyTest.java, v 0.1 2014年12月31日 下午9:25:49 zhangwei_david Exp $
 */
public class DemoTest {

    @Autowired
    private Validator customerValidatorFactory;

    @Test
    public void helloTest() {
        Form form = new Form();
        form.setCurrent("2015 11 11");
        BindException errors = new BindException(form, "target");
        customerValidatorFactory.validate(form, errors);
        System.out.println(errors.getFieldErrors());
    }

}
