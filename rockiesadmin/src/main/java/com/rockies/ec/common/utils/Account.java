package com.rockies.ec.common.utils;

public class Account {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public int getSite() {
        return site;
    }

    public void setSite(int site) {
        this.site = site;
    }

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStoreGroup() {
        return storeGroup;
    }

    public void setStoreGroup(int storeGroup) {
        this.storeGroup = storeGroup;
    }
    public String getRoleType() { return roleType; }

    public void setRoleType(String roleType) { this.roleType = roleType; }
    private long id;
    private String username;
    private String token;
    private long createTime;
    private long expireTime;
    private int site;
    private int storeGroup;
    private int store;
    private int role;
    private boolean isActive;
    private String email;
    private String phone;
    private String roleType;
}
