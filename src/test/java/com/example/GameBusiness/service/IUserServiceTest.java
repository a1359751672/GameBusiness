package com.example.GameBusiness.service;

import com.example.GameBusiness.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IUserServiceTest {
    @Autowired
    IUserService service;

    @Test
    void regist() {
        User user = new User();
        user.setUsername("2582532130");
        user.setPassword("2582532130");
        service.regist(user);
        System.out.println("添加用户数据成功！");
    }

    @Test
    void login() {
        service.login("2582532130", "2582532130");
        System.out.println("登录成功");
    }
}