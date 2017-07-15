package org.obsidian.scss.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Lee on 2017/7/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:spring/spring-*.xml")
public class GroupWordServiceTest {

    @Autowired
    private GroupWordService groupWordService;

    @Test
    public void getTrie() throws Exception {

    }

    @Test
    public void getServiceGroupIdByContent() throws Exception {
        System.out.println(groupWordService.getServiceGroupIdByContent("我想买房要买房，但是没钱"));
    }

}