package org.obsidian.scss.controller;

import org.obsidian.scss.bean.Show;
import org.obsidian.scss.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hp on 2017/7/31.
 */
@Controller
public class detailView {
    @Autowired
    private ConversationService conversationService;
    
    @RequestMapping("selectDuringTimeInfo")
    @ResponseBody
    public Show selectDuringTimeInfo(@RequestParam(value = "startTime",defaultValue = "0") long startTime,
                                     @RequestParam(value = "stopTime",defaultValue = "0") long stopTime){
        Show show = new Show();
        long conversationCount = conversationService.selectDuringConverSationNum(startTime,stopTime);
        show.setData(conversationCount);
        return show;
    }
}
