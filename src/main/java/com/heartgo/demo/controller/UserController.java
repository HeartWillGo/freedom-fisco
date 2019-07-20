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
    public String loginUser(User user) throws Exception {
        if (isUserParamsEmpty(user)) {
            return JSON.toJSONString(new BackendResult(USER_EMPTY_INFO));
        }
        String inUserId = user.getUserId();
        String inPassWord = user.getPassWord();
        String userInfStr = userService.queryUser(inUserId);
        if (null == userInfStr || userInfStr.isEmpty()) {
            return JSON.toJSONString(new BackendResult(USER_NOT_EXIST));
        }

        String truePwd = JSON.parseObject(userInfStr).getString("passWord");

        //正确性校验
        if (!inPassWord.equals(truePwd)) {
            return JSON.toJSONString(new BackendResult(USER_PASSWORD_INCRRECT));
        }
        return JSON.toJSONString(new BackendResult(OK));
    }

    @GetMapping("queryUser")
    public String queryUser(String userId) throws Exception {
        BackendResult res = new BackendResult();

        String userinfo = userService.queryUser(userId);
        if (null == userinfo || userinfo.isEmpty()) {
            res.setCode(-1);
            res.setMsg("user doesn't exist");

        } else {
            res.setCode(200);
            res.setMsg(userinfo);
        }
        return JSON.toJSONString(res);
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