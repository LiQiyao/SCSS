package org.obsidian.scss.service.Impl;

import org.obsidian.scss.dao.KeywordMapper;
import org.obsidian.scss.entity.Keyword;
import org.obsidian.scss.entity.KeywordExample;
import org.obsidian.scss.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hp on 2017/7/16.
 */
@Service
public class KeywordServiceImpl implements KeywordService{
    @Autowired
    private KeywordMapper keywordMapper;
    
    public Keyword selectKeyword(int keywordId) {
        KeywordExample example = new KeywordExample();
        KeywordExample.Criteria criteria = example.createCriteria();
        criteria.andKeywordIdEqualTo(keywordId);
        return keywordMapper.selectByExample(example).get(0);
    }

    public Keyword selectByValue(String tagName) {
        KeywordExample example = new KeywordExample();
        KeywordExample.Criteria criteria = example.createCriteria();
        criteria.andValueEqualTo(tagName);
        return keywordMapper.selectByExample(example).get(0);
    }

    public int deleteByKeywordId(int keywordId) {
        return keywordMapper.deleteById(keywordId);
    }

    public int insertKeyword(String tagName) {
        Keyword keyword = new Keyword();
        keyword.setValue(tagName);
        return keywordMapper.insert(keyword);
    }
}
