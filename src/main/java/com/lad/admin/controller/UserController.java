package com.lad.admin.controller;

import com.lad.admin.service.IUserSerivce;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2017
 * Version: 1.0
 * Time:2017/7/11
 */
@Api(value = "UserController", description = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserSerivce userSerivce;

    @GetMapping("/userList")
    public String getAllUser(Model model){
        model.addAttribute("users",userSerivce.findAll());
        return "userPage/userList";
    }
}
