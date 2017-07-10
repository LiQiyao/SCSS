package org.obsidian.scss.entity;

public class Keyword {
    private Integer keywordId;

    private String value;

    public Integer getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(Integer keywordId) {
        this.keywordId = keywordId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Keyword(Integer keywordId, String value) {
        this.keywordId = keywordId;
        this.value = value;
    }

    public Keyword() {
    }

    public Keyword(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Keyword{" +
                "keywordId=" + keywordId +
                ", value='" + value + '\'' +
                '}';
    }
}