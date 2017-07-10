package org.obsidian.scss.service.Impl;

import org.apache.ibatis.javassist.compiler.ast.Keyword;
import org.obsidian.scss.dao.KeywordMapper;
import org.obsidian.scss.dao.KnowledgeKeywordMapper;
import org.obsidian.scss.dao.KnowledgeMapper;
import org.obsidian.scss.entity.KnowledgeKeyword;
import org.obsidian.scss.service.KnowledgeService;
import org.obsidian.scss.util.Trie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public int addKnowledgeAndKey(String content, int level, List<String> keyList) {
        return 1;
    }

    public int removeKnowledge(int knowledgeId) {

        return 0;
    }


    public Trie readTrieFromDB() {
        return null;
    }

    public Trie readTrieFromFile() {
        return null;
    }

    public static void main(String[] args) {
        KnowledgeService knowledgeService = new KnowledgeServiceImpl();

    }
}
