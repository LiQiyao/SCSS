package org.obsidian.scss.service;

import org.obsidian.scss.bean.KnowledgeList;
import org.obsidian.scss.bean.Message;
import org.obsidian.scss.bean.RobotChat;
import org.obsidian.scss.entity.Knowledge;
import org.obsidian.scss.util.Trie;

import java.util.List;

/**
 * Created by Lee on 2017/7/9.
 */
public interface KnowledgeService {

    int addKnowledgeAndKey(String question, String answer ,int level, int author, long time , List<String> keyList);

    int removeKnowledge(int knowledgeId);

    Trie readTrieFromDB();

    Trie readTrieFromFile();

    List<Knowledge> getKnowledgeByContent(String content);

    Message<RobotChat> getRobotChat(String content);
}
