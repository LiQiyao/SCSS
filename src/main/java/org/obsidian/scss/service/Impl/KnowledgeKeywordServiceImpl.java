package org.obsidian.scss.service.Impl;

import org.obsidian.scss.dao.KnowledgeKeywordMapper;
import org.obsidian.scss.entity.Knowledge;
import org.obsidian.scss.entity.KnowledgeExample;
import org.obsidian.scss.entity.KnowledgeKeyword;
import org.obsidian.scss.entity.KnowledgeKeywordExample;
import org.obsidian.scss.service.KnowledgeKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by hp on 2017/7/16.
 */
@Service
public class KnowledgeKeywordServiceImpl implements KnowledgeKeywordService {
    @Autowired
    KnowledgeKeywordMapper knowledgeKeywordMapper;
    
    @Transactional
    public List<KnowledgeKeyword> selectKeywordId(int knowledgeId) {
        KnowledgeKeywordExample example = new KnowledgeKeywordExample();
        KnowledgeKeywordExample.Criteria criteria = example.createCriteria();
        criteria.andKnowledgeIdEqualTo(knowledgeId);
        return knowledgeKeywordMapper.selectByExample(example);
    }

    @Transactional
    public int selectKeywordIdNum(int keywordId) {
        System.out.println(keywordId);
        KnowledgeKeywordExample example = new KnowledgeKeywordExample();
        KnowledgeKeywordExample.Criteria criteria = example.createCriteria();
        criteria.andKeywordIdEqualTo(keywordId);
        return knowledgeKeywordMapper.selectByExample(example).size();
    }

    @Transactional
    public int delete(int keyword, int knowledgeId) {
        KnowledgeKeywordExample example = new KnowledgeKeywordExample();
        KnowledgeKeywordExample.Criteria criteria = example.createCriteria();
        criteria.andKeywordIdEqualTo(keyword);
        criteria.andKnowledgeIdEqualTo(knowledgeId);
        return knowledgeKeywordMapper.deleteByExample(example);
    }

    public int insert(KnowledgeKeyword knowledgeKeyword) {
        return knowledgeKeywordMapper.insert(knowledgeKeyword);
    }
}
