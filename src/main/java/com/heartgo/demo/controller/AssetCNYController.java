package com.heartgo.demo.controller;

import com.alibaba.fastjson.JSON;
import com.heartgo.demo.model.CommonResult;
import com.heartgo.demo.service.AssetCNYService;
import com.heartgo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "", produces = "application/json")
public class AssetCNYController {

    @Autowired
    UserService userService;

    @Autowired
    AssetCNYService assetCNYService;

    /**
     * 人民币充值到freedom
     *
     */
    @PostMapping("saveCNY")
    public String save(CNYSaveInfo cnySaveInfo) {
        String userId = cnySaveInfo.getUserId();
        String userinfoStr = userService.queryUser(userId);
        CommonResult commonResult = new CommonResult();

        //1. 校验 userId
        if (null == userinfoStr || userinfoStr.isEmpty()) {
            commonResult.setCode(-1);
            commonResult.setMsg("invalid userId.");
            return JSON.toJSONString(commonResult);
        }
        // 2. 银行转账:个人账户到 freedom 账户
        commonResult.setCode(200);
        commonResult.setMsg("success");
        return JSON.toJSONString(commonResult);
    }





    class CNYSaveInfo{
        public String userId;
        public Long amount;

        public CNYSaveInfo(String userId, Long amount) {
            this.userId = userId;
            this.amount = amount;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Long getAmount() {
            return amount;
        }

        public void setAmount(Long amount) {
            this.amount = amount;
        }
    }
}
