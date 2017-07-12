package org.obsidian.scss.bean;

import org.obsidian.scss.entity.Knowledge;

import java.util.List;

/**
 * Created by Lee on 2017/7/12.
 */
public class KnowledgeList {
    private List<Knowledge> knowledgeList;

    public List<Knowledge> getKnowledgeList() {
        return knowledgeList;
    }

    public void setKnowledgeList(List<Knowledge> knowledgeList) {
        this.knowledgeList = knowledgeList;
    }

    public KnowledgeList(List<Knowledge> knowledgeList) {
        this.knowledgeList = knowledgeList;
    }

    public KnowledgeList() {
    }
}
