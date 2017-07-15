package org.obsidian.scss.bean;

/**
 * Created by Lee on 2017/7/14.
 */
public class UserInfoResp {

    private int userType;

    private String nickName;

    private int userId;

    public UserInfoResp() {
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public UserInfoResp(int userType, String nickName, int userId) {
        this.userType = userType;
        this.nickName = nickName;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserInfoResp{" +
                "userType=" + userType +
                ", nickName='" + nickName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
