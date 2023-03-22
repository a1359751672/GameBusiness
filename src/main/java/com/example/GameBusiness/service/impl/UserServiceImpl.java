package com.example.GameBusiness.service.impl;

import com.example.GameBusiness.Mapper.UserMapper;
import com.example.GameBusiness.common.Constant;
import com.example.GameBusiness.entity.User;
import com.example.GameBusiness.service.IUserService;
import com.example.GameBusiness.service.ex.InsertException;
import com.example.GameBusiness.service.ex.LoginException;
import com.example.GameBusiness.service.ex.UpdateException;
import com.example.GameBusiness.service.ex.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper mapper;
//    注册功能
    @Override
    public void regist(User user) {
        //        判断user是否为空
        if (user == null){
            throw new UpdateException("用户异常：用户信息为空");
        }
//        判断用户名或者密码为空
        if (StringUtils.isEmpty(user.getUsername())){
            throw new UpdateException("注册异常：用户名为空");
        }
        if (StringUtils.isEmpty(user.getPassword())){
            throw new UpdateException("注册异常：密码为空");
        }
//        判断用户名是否存在
        User u = null;
        try{
            u = mapper.getByName(user.getUsername());
        }catch (Exception e){
            e.printStackTrace();
            throw new InsertException("注册异常:查询用户名异常",e);
        }
        if(u!=null){
//            throw new UserExistException("注册异常用户名已经存在");
            throw new UserExistException("注册异常：用户名已存在");
        }

//        对密码加密
//        生成盐值
        String salt = UUID.randomUUID().toString();
        String md5Password = getMD5Password(user.getPassword(), salt, Constant.MD5_HASH_TIME);
        user.setPassword(md5Password); //设置user对象加密后的密码
        user.setSalt(salt); //设置user对象的盐值
        user.setGender(Constant.USER_GANDER_UNKNOWN); //设置user对象的性别
        user.setIsDelete(Constant.IS_NOT_DELETE); //设置user对象是否被删除
        user.setCreatedUser(user.getUsername()); //设置user对象的创建者
        user.setModifiedUser(user.getUsername()); //设置user对象的最后修改者

//        调用持久层方法，添加用户数据
        int row=0;
        try {
            row = mapper.insertUser(user);
        }catch (Exception e){
            e.printStackTrace();
            throw new InsertException("注册异常：添加用户信息异常",e);
        }
        if (row !=1){
            throw new InsertException("注册异常：添加用户信息失败");
        }
    }

//    用户登录
    @Override
    public User login(String username, String password) {
        //        参数验证
        if (StringUtils.isEmpty(username)){
            throw new UpdateException("登录异常：用户名为空");
        }
        if (StringUtils.isEmpty(password)){
            throw new UpdateException("登陆异常：密码为空");
        }
//        验证用户是否存在
        User u = null;
        try {
            u = mapper.getByName(username);
        }catch (Exception e){
            e.printStackTrace();
            throw new InsertException("登录异常：用户名查询失败"+e.getMessage(),e);
        }
        if (u==null){
            throw new InsertException("登录异常：用户名不存在");
        }
        System.out.println(u);
//        获取查询到的用户盐值
        String salt = u.getSalt();
//        对输入的密码进行加密
        String md5Password = getMD5Password(password, salt, Constant.MD5_HASH_TIME);
//        比对密码
        if (!md5Password.equals(u.getPassword())){
            throw new LoginException("登录异常：密码错误");
        }
//        判断用户是否被删除
        System.out.println(u.getIsDelete());
        if (u.getIsDelete().equals(Constant.IS_DELETE)){
            throw new LoginException("登录异常：用户已被删除");
        }
        System.out.println("业务层用户登录成功！");
        return u;
    }


    //    对密码进行加密
    /*
     * @param password原密码（明文）
     * @param salt盐值(UUID生成的唯一值)
     * @param time--hash迭代的次数
     * */
    public String getMD5Password(String password,String salt,int time){
//        组合明文和盐值
        password = password+salt;
//        进行哈希迭代
        for (int i = 1;i<=time;i++){
            password = DigestUtils.md5DigestAsHex(password.getBytes());
        }
        return password;
    }
}
