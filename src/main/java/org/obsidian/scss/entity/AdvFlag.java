package org.obsidian.scss.entity;

public class AdvFlag {
    private Integer flagId;

    private Integer advId;

    public AdvFlag(Integer flagId, Integer advId) {
        this.flagId = flagId;
        this.advId = advId;
    }

    public AdvFlag() {
        super();
    }

    public Integer getFlagId() {
        return flagId;
    }

    public void setFlagId(Integer flagId) {
        this.flagId = flagId;
    }

    public Integer getAdvId() {
        return advId;
    }

    public void setAdvId(Integer advId) {
        this.advId = advId;
    }
}