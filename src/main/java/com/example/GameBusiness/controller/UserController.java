package com.example.GameBusiness.controller;
import com.example.GameBusiness.entity.JsonResult;
import com.example.GameBusiness.entity.User;
import com.example.GameBusiness.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    IUserService service;

    //注册用户信息
    @RequestMapping("/regist")
    public JsonResult<Void> regist(User user){
        service.regist(user);
        return new JsonResult<>(1000,"OK");
    }

//    用户登录
    @RequestMapping("/login")
    public JsonResult<Void> login(String username, String password, HttpServletRequest req){
        User user = service.login(username, password);
        HttpSession session = req.getSession();
        session.setAttribute("user",user);
        return JsonResult.getSuccessJR();
}
}
