package com.heartgo.demo.service;

import com.heartgo.demo.client.UserInfoClient;
import com.heartgo.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    @Autowired
    private UserInfoClient userInfoClient;


    public void registerUser(User user)
    {
        user.setUserPhone(user.getUserId());
        userInfoClient.registerUser(user.getUserId(), user);

    }

    public String queryUser(String userId) {
        return userInfoClient.queryUserInfo(userId);
    }
}
