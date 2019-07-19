package com.heartgo.demo;

import com.heartgo.demo.client.AssetClient;
import com.heartgo.demo.client.UserInfoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

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

}
