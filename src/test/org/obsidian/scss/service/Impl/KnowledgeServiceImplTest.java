package org.obsidian.scss.service.Impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.obsidian.scss.dao.KeywordMapper;
import org.obsidian.scss.dao.KnowledgeKeywordMapper;
import org.obsidian.scss.dao.KnowledgeMapper;
import org.obsidian.scss.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Lee on 2017/7/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class KnowledgeServiceImplTest {

    @Autowired
    private KnowledgeService knowledgeService;

    @Test
    public void addKnowledgeAndKey() throws Exception {
        List<String> list = new ArrayList<String>();
        list.add("房价");
        list.add("涨价");
        knowledgeService.addKnowledgeAndKey("最近的房价涨价了吗", "涨价了", 1, 1, 1, list);
    }

    @Test
    public void removeKnowledge() throws Exception {
        knowledgeService.removeKnowledge(7);
    }

    @Test
    public void readTrieFromDB() throws Exception {

    }

    @Test
    public void readTrieFromFile() throws Exception {

    }

    @Test
    public void getKnowledgeByContent() throws Exception {
        /*knowledgeService.getKnowledgeByContent("我要买房价买房，售楼部在哪里啊，多少钱一平方啊？");*/
        knowledgeService.getRobotAns("我要买房价买房，售楼部在哪里啊，多少钱一平方啊？");
    }
}