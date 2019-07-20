package com.heartgo.demo.service;

import com.heartgo.demo.client.AssetCNYKClient;
import com.heartgo.demo.model.RESULT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;

/**
 * 虚拟资产 service
 */
public class AssetCNYKService {
    @Autowired
    private AssetCNYKClient assetCNYKClient;

//    public RESULT registAccount(String userId,String cnykAccount)
//    {
//    }

    public boolean update(String account, Long change) {
        try {
            long l = assetCNYKClient.queryCNYAmount(account);
            BigInteger newValue = BigInteger.valueOf(l += change);
            assetCNYKClient.update(account, newValue);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
