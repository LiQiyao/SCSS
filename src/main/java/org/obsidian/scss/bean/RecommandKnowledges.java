package org.obsidian.scss.bean;

import org.obsidian.scss.entity.Knowledge;

import java.util.List;

/**
 * Created by Lee on 2017/7/15.
 */
public class RecommandKnowledges {

    private int conversationId;

    private List<Knowledge> knowledgeList;

    public RecommandKnowledges(int conversationId, List<Knowledge> knowledgeList) {
        this.conversationId = conversationId;
        this.knowledgeList = knowledgeList;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public List<Knowledge> getKnowledgeList() {
        return knowledgeList;
    }

    public void setKnowledgeList(List<Knowledge> knowledgeList) {
        this.knowledgeList = knowledgeList;
    }

    public RecommandKnowledges() {

    }
}
