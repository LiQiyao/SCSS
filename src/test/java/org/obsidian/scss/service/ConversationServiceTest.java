package org.obsidian.scss.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Lee on 2017/7/13.
 */
@ContextConfiguration("classpath:spring/spring-*.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ConversationServiceTest {

    @Autowired
    private ConversationService conversationService;

    @Test
    public void getByClientId() throws Exception {

    }

    @Test
    public void getByServiceId() throws Exception {

    }

    @Test
    public void getAvgScoreByServiceId() throws Exception {

    }

    @Test
    public void startConversation() throws Exception {
        conversationService.startConversation(1, 1, 100000);
    }

    @Test
    public void endConversation() throws Exception {
        conversationService.endConversation(1,199999, 3);
    }

}