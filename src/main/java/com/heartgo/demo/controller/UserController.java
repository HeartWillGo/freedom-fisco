package com.heartgo.demo.controller;


import com.heartgo.demo.client.AssetClient;
import com.heartgo.demo.client.UserInfoClient;
import com.heartgo.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.HashMap;


@RestController
@RequestMapping(value = "", produces = "application/json")
public class UserController {

    @Autowired
    private UserInfoClient userInfoClient;
    @GetMapping("registUser")
    public String registUser() throws Exception{
        User user=new User();
        user.setIdCard("344222667");
        user.setPassWord("9999");
        user.setUserPhone("134266");
        userInfoClient.registerUser("heartgo",user);
        String res="success";
//        ResponseUtils.packageResponse(logId, ChallengeRespCodeEnum.SUCCESS, resData);
        return "@json:" ;

    }

    @GetMapping("queryUser")
    public String queryUser() throws Exception{
        return  userInfoClient.queryUserInfo("heartgo");

    }



}