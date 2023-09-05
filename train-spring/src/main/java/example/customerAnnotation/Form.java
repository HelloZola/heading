package example.customerAnnotation;

/**
 *
 * @author zhangwei_david
 * @version $Id: Form.java, v 0.1 2015年5月30日 下午4:04:06 zhangwei_david Exp $
 */
public class Form {
    @DateString
    private String current;

    /**
     * Getter method for property <tt>current</tt>.
     *
     * @return property value of current
     */
    public String getCurrent() {
        return current;
    }

    /**
     * Setter method for property <tt>current</tt>.
     *
     * @param current value to be assigned to property current
     */
    public void setCurrent(String current) {
        this.current = current;
    }

}
