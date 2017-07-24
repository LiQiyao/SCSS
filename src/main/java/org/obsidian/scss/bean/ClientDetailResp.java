package org.obsidian.scss.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/15.
 */
public class ClientDetailResp {

    private int clientId;

    private String clientName;

    private int sex;

    private Long phoneNum;

    private String email;

    private String wx;

    private String qq;

    private String weibo;

    private String address;

    private List<String> tagList;

    private List<String> unusedTagList;

    public ClientDetailResp(int clientId, String clientName, int sex, Long phoneNum, String email, String wx, String qq, String weibo, String address, List<String> tagList, List<String> unusedTagList) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.sex = sex;
        this.phoneNum = phoneNum;
        this.email = email;
        this.wx = wx;
        this.qq = qq;
        this.weibo = weibo;
        this.address = address;
        this.tagList = tagList;
        this.unusedTagList = unusedTagList;
    }

    public ClientDetailResp() {
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Long getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Long phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public List<String> getUnusedTagList() {
        return unusedTagList;
    }

    public void setUnusedTagList(List<String> unusedTagList) {
        this.unusedTagList = unusedTagList;
    }

    @Override
    public String toString() {
        return "ClientDetailResp{" +
                "clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", sex=" + sex +
                ", phoneNum=" + phoneNum +
                ", email='" + email + '\'' +
                ", wx='" + wx + '\'' +
                ", qq='" + qq + '\'' +
                ", weibo='" + weibo + '\'' +
                ", address='" + address + '\'' +
                ", tagList=" + tagList +
                ", unusedTagList=" + unusedTagList +
                '}';
    }
}
