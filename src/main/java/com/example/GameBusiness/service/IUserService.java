package com.example.GameBusiness.service;

import com.example.GameBusiness.entity.User;

public interface IUserService {
//    用户注册
    void regist(User user);

//    用户登录
    User login(String username,String password);

}
