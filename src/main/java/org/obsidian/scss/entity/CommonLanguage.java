package org.obsidian.scss.entity;

public class CommonLanguage {
    private Integer commonLanguageId;

    private Integer serviceId;

    private String content;

    private Integer frequency;

    public Integer getCommonLanguageId() {
        return commonLanguageId;
    }

    public void setCommonLanguageId(Integer commonLanguageId) {
        this.commonLanguageId = commonLanguageId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }
}