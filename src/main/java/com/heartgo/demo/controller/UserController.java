package com.heartgo.demo.controller;


import com.alibaba.fastjson.JSON;
import com.heartgo.demo.client.AssetClient;
import com.heartgo.demo.client.UserInfoClient;
import com.heartgo.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.HashMap;


@RestController
@RequestMapping(value = "", produces = "application/json")
public class UserController {


    @Autowired
    private UserInfoClient userInfoClient;

    /**
     * 注册用户,提供: 1. 账号  2. 密码
     *
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("registUser")
    public String registUser(User user) throws Exception {
        if (null == user.getUserId() || user.getUserId().isEmpty() || null == user.getPassWord() || user.getPassWord().isEmpty())
            return "failed";
        userInfoClient.registerUser(user.getUserId(), user);
        return "success";

    }

    @GetMapping("queryUser")
    public String queryUser(String userId) throws Exception {
        return userInfoClient.queryUserInfo(userId);
    }

    @PostMapping("login")
    public String loginUser(User user) throws Exception {
        if (null == user.getUserId() || user.getUserId().isEmpty() || null == user.getPassWord() || user.getPassWord().isEmpty()) {
            LoginResult lgRes = new LoginResult();
            lgRes.setCode(-1);
            lgRes.setMsg("user or password is empty.");
            return JSON.toJSON(lgRes).toString();
        }
        String userId = user.getUserId();
        String passWord = user.getPassWord();



        return "success";

    }

    class LoginResult {
        private int Code;
        private String msg;

        public int getCode() {
            return Code;
        }

        public void setCode(int code) {
            Code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }


}