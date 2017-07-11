package org.obsidian.scss.entity;

public class KnowledgeKeyword {
    private Integer keywordId;

    private Integer knowledgeId;

    public Integer getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(Integer keywordId) {
        this.keywordId = keywordId;
    }

    public Integer getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(Integer knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public KnowledgeKeyword(Integer keywordId, Integer knowledgeId) {
        this.keywordId = keywordId;
        this.knowledgeId = knowledgeId;
    }
}