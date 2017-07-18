package org.obsidian.scss.bean;

/**
 * Created by Lee on 2017/7/18.
 */
public class ConversationEndReq {

    private int conversationId;

    public ConversationEndReq(int conversationId) {
        this.conversationId = conversationId;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public ConversationEndReq() {
    }

    @Override
    public String toString() {
        return "ConversationEndReq{" +
                "conversationId=" + conversationId +
                '}';
    }
}
