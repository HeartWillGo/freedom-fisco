package com.heartgo.demo.model;

public enum RESULT {
    OK(0, "OK"),
    DB_ERROR(1, "common database error."),
    EMPTY_USER_INFO(2, "user name or password is empty."),
    DB_NO_RESP(3, "database no response."),
    USER_ALREADY_EXIST(4, "register failed,user already exist."),
    USER_PASSWORD_INCRRECT(5,"user password incorrect."),
    USER_NOT_EXIST(6,"user does not exist.");


    private final int code;
    private final String description;

    private RESULT(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
}