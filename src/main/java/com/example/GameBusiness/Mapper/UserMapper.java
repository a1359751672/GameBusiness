package com.example.GameBusiness.Mapper;

import com.example.GameBusiness.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
//添加用户信息
    Integer insertUser(User user);
    //    根据用户名查询用户信息
    User getByName(String username);
}
