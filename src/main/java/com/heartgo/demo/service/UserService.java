package com.heartgo.demo.service;

import java.util.UUID;

public class UserService {
    public String getUUID()
    {
        return UUID.randomUUID().toString();
    }

}
