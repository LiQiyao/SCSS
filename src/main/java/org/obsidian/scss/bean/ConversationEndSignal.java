package org.obsidian.scss.bean;

/**
 * Created by Lee on 2017/7/18.
 */
public class ConversationEndSignal {

    private int conversationId;

    @Override
    public String toString() {
        return "ConversationEndSignal{" +
                "conversationId=" + conversationId +
                '}';
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public ConversationEndSignal(int conversationId) {
        this.conversationId = conversationId;
    }
}
