package com.heartgo.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.heartgo.demo.client.AssetClient;
import org.aopalliance.intercept.Invocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;


@Controller
@RequestMapping(value = "", produces = "application/json")
public class AdminActionController {

    @Autowired
    private AssetClient assetClient;
    @GetMapping("deploy")
    public String deploy() throws Exception{
        assetClient.initialize();
        assetClient.deployAssetAndRecordAddr();
        String res="success";
//        ResponseUtils.packageResponse(logId, ChallengeRespCodeEnum.SUCCESS, resData);
        return "@json:" ;

    }

    @GetMapping("regist")
    public String regist() throws Exception{
        assetClient.registerAssetAccount("Alice3", new BigInteger("2322"));
        assetClient.queryAssetAmount("Alice3");
        return "success";

    }

    @GetMapping("query")
    public String query() throws Exception{
        assetClient.registerAssetAccount("Alice3", new BigInteger("2322"));
        assetClient.queryAssetAmount("Alice3");
        return "success";

    }

    @GetMapping("")
    public String helloWorld(HashMap<String, Object> map) {
        map.put("hello", "欢迎进入HTML页面");
        return "/index";
    }
    @GetMapping("center")
    public String mycenter(HashMap<String, Object> map) {
//        map.put("hello", "欢迎进入HTML页面");
        return "/myCenter";
    }


}