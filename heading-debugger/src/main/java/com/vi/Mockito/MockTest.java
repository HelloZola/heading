package com.vi.Mockito;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author chen.kangliu
 * @date 2018-06-01
 */
public class MockTest {

    @Test
    public void test1() {
        //初始化内容
        PersonDao mockDao = mock(PersonDao.class);
        Person person = new Person();
        person.setAge(12);
        person.setName("chenkangliu");
        //设置模拟调用setPersion方法
        PersonService service = new PersonService(mockDao);
        //设置模拟返回条件
        when(mockDao.setPerson(person)).thenReturn(new Person("aida", 12));
        //开始调用
        Person ress = service.testPersionDao(person);
        //验证是否调了一次mockDao的setPerson方法
        verify(mockDao, times(1)).setPerson(isA(Person.class));
        assertEquals(new Person("aida", 12).toString(), ress.toString());
    }
}
