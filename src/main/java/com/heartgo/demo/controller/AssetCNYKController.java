package com.heartgo.demo.controller;

import com.heartgo.demo.service.AssetCNYKService;
import org.springframework.beans.factory.annotation.Autowired;
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
//        BackendResult res = new BackendResult();
//        if (null == userId || userId.isEmpty()) {
//            res.setCode(-1);
//            res.setMsg("user is is empty.");
//            return JSON.toJSONString(res);
//        }
//
//    }
}
