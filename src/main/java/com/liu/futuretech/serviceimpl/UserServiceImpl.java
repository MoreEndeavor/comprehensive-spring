package com.liu.futuretech.serviceimpl;

import com.liu.futuretech.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public void getUser() {
        System.out.println("one user");
    }
}
