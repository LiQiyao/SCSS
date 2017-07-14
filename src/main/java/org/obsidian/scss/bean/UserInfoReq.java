package org.obsidian.scss.bean;

/**
 * Created by Lee on 2017/7/14.
 */
public class UserInfoReq {
    private int userType;
    private int userId;

    public UserInfoReq(int userType, int userId) {
        this.userType = userType;
        this.userId = userId;
    }

    public UserInfoReq() {
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserInfoReq{" +
                "userType=" + userType +
                ", userId=" + userId +
                '}';
    }
}
