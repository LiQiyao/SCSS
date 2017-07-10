package org.obsidian.scss.entity;

public class Knowledge {
    private Integer knowledgeId;

    private String question;

    private String answer;

    private Integer level;

    private Integer author;

    private Long time;

    public Integer getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(Integer knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Knowledge(String question, String answer, Integer level, Integer author, Long time) {
        this.question = question;
        this.answer = answer;
        this.level = level;
        this.author = author;
        this.time = time;
    }
}