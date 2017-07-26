package org.obsidian.scss.controller;

import org.obsidian.scss.bean.ServiceOverview;
import org.obsidian.scss.service.ConversationService;
import org.obsidian.scss.service.WorkTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Lee on 2017/7/26.
 */
@Controller
@RequestMapping("/service")
public class ServiceOverviewController {

    @Autowired
    private WorkTimeService workTimeService;

    @Autowired
    private ConversationService conversationService;

    @RequestMapping(value = "/overview", method = RequestMethod.GET)
    @ResponseBody
    public ServiceOverview overView(){
        ServiceOverview serviceOverView = new ServiceOverview();
        return serviceOverView;
    }
}
