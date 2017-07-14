package org.obsidian.scss.service.Impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.obsidian.scss.bean.ClientChat;
import org.obsidian.scss.bean.Message;
import org.obsidian.scss.dao.KeywordMapper;
import org.obsidian.scss.dao.KnowledgeKeywordMapper;
import org.obsidian.scss.dao.KnowledgeMapper;
import org.obsidian.scss.entity.Client;
import org.obsidian.scss.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Type;
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
        knowledgeService.removeKnowledge(2);
    }

    @Test
    public void readTrieFromDB() throws Exception {
        String msg = "{\"type\":\"ClientChat\",\"content\":{\"clientId\":1,\"chatMsg\":\"我有3.5个亿，现在房价怎么样？\"}}";
        Gson gson = new Gson();
/*        Message message = gson.fromJson(msg, Message.class);
        String content2 = gson.toJson(message.getContent());
        ClientChat clientChat = gson.fromJson(content2, ClientChat.class);
        System.out.println(content2);
        ((ClientChat)message.getContent()).getChatMsg();
        System.out.println(message.getContent().getClass());*/
        Type objectType = new TypeToken<Message<ClientChat>>(){}.getType();
        Message<ClientChat> message = gson.fromJson(msg, objectType);
        System.out.println(message.getContent().getClass());
    }

    @Test
    public void readTrieFromFile() throws Exception {

    }

    @Test
    public void getKnowledgeByContent() throws Exception {
        /*knowledgeService.getKnowledgeByContent("我要买房价买房，售楼部在哪里啊，多少钱一平方啊？");*/
        knowledgeService.getRobotChat("我要买房价买房，售楼部在哪里啊，多少钱一平方啊？");
    }
}