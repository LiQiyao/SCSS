package org.obsidian.scss.entity;

import java.util.List;

public class Conversation {
    private Integer conversationId;

    private Integer clientId;

    private Integer serviceId;

    private Long startTime;

    private Long stopTime;

    private Integer score;

    private List<ChatLog> chatLogList;

    public Integer getConversationId() {
        return conversationId;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getStopTime() {
        return stopTime;
    }

    public void setStopTime(Long stopTime) {
        this.stopTime = stopTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<ChatLog> getChatLogList() {
        return chatLogList;
    }

    public void setChatLogList(List<ChatLog> chatLogList) {
        this.chatLogList = chatLogList;
    }

    public Conversation() {
    }

    public Conversation(Integer clientId, Integer serviceId, Long startTime) {
        this.clientId = clientId;
        this.serviceId = serviceId;
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "conversationId=" + conversationId +
                ", clientId=" + clientId +
                ", serviceId=" + serviceId +
                ", startTime=" + startTime +
                ", stopTime=" + stopTime +
                ", score=" + score +
                ", chatLogList=" + chatLogList +
                '}';
    }
}