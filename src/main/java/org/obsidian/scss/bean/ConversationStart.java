package org.obsidian.scss.bean;

import org.obsidian.scss.entity.ChatLog;

import java.util.List;

/**
 * Created by Lee on 2017/7/14.
 */
public class ConversationStart {

    private int conversationId;

    private String account;

    private int clientId;

    private List<ChatLog> chatLogList;

    public ConversationStart() {
    }

    public ConversationStart(int conversationId, String account, int clientId, List<ChatLog> chatLogList) {
        this.conversationId = conversationId;
        this.account = account;
        this.clientId = clientId;
        this.chatLogList = chatLogList;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public List<ChatLog> getChatLogList() {
        return chatLogList;
    }

    public void setChatLogList(List<ChatLog> chatLogList) {
        this.chatLogList = chatLogList;
    }
}
