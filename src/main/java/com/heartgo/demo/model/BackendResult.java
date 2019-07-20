package com.heartgo.demo.model;


/**
 * 前后台交互错误码,内容可以灵活添加,也可以直接由 RESULT 构造
 */
public class BackendResult {
    private int code;
    private String msg;

    public BackendResult(){}

    public BackendResult(RESULT res) {
        this.code = res.getCode();
        this.setMsg(res.getDescription());
    }

    public void fillByRes(RESULT res) {
        this.code = res.getCode();
        this.setMsg(res.getDescription());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
