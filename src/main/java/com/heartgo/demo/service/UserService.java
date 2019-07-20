package com.heartgo.demo.service;

import com.heartgo.demo.client.UserInfoClient;
import com.heartgo.demo.model.RESULT;
import com.heartgo.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    @Autowired
    private UserInfoClient userInfoClient;


    public RESULT registerUser(User user) {
        return userInfoClient.registerUser(user);
    }

    public String queryUser(String userId) {
        return userInfoClient.queryUserInfo(userId);
    }
}
