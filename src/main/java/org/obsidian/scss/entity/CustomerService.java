package org.obsidian.scss.entity;

public class CustomerService {
    private Integer serviceId;

    private String name;

    private Integer groupId;

    private String nickname;

    private String employeeId;

    private String autoMessage;

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId == null ? null : employeeId.trim();
    }

    public String getAutoMessage() {
        return autoMessage;
    }

    public void setAutoMessage(String autoMessage) {
        this.autoMessage = autoMessage == null ? null : autoMessage.trim();
    }
}