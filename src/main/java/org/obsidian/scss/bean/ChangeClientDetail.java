package org.obsidian.scss.bean;

/**
 * Created by Administrator on 2017/7/16.
 */
public class ChangeClientDetail {

    private int clientId;

    private String clientName;

    private int sex;

    private Long phoneNum;

    private String email;

    private String address;

    private String wx;

    private String qq;

    private String weibo;

    private String taobao;

    private String alipay;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getTaobao() {
        return taobao;
    }

    public void setTaobao(String taobao) {
        this.taobao = taobao;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public ChangeClientDetail() {
    }

    public ChangeClientDetail(int clientId, String clientName, int sex, Long phoneNum, String email, String address, String wx, String qq, String weibo, String taobao, String alipay) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.sex = sex;
        this.phoneNum = phoneNum;
        this.email = email;
        this.address = address;
        this.wx = wx;
        this.qq = qq;
        this.weibo = weibo;
        this.taobao = taobao;
        this.alipay = alipay;
    }

    @Override
    public String toString() {
        return "ChangeClientDetail{" +
                "clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", sex=" + sex +
                ", phoneNum=" + phoneNum +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", wx='" + wx + '\'' +
                ", qq='" + qq + '\'' +
                ", weibo='" + weibo + '\'' +
                ", taobao='" + taobao + '\'' +
                ", alipay='" + alipay + '\'' +
                '}';
    }
}
