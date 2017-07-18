package org.obsidian.scss.bean;

/**
 * Created by Administrator on 2017/7/17.
 */
public class ChangeClientConversationRelationship {

    private int conversationId;

    private int clientId;

    public ChangeClientConversationRelationship(int conversationId, int clientId) {
        this.conversationId = conversationId;
        this.clientId = clientId;
    }

    public ChangeClientConversationRelationship() {
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

    @Override
    public String toString() {
        return "ChangeClientConversationRelationship{" +
                "conversationId=" + conversationId +
                ", clientId=" + clientId +
                '}';
    }
}
