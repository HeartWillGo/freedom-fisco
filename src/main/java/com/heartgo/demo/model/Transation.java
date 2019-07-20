package com.heartgo.demo.model;

public class Transation {
    private String fromId;
    private String toId;
    private Integer action;
    private Integer acountType;
    private Long amount;

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public Integer getAcountType() {
        return acountType;
    }

    public void setAcountType(Integer acountType) {
        this.acountType = acountType;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
