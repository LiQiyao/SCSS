package org.obsidian.scss.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.obsidian.scss.dao.ChatLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Lee on 2017/7/13.
 */
@ContextConfiguration("classpath:spring/spring-*.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ChatLogServiceTest {

    @Autowired
    private ChatLogService chatLogService;

    @Test
    public void add() throws Exception {
        chatLogService.add(1,1,0,"hhhh",122222L,1);
    }

    @Test
    public void getByClientId() throws Exception {
        chatLogService.getByClientId(1);
    }
}