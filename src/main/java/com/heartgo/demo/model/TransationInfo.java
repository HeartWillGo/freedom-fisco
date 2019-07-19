package com.heartgo.demo.model;

public class TransationInfo {
    private String fromId;
    private String toId;
    private int action;
    private int acountType;
    private long amount;

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

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getAcountType() {
        return acountType;
    }

    public void setAcountType(int acountType) {
        this.acountType = acountType;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
