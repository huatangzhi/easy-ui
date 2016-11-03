package com.superbool.easyui.model;

public class UserInfo {

    private Integer id;
    private String cardId;
    private String name;
    private String department;
    private String sameId;

    public UserInfo() {
    }

    public UserInfo(String cardId, String name, String department, String sameId) {
        this.cardId = cardId;
        this.name = name;
        this.department = department;
        this.sameId = sameId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSameId() {
        return sameId;
    }

    public void setSameId(String sameId) {
        this.sameId = sameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", cardId='" + cardId + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", sameId='" + sameId + '\'' +
                '}';
    }
}