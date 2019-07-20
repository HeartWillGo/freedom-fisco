package com.heartgo.demo;

import com.heartgo.demo.client.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public AssetClient assetClient ()throws Exception{
        AssetClient assetClient=new AssetClient();
        assetClient.initialize();
        assetClient.deployAssetAndRecordAddr();
        return assetClient;
    }

    @Bean
    public UserInfoClient userInfoClient ()throws Exception{
        UserInfoClient userInfoClient=new UserInfoClient();
        userInfoClient.initialize();
        userInfoClient.deployAssetAndRecordAddr();
        return userInfoClient;
    }
    @Bean
    public BankInfoClient bankInfoClient ()throws Exception{
        BankInfoClient bankInfoClient=new BankInfoClient();
        bankInfoClient.initialize();
        bankInfoClient.deployAssetAndRecordAddr();
        return bankInfoClient;
    }

    @Bean
    public AssetDipostClient assetDipostClient ()throws Exception{
        AssetDipostClient assetDipostClient=new AssetDipostClient();
        assetDipostClient.initialize();
        assetDipostClient.deployAssetAndRecordAddr();
        return assetDipostClient;
    }
    @Bean
    public AssetCNYClient assetCNYClient ()throws Exception{
        AssetCNYClient assetCNYClient=new AssetCNYClient();
        assetCNYClient.initialize();
        assetCNYClient.deployAssetAndRecordAddr();
        return assetCNYClient;
    }
    @Bean
    public AssetCNYKClient assetCNYKClient ()throws Exception{
        AssetCNYKClient assetCNYKClient=new AssetCNYKClient();
        assetCNYKClient.initialize();
        assetCNYKClient.deployAssetAndRecordAddr();
        return assetCNYKClient;
    }
}
