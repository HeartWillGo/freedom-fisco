package com.heartgo.demo.controller;


import com.alibaba.fastjson.JSON;
import com.heartgo.demo.model.BackendResult;
import com.heartgo.demo.model.Transation;
import com.heartgo.demo.service.AssetCNYKService;
import com.heartgo.demo.service.AssetCNYService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "", produces = "application/json")
public class TransController {

    @Autowired
    private AssetCNYService assetCNYService;
    @Autowired
    private AssetCNYKService assetCNYKService;


    /**
     * 人民币兑 FD
     * <p>
     * 验证:
     * 1. 用户存在
     * 2. 自己账户有效
     * 3. 余额充足
     * 4. fd账户有效
     * <p>
     * 实施:
     * 1. 人民币账户减少金额
     * 2. 托管账户增加金额
     * 3. FD 账户增加金额
     *
     * @param tInfo
     * @return
     */
    @PostMapping("cny2cnyk")
    public String Cny2Cnyk(Transation tInfo) {
        BackendResult res = new BackendResult();

        if (null == tInfo.getFromId() ||
                tInfo.getFromId().isEmpty() ||
                null == tInfo.getToId() ||
                tInfo.getToId().isEmpty() ||
                null == tInfo.getAmount()
        ) {
            res.setCode(-1);
            res.setMsg("some paramter is empty.");
            return JSON.toJSONString(res);
        }
        Long amount = tInfo.getAmount();
        String fromId = tInfo.getFromId();
        String toId = tInfo.getToId();

        // 自己人民币账户减少
        assetCNYService.update(fromId, -amount);
        // fd 账户增加
        assetCNYKService.update(toId, amount);

        res.setCode(200);
        res.setMsg("OK");
        return JSON.toJSONString(res);
    }

    @PostMapping("cnyk2cny")
    public String Cnyk2Cny(Transation tInfo) {

        BackendResult res = new BackendResult();

        if (null == tInfo.getFromId() ||
                tInfo.getFromId().isEmpty() ||
                null == tInfo.getToId() ||
                tInfo.getToId().isEmpty() ||
                null == tInfo.getAmount()
        ) {
            res.setCode(-1);
            res.setMsg("some paramter is empty.");
            return JSON.toJSONString(res);
        }
        Long amount = tInfo.getAmount();
        String fromId = tInfo.getFromId();
        String toId = tInfo.getToId();

        // fd 账户减少
        assetCNYKService.update(fromId, -amount);
        // 人民币账户增加
        assetCNYService.update(toId, amount);

        res.setCode(200);
        res.setMsg("OK");
        return JSON.toJSONString(res);
    }

    @PostMapping("cnyk2cnyk")
    public String Cnyk2Cnyk(Transation tInfo) {

        BackendResult res = new BackendResult();

        if (null == tInfo.getFromId() ||
                tInfo.getFromId().isEmpty() ||
                null == tInfo.getToId() ||
                tInfo.getToId().isEmpty() ||
                null == tInfo.getAmount()
        ) {
            res.setCode(-1);
            res.setMsg("some paramter is empty.");
            return JSON.toJSONString(res);
        }
        Long amount = tInfo.getAmount();
        String fromId = tInfo.getFromId();
        String toId = tInfo.getToId();

        // fd 账户减少
        assetCNYKService.update(fromId, -amount);
        // 人民币账户增加
        assetCNYKService.update(toId, amount);

        res.setCode(200);
        res.setMsg("OK");
        return JSON.toJSONString(res);
    }


}
