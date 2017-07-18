package org.obsidian.scss.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */
public class RecommandTags {
    private int conversationId;

    private List<String> tagList;

    public RecommandTags(int conversationId, List<String> tagList) {
        this.conversationId = conversationId;
        this.tagList = tagList;
    }

    public RecommandTags() {
    }

    @Override
    public String toString() {
        return "RecommandTags{" +
                "conversationId=" + conversationId +
                ", tagList=" + tagList +
                '}';
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }
}
