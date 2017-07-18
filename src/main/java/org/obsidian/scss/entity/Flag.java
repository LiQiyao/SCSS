package org.obsidian.scss.entity;

public class Flag {
    private Integer flagId;

    private String name;

    public Integer getFlagId() {
        return flagId;
    }

    public void setFlagId(Integer flagId) {
        this.flagId = flagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "Flag{" +
                "flagId=" + flagId +
                ", name='" + name + '\'' +
                '}';
    }
}