package org.obsidian.scss.bean;

/**
 * Created by Lee on 2017/7/18.
 */
public class SetScore {

    private int conversationId;

    private int score;

    @Override
    public String toString() {
        return "SetScore{" +
                "conversationId=" + conversationId +
                ", score=" + score +
                '}';
    }

    public SetScore() {
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public SetScore(int conversationId, int score) {
        this.conversationId = conversationId;
        this.score = score;
    }
}
