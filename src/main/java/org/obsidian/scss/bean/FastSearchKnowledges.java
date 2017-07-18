package org.obsidian.scss.bean;

/**
 * Created by Administrator on 2017/7/18.
 */
public class FastSearchKnowledges {

    private int conversationId;

    private String searchSentence;

    public FastSearchKnowledges() {
    }

    @Override
    public String toString() {
        return "FastSearchKnowledges{" +
                "conversationId=" + conversationId +
                ", searchSentence='" + searchSentence + '\'' +
                '}';
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public String getSearchSentence() {
        return searchSentence;
    }

    public void setSearchSentence(String searchSentence) {
        this.searchSentence = searchSentence;
    }

    public FastSearchKnowledges(int conversationId, String searchSentence) {
        this.conversationId = conversationId;
        this.searchSentence = searchSentence;
    }
}
