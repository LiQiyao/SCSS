package org.obsidian.scss.service;

import org.obsidian.scss.entity.Keyword;

/**
 * Created by hp on 2017/7/16.
 */
public interface KeywordService {
    Keyword selectKeyword(int keywordId);
    Keyword selectByValue(String tagName);
    int deleteByKeywordId(int keywordId);
    int insertKeyword(String tagName);
}
