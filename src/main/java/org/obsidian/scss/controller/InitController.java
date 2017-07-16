package org.obsidian.scss.controller;

import org.obsidian.scss.service.GroupQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Controller;

/**
 * Created by Lee on 2017/7/7.
 */
@Controller
public class InitController implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private GroupQueue groupQueue;

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("服务器启动！======初始化队列Bean");
        System.out.println(groupQueue);
    }
}
