package org.obsidian.scss.bean;

/**
 * Created by Lee on 2017/7/28.
 */
public class TransferEndSignal {
    private int conversationId;

    public TransferEndSignal(int conversationId) {
        this.conversationId = conversationId;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public TransferEndSignal() {
    }

    @Override
    public String toString() {
        return "TransferEndSignal{" +
                "conversationId=" + conversationId +
                '}';
    }
}
