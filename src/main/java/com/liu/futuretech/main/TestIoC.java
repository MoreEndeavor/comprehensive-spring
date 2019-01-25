package com.liu.futuretech.main;

import com.liu.futuretech.configAnnotation.HelloWorld;
import com.liu.futuretech.entity.User;
import com.liu.futuretech.service.UserService;
import com.liu.futuretech.serviceimpl.UserServiceImpl;
import com.liu.futuretech.soundsystem.SgtPeppers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

public class TestIoC {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (User) context.getBean("user");
        UserService userService = (UserServiceImpl)context.getBean("userServiceImpl");
        Test t1 = (Test)context.getBean("test");
        System.out.println(user);
        System.out.println("exits? :" + userService);
        userService.getUser();
        t1.test();
        t1.testh();
    }
}

@Component("test")
class Test {
    @Autowired
    private UserService userService;
    public void test() {
        userService.getUser();
        System.out.println(userService);
    }

    @Autowired
    private HelloWorld helloWorld;
    public void testh() {
        helloWorld.say();
        gtPeppers.play();
    }

    @Autowired
    private SgtPeppers gtPeppers;

}