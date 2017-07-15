package org.obsidian.scss.entity;

public class Advertisement {
    private Integer advId;

    private String advContent;

    public Advertisement(Integer advId, String advContent) {
        this.advId = advId;
        this.advContent = advContent;
    }

    public Advertisement() {
        super();
    }

    public Integer getAdvId() {
        return advId;
    }

    public void setAdvId(Integer advId) {
        this.advId = advId;
    }

    public String getAdvContent() {
        return advContent;
    }

    public void setAdvContent(String advContent) {
        this.advContent = advContent == null ? null : advContent.trim();
    }
}