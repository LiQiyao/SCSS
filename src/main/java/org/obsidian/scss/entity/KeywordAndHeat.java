package org.obsidian.scss.entity;

import java.util.List;

public class KeywordAndHeat {
    private Integer keywordId;

    private Long heatTime;
    
    private List<Keyword> keywords;

    public Integer getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(Integer keywordId) {
        this.keywordId = keywordId;
    }

    public Long getHeatTime() {
        return heatTime;
    }

    public void setHeatTime(Long heatTime) {
        this.heatTime = heatTime;
    }
    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }
    public KeywordAndHeat(Integer keywordId, Long heatTime,List<Keyword> keywords) {
        this.keywordId = keywordId;
        this.heatTime = heatTime;
        this.keywords =keywords;
    }

    public KeywordAndHeat() {
    }

    @Override
    public String toString() {
        return "KeywordHeat{" +
                "keywordId=" + keywordId +
                ", heatTime=" + heatTime +
                '}';
    }
}