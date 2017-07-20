package com.lad.admin.controller;

import com.lad.admin.service.IUserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2017
 * Version: 1.0
 * Time:2017/7/11
 */
@Controller("/user")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserSerivce userSerivce;

    @RequestMapping("/userList")
    public String getAllUser(Model model){
        model.addAttribute("users",userSerivce.findAll());
        return "userPage/userList";
    }
}
