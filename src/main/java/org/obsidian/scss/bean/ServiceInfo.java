package org.obsidian.scss.bean;

/**
 * Created by Lee on 2017/7/17.
 */
public class ServiceInfo {

    private int serviceId;

    private String serviceName;

    private String nickName;

    private String groupName;

    private String employeeId;

    private String autoMessage;

    public ServiceInfo() {
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public ServiceInfo(int serviceId, String serviceName, String nickName, String groupName, String employeeId, String autoMessage) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.nickName = nickName;
        this.groupName = groupName;
        this.employeeId = employeeId;
        this.autoMessage = autoMessage;
    }

    public String getAutoMessage() {
        return autoMessage;
    }

    public void setAutoMessage(String autoMessage) {
        this.autoMessage = autoMessage;
    }

    @Override
    public String toString() {
        return "ServiceInfo{" +
                "serviceId=" + serviceId +
                ", serviceName='" + serviceName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", groupName='" + groupName + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", autoMessage='" + autoMessage + '\'' +
                '}';
    }
}
