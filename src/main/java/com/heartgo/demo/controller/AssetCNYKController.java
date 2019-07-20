package com.heartgo.demo.controller;

import com.alibaba.fastjson.JSON;
import com.heartgo.demo.model.CommonResult;
import com.heartgo.demo.service.AssetCNYKService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "", produces = "application/json")
public class AssetCNYKController {

    @Autowired
    private AssetCNYKService assetCNYKService;

//    @Autowired
//
//    /**
//     * 查询余额
//     */
//    @GetMapping("queryBanlance")
//    public String queryBanlance(String userId) {
//        CommonResult res = new CommonResult();
//        if (null == userId || userId.isEmpty()) {
//            res.setCode(-1);
//            res.setMsg("user is is empty.");
//            return JSON.toJSONString(res);
//        }
//
//    }
}
