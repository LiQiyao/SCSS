package org.obsidian.scss.bean;

import org.obsidian.scss.entity.ChatLog;

import java.util.List;

/**
 * Created by Lee on 2017/7/17.
 */
public class TransferSignal {

    private int conversationId;

    private int clientId;

    private List<ChatLog> historyList;


    public TransferSignal() {
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public List<ChatLog> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<ChatLog> historyList) {
        this.historyList = historyList;
    }

    public TransferSignal(int conversationId, int clientId, List<ChatLog> historyList) {
        this.conversationId = conversationId;
        this.clientId = clientId;
        this.historyList = historyList;
    }

    @Override
    public String toString() {
        return "TransferSignal{" +
                "conversationId=" + conversationId +
                ", clientId=" + clientId +
                ", historyList=" + historyList +
                '}';
    }
}
