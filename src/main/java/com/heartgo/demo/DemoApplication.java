package com.heartgo.demo;

import com.heartgo.demo.client.*;
import com.heartgo.demo.service.AssetCNYKService;
import com.heartgo.demo.service.AssetCNYService;
import com.heartgo.demo.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;


@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws IOException {

        final Resource contractResource = new ClassPathResource("contract.properties");
        FileWriter fileWriter =new FileWriter(contractResource.getFile());
        fileWriter.write("");
        fileWriter.close();
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

    @Bean
    public AssetCNYKService assetCNYKService() {
        return new AssetCNYKService();
    }

    @Bean
    public AssetCNYService assetCNYService() {
        return new AssetCNYService();
    }

    @Bean
    public UserService userService() {
        return new UserService();
    }

}
