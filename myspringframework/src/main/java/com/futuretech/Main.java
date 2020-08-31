package com.futuretech;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("dao.xml");
        Dao dao = applicationContext.getBean("accountDao", Dao.class);
        System.out.println("test");
    }
}
