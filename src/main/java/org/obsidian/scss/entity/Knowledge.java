package org.obsidian.scss.entity;

import java.util.List;

public class Knowledge {
    private Integer knowledgeId;

    private String question;

    private String answer;

    private Integer level;

    private Integer author;

    private Long time;

    private List<Keyword> keywordList;

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

    public Knowledge(Integer knowledgeId, String question, String answer, Integer level, Integer author, Long time) {
        this.knowledgeId = knowledgeId;
        this.question = question;
        this.answer = answer;
        this.level = level;
        this.author = author;
        this.time = time;
    }

    public Knowledge() {
    }

    public List<Keyword> getKeywordList() {
        return keywordList;
    }

    public void setKeywordList(List<Keyword> keywordList) {
        this.keywordList = keywordList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Knowledge knowledge = (Knowledge) o;

        if (knowledgeId != null ? !knowledgeId.equals(knowledge.knowledgeId) : knowledge.knowledgeId != null)
            return false;
        if (question != null ? !question.equals(knowledge.question) : knowledge.question != null) return false;
        if (answer != null ? !answer.equals(knowledge.answer) : knowledge.answer != null) return false;
        if (level != null ? !level.equals(knowledge.level) : knowledge.level != null) return false;
        if (author != null ? !author.equals(knowledge.author) : knowledge.author != null) return false;
        return time != null ? time.equals(knowledge.time) : knowledge.time == null;
    }

    @Override
    public int hashCode() {
        int result = knowledgeId != null ? knowledgeId.hashCode() : 0;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Knowledge{" +
                "knowledgeId=" + knowledgeId +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", level=" + level +
                ", author=" + author +
                ", time=" + time +
                ", keywordList=" + keywordList +
                '}';
    }
}