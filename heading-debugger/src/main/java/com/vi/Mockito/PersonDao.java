package com.vi.Mockito;

/**
 * @author chen.kangliu
 * @date 2018-06-01
 */
public class PersonDao {

    public Person setPerson(Person person) {
        System.out.println("hello persion");
        System.out.println(person);
        return person;
    }
}
