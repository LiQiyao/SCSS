package org.obsidian.scss.service.Impl;

import org.obsidian.scss.bean.Message;
import org.obsidian.scss.bean.RobotChat;
import org.obsidian.scss.dao.KeywordHeatMapper;
import org.obsidian.scss.dao.KeywordMapper;
import org.obsidian.scss.dao.KnowledgeKeywordMapper;
import org.obsidian.scss.dao.KnowledgeMapper;
import org.obsidian.scss.entity.*;
import org.obsidian.scss.service.KnowledgeService;
import org.obsidian.scss.util.KeywordFinder;
import org.obsidian.scss.util.Trie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Autowired
    private KeywordHeatMapper keywordHeatMapper;

    /**
     * 添加知识库以及其对应的关键词
     */
    @Transactional
    public int addKnowledgeAndKey(String question, String answer, int level, int author, long time, List<String> keyList) {
        knowledgeMapper.insertSelective(new Knowledge(question, answer, level, author, time));
        int knowledgeId = knowledgeMapper.selectLastId();
        int keywordId;
        keyList.add(question);
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
        return 1;//全都是返回0了修正为1
    }

    /**
     * 根据知识Id删除知识
     */
    @Transactional
    public int removeKnowledge(int knowledgeId) {
        Knowledge knowledge = knowledgeMapper.selectByKnowledgeId(knowledgeId);
        knowledgeMapper.deleteById(knowledgeId);
        knowledgeKeywordMapper.deleteByKnowledgeId(knowledgeId);
        if(knowledge != null){
            for (Keyword keyword : knowledge.getKeywordList()){
                if (knowledgeKeywordMapper.selectByKeywordId(keyword.getKeywordId()) == 0){
                    keywordMapper.deleteById(keyword.getKeywordId());
                }
            }
        }
        return 0;
    }

    public Trie readTrieFromDB() {
        List<Keyword> list = keywordMapper.selectAll();
        return new Trie(list);
    }

    public Trie readTrieFromFile() {
        return null;
    }
    /**
     * 根据内容反馈知识库
     */
    @Transactional
    public List<Knowledge> getKnowledgeByContent(String content) {
        Trie trie = new Trie(keywordMapper.selectAll());
        List<Keyword> found = KeywordFinder.findKeywordInContent(content, trie);
        Map<Knowledge, List<Keyword>> map = new HashMap<Knowledge, List<Keyword>>();
        for (Keyword keyword : found){
            keywordHeatMapper.insert(new KeywordHeat(keyword.getKeywordId(), new Date().getTime()));
            List<Knowledge> knowledgeList = knowledgeMapper.selectByKeywordId(keyword.getKeywordId());
            for (Knowledge knowledge : knowledgeList){
                if (map.containsKey(knowledge)){
                    map.get(knowledge).add(keyword);
                }else {
                    List<Keyword> tmp = new ArrayList<Keyword>();
                    tmp.add(keyword);
                    map.put(knowledge, tmp);
                }
            }
        }
        List<Knowledge> res = new ArrayList<Knowledge>();
        for (Knowledge key : map.keySet()){
            key.setKeywordList(map.get(key));
            res.add(key);
        }
        return res;
    }

    /**
     * 根据客户提问，机器人作出回答并关联推送
     */
    @Transactional
    public Message<RobotChat> getRobotChat(String content) {
        Trie trie = new Trie(keywordMapper.selectAll());
        List<Keyword> found = KeywordFinder.findKeywordInContent(content, trie);
        Map<Knowledge, Integer> map = new HashMap<Knowledge, Integer>();
        int maxHit = 1;
        for (Keyword keyword : found){
            keywordHeatMapper.insert(new KeywordHeat(keyword.getKeywordId(), new Date().getTime()));
            List<Knowledge> knowledgeList = knowledgeMapper.selectByKeywordId1(keyword.getKeywordId());
            for (Knowledge knowledge : knowledgeList){
                if (map.containsKey(knowledge)){
                    map.put(knowledge, map.get(knowledge) + 1);
                    maxHit = Math.max(maxHit, map.get(knowledge));
                }else {
                    map.put(knowledge, 1);
                }
            }
        }
        boolean first = true;
        List<String> questionPush = new ArrayList<String>();
        String ans = "不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！";
        System.out.println(maxHit);
        for (Knowledge knowledge : map.keySet()){
            if (map.get(knowledge) == maxHit && first){
                ans = knowledge.getAnswer();
                System.out.println("!!");
                first = false;
            } else {
                questionPush.add(knowledge.getQuestion());
            }
        }
        System.out.println("!!!" + ans);
        return new Message<RobotChat>(new RobotChat(ans, questionPush, new Date().getTime()));
    }
    public List<Knowledge> selectKnowledgeBySearchName(String keyword) {
        return knowledgeMapper.selectKnowledgeBySearchName(keyword);
    }

    public List<Knowledge> selectKnowledge() {
        KnowledgeExample example = new KnowledgeExample();
        KnowledgeExample.Criteria criteria = example.createCriteria();
        return knowledgeMapper.selectByExample(example);
    }

    public int updateKnowledge(Knowledge knowledge) {
        KnowledgeExample example = new KnowledgeExample();
        KnowledgeExample.Criteria criteria = example.createCriteria();
        criteria.andKnowledgeIdEqualTo(knowledge.getKnowledgeId());
        int res = knowledgeMapper.updateByExample(knowledge,example);
        return res;
    }
}
