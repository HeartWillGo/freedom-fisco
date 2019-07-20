package com.heartgo.demo.model;

/**
 * 系统内错误码
 */
public enum RESULT {
    OK(0, "OK"),

    /*   数据库类错误     */
    DB_ERROR(100, "common database error."),
    DB_NO_RESP(101, "database no response."),

    /*   用户类错误       */
    USER_EMPTY_INFO(200, "user name or password is empty."),
    USER_ALREADY_EXIST(201, "register failed,user already exist."),
    USER_PASSWORD_INCRRECT(202,"user password incorrect."),
    USER_NOT_EXIST(203,"user does not exist."),

    /*   银行卡类错误      */
    BANK_EMPTY_INFO(204,"bank info empty.");


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