package com.heartgo.demo.controller;


import com.alibaba.fastjson.JSON;
import com.heartgo.demo.model.BackendResult;
import com.heartgo.demo.model.RESULT;
import com.heartgo.demo.model.User;
import com.heartgo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.heartgo.demo.model.RESULT.*;


@RestController
@RequestMapping(value = "", produces = "application/json")
public class UserController {


    @Autowired
    private UserService userService;


    /**
     * 用户注册
     *
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("registUser")
    public String registUser(User user) throws Exception {
        // 参数校验
        if (isUserParamsEmpty(user)) {
            return JSON.toJSONString(new BackendResult(USER_EMPTY_INFO));
        }

        RESULT res = userService.registerUser(user);


        return JSON.toJSONString(new BackendResult(res));
    }

    @PostMapping("login")
    public String loginUser(User inUser) throws Exception {
        if (isUserParamsEmpty(inUser)) {
            return JSON.toJSONString(new BackendResult(USER_EMPTY_INFO));
        }
        String inUserId = inUser.getUserId();
        if (!userService.isUserExits(inUser.getUserId())) {
            return JSON.toJSONString(new BackendResult(USER_NOT_EXIST));
        }
        User trueUser = userService.queryUserById(inUser.getUserId());

        //正确性校验
        if (!inUser.getPassWord().equals(trueUser.getPassWord())) {
            return JSON.toJSONString(new BackendResult(USER_PASSWORD_INCRRECT));
        }
        return JSON.toJSONString(new BackendResult(OK));
    }

    @GetMapping("queryUser")
    public String queryUser(String userId) throws Exception {
        BackendResult res = new BackendResult();
        User user = userService.queryUserById(userId);
        return JSON.toJSONString(user);
    }


    /**
     * 参数校验
     *
     * @param user
     * @return
     */
    private boolean isUserParamsEmpty(User user) {
        return (null == user.getUserId() ||
                user.getUserId().isEmpty() ||
                null == user.getPassWord() ||
                user.getPassWord().isEmpty());
    }
}