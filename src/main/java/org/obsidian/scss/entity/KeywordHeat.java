package org.obsidian.scss.entity;

public class KeywordHeat {
    private Integer keywordId;

    private Long heatTime;

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

    public KeywordHeat() {
    }

    public KeywordHeat(Integer keywordId, Long heatTime) {
        this.keywordId = keywordId;
        this.heatTime = heatTime;
    }
}