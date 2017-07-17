package org.obsidian.scss.service;

import java.util.List;

import org.obsidian.scss.entity.Knowledge;
import org.obsidian.scss.entity.KnowledgeKeyword;

/**
 * Created by hp on 2017/7/16.
 */
public interface KnowledgeKeywordService {
    List<KnowledgeKeyword> selectKeywordId(int  knowledgeId);
    int selectKeywordIdNum(int  keywordId);
    int delete(int keyword,int knowledgeId);
}
