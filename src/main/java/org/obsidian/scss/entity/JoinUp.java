package org.obsidian.scss.entity;

public class JoinUp {
    private Integer joinUpId;

    private Integer accessId;

    private Integer clientId;

    private Long time;

    private String account;

    public Integer getJoinUpId() {
        return joinUpId;
    }

    public void setJoinUpId(Integer joinUpId) {
        this.joinUpId = joinUpId;
    }

    public Integer getAccessId() {
        return accessId;
    }

    public void setAccessId(Integer accessId) {
        this.accessId = accessId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }
}