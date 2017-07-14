package org.obsidian.scss.controller;

import org.obsidian.scss.entity.Conversation;
import org.obsidian.scss.entity.CustomerService;
import org.obsidian.scss.service.ConversationService;
import org.obsidian.scss.service.CustomerServiceService;
import org.obsidian.scss.service.WorkTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hp on 2017/7/14.
 */
@Controller
public class OverViewController {
    @Autowired
    private WorkTimeService workTimeService;
    @Autowired
    private CustomerServiceService customerService;
    @Autowired
    private ConversationService conversationService;
    
    @RequestMapping("/OverView")
//    @ResponseBody
    public String OverView (Model model){
        System.out.println("客服在线人数："+workTimeService.OnlineServer());
        System.out.println("客服总人数："+customerService.selectTotalServer());
        System.out.println("在线会话数："+conversationService.selectConversationNotFinish());
        return "";
    }
}
