package org.obsidian.scss.controller;

import org.obsidian.scss.bean.OverViewInfo;
import org.obsidian.scss.bean.Show;
import org.obsidian.scss.entity.Conversation;
import org.obsidian.scss.entity.CustomerService;
import org.obsidian.scss.entity.ServiceGroup;
import org.obsidian.scss.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Cjn on 2017/7/14.
 */
@Controller
public class OverViewController {
    @Autowired
    private WorkTimeService workTimeService;
    @Autowired
    private CustomerServiceService customerService;
    @Autowired
    private ConversationService conversationService;
    @Autowired
    private AdvertisementService advertisementService;
    @Autowired
    private KeywordHeatService keywordHeatService;
    @Autowired
    private ServiceGroupService serviceGroupService;
    
    @RequestMapping("/OverView")
    @ResponseBody
    public Show  OverView (){
        System.out.println("客服在线人数："+workTimeService.OnlineServer());
        System.out.println("客服总人数："+customerService.selectTotalServer());
        System.out.println("在线会话数："+conversationService.selectConversationNotFinish());
        System.out.println("最近一个月会话："+conversationService.selectRecentMonth().size());
        System.out.println("最近一个星期会话："+conversationService.selectRecentWeekend().size());
        System.out.println("最近24小时会话：" + conversationService.selectRecentHour().size());
        System.out.println("最近10分钟会话:"+conversationService.selectRecentMinute().size());
        OverViewInfo overViewInfo = new OverViewInfo();
        overViewInfo.setGroupAndPersonNums(serviceGroupService.selectGroupPersonNum());
        overViewInfo.setWeekInfo(conversationService.selectRecentWeekend());
        overViewInfo.setHalfMonth(conversationService.selectRecentMonth());
        overViewInfo.setMinuteInfo(conversationService.selectRecentMinute());
        overViewInfo.setOnlineServer(workTimeService.OnlineServer());
        overViewInfo.setTotalServer(customerService.selectTotalServer());
        overViewInfo.setOnlineClient(conversationService.selectConversationNotFinish());
        overViewInfo.setAdvCount(advertisementService.getTotalAdv());
        overViewInfo.setKeywordAndHeats(keywordHeatService.getHeatWord());
        Show show = new Show();
        show.setData(overViewInfo);
        return show;
    }
}
