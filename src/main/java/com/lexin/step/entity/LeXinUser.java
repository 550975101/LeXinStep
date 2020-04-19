package com.lexin.step.entity;

public class LeXinUser {

    private boolean exist;
    private boolean hasMobile;
    private String userId;
    private String accessToken;
    private long expireAt;
    private int userType;
    private boolean needInfo;

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public boolean isHasMobile() {
        return hasMobile;
    }

    public void setHasMobile(boolean hasMobile) {
        this.hasMobile = hasMobile;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(long expireAt) {
        this.expireAt = expireAt;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public boolean isNeedInfo() {
        return needInfo;
    }

    public void setNeedInfo(boolean needInfo) {
        this.needInfo = needInfo;
    }
}
