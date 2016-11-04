package com.superbool.easyui.model;

public class UserInfo {

    private Integer id;
    private String cardId;
    private String name;
    private String department;
    private String sameId;
    private String createAt;
    private String modifyAt;

    public UserInfo() {
    }


    public UserInfo(String cardId, String name, String department) {
        this.cardId = cardId;
        this.name = name;
        this.department = department;

    }


    public UserInfo(String cardId, String name, String department, String sameId) {
        this.cardId = cardId;
        this.name = name;
        this.department = department;
        this.sameId = sameId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(String modifyAt) {
        this.modifyAt = modifyAt;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", cardId='" + cardId + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", sameId='" + sameId + '\'' +
                ", createAt='" + createAt + '\'' +
                ", modifyAt='" + modifyAt + '\'' +
                '}';
    }
}
