package org.obsidian.scss.bean;

/**
 * Created by Lee on 2017/7/20.
 */
public class ScoreReq {

    private int conversationId;

    public ScoreReq(int conversationId) {
        this.conversationId = conversationId;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    @Override
    public String toString() {
        return "ScoreReq{" +
                "conversationId=" + conversationId +
                '}';
    }
}
