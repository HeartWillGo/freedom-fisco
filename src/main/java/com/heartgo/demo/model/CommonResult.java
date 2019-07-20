package com.heartgo.demo.model;

public class CommonResult {
    private int code;
    private String msg;

    public CommonResult(){}

    public CommonResult(RESULT res) {
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
