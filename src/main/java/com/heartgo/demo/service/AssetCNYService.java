package com.heartgo.demo.service;

import com.heartgo.demo.client.AssetCNYClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;

/**
 * 人民币账户 service
 */
public class AssetCNYService {

    @Autowired
    private AssetCNYClient assetCNYClient;

    /**
     * 更新金额,change 有符号
     *
     * @param account
     * @param change
     * @return
     */
    public boolean update(String account, Long change) {
        try {
            long l = assetCNYClient.queryCNYK(account);
            BigInteger newValue = BigInteger.valueOf(l += change);
            assetCNYClient.update(account, newValue);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
