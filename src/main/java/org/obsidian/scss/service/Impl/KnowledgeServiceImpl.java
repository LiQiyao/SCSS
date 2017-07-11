package org.obsidian.scss.service.Impl;

import org.obsidian.scss.dao.KeywordMapper;
import org.obsidian.scss.dao.KnowledgeKeywordMapper;
import org.obsidian.scss.dao.KnowledgeMapper;
import org.obsidian.scss.entity.Keyword;
import org.obsidian.scss.entity.Knowledge;
import org.obsidian.scss.entity.KnowledgeKeyword;
import org.obsidian.scss.service.KnowledgeService;
import org.obsidian.scss.util.Trie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2017/7/9.
 */
@Service
public class KnowledgeServiceImpl implements KnowledgeService{

    @Autowired
    private KeywordMapper keywordMapper;

    @Autowired
    private KnowledgeMapper knowledgeMapper;

    @Autowired
    private KnowledgeKeywordMapper knowledgeKeywordMapper;


    @Transactional
    public int addKnowledgeAndKey(String question, String answer, int level, int author, long time, List<String> keyList) {
        knowledgeMapper.insertSelective(new Knowledge(question, answer, level, author, time));
        int knowledgeId = knowledgeMapper.selectLastId();
        int keywordId;
        for (String s : keyList){
            Keyword keyword = keywordMapper.selectByValue(s);
            if (keyword != null){
                keywordId = keyword.getKeywordId();
            }else {
                keywordMapper.insert(new Keyword(s));
                keywordId = keywordMapper.selectLastId();
            }
            knowledgeKeywordMapper.insert(new KnowledgeKeyword(keywordId, knowledgeId));
        }
        keywordMapper.insert(new Keyword(question));
        keywordId = keywordMapper.selectLastId();
        knowledgeKeywordMapper.insert(new KnowledgeKeyword(keywordId, knowledgeId));
        return 0;
    }

    public int removeKnowledge(int knowledgeId) {

        return 0;
    }


    public Trie readTrieFromDB() {
        List<Keyword> list = keywordMapper.selectAll();
        return new Trie(list);
    }

    public Trie readTrieFromFile() {
        return null;
    }


}
