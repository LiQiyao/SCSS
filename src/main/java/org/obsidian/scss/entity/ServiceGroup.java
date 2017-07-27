package org.obsidian.scss.entity;

import java.util.LinkedList;
import java.util.List;

public class ServiceGroup {
    private Integer groupId;

    private String name;

    private List<CustomerService> onlineMembers = new LinkedList<CustomerService>();

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public List<CustomerService> getOnlineMembers() {
        return onlineMembers;
    }

    public void setOnlineMembers(List<CustomerService> onlineMembers) {
        this.onlineMembers = onlineMembers;
    }

    public ServiceGroup() {
    }

    public ServiceGroup(Integer groupId, String name, List<CustomerService> onlineMembers) {
        this.groupId = groupId;
        this.name = name;
        this.onlineMembers = onlineMembers;
    }

    @Override
    public String toString() {
        return "ServiceGroup{" +
                "groupId=" + groupId +
                ", name='" + name + '\'' +
                ", onlineMembers=" + onlineMembers +
                '}';
    }
}