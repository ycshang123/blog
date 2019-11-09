package com.scs.web.blog.service;

import com.scs.web.blog.domain.UserDto;
import com.scs.web.blog.factory.ServiceFactory;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class UserServiceTest {
    private  UserService userService = ServiceFactory.getUserServiceInstance();
    @Test
    public void signIn() {
        UserDto userDto = new UserDto("13923554302","bfa010ab6c01e42b2e86aa0e26f21d4f");
        Map<String,Object> map = userService.signIn(userDto);
        System.out.println(map);
    }
}