package com.vi.Mockito;

/**
 * @author chen.kangliu
 * @date 2018-06-01
 */
public class PersonService {

    private PersonDao personDao;

    public PersonService(PersonDao personDao_) {
        this.personDao = personDao_;
    }

    public Person testPersionDao(Person persion) {
        System.out.println("hello perison service!");
        return personDao.setPerson(persion);
    }

}
