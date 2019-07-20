package com.heartgo.demo.model;

public class User
{
    private String userId;
    private String userPhone;
    private String idCard;
    private String userName;
    private String passWord; //密码
    private String bankId;   //银行卡数据的ID
    private String CNYKID;    //CNYK账户Id
    private String CNYID;    //CNY账户Id
    private String depositID;    //CNY账户Id

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
