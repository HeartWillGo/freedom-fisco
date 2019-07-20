package com.heartgo.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.heartgo.demo.model.CommonResult;
import com.heartgo.demo.model.User;
import com.heartgo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "", produces = "application/json")
public class UserController {


    @Autowired
    private UserService userService;

    /**
     * 注册用户,提供: 1. 账号  2. 密码
     *
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("registUser")
    public String registUser(User user) throws Exception {
        CommonResult res = new CommonResult();
        if (null == user.getUserId() || user.getUserId().isEmpty() || null == user.getPassWord() || user.getPassWord().isEmpty()) {
            res.setCode(-1);
            res.setMsg("userId or passord is empty.");
            return JSON.toJSONString(res);
        }

        userService.registerUser(user);
        res.setCode(200);
        res.setMsg("success");
        return JSON.toJSONString(res);
    }

    @GetMapping("queryUser")
    public String queryUser(String userId) throws Exception {
        CommonResult res = new CommonResult();

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

    @PostMapping("login")
    public String loginUser(User user) throws Exception {
        CommonResult lgRes = new CommonResult();
        // 存在性校验
        if (null == user.getUserId() || user.getUserId().isEmpty() || null == user.getPassWord() || user.getPassWord().isEmpty()) {
            lgRes.setCode(-1);
            lgRes.setMsg("user or password is empty.");
            return JSON.toJSONString(lgRes);
        }
        String inUserId = user.getUserId();
        String inPassWord = user.getPassWord();

        JSONObject userInfo = JSON.parseObject(userService.queryUser(inUserId));
        String truePwd = userInfo.getString("passWord");
        //正确性校验
        if (!inPassWord.equals(truePwd)) {
            lgRes.setCode(-2);
            lgRes.setMsg("user password is incorrect.");
            return JSON.toJSON(lgRes).toString();
        }

        lgRes.setCode(200);
        lgRes.setMsg("login success.");

        return JSON.toJSONString(lgRes);
    }


}