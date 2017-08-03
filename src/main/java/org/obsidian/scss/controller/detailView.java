package org.obsidian.scss.controller;

import org.obsidian.scss.bean.DetailViewList;
import org.obsidian.scss.bean.ScoreAndNum;
import org.obsidian.scss.bean.ServiceAndRankInfo;
import org.obsidian.scss.bean.Show;
import org.obsidian.scss.entity.*;
import org.obsidian.scss.service.*;
import org.obsidian.scss.service.Impl.JoinUpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Time;
import java.util.*;

/**
 * Created by hp on 2017/7/31.
 */
@Controller
public class detailView {
    @Autowired
    private ConversationService conversationService;
    @Autowired
    private JoinUpService joinUpService; 
    @Autowired
    private WorkTimeService workTimeService;
    @Autowired
    private CustomerServiceService customerServiceService;
    @Autowired
    private ServiceGroupService serviceGroupService;
    
    @RequestMapping("selectDuringTimeInfo")
    @ResponseBody
    public Show selectDuringTimeInfo(@RequestParam(value = "startTime",defaultValue = "0") long startTime,
                                     @RequestParam(value = "stopTime",defaultValue = "0") long stopTime){
        Show show = new Show();
        long conversationCount = conversationService.selectDuringConverSationNum(startTime,stopTime);
        int clientNum = joinUpService.selectDuringClientNum(startTime,stopTime);
        double avgScore = conversationService.avgGrades(startTime,stopTime);
        List<ScoreAndNum> scoreAndNums= conversationService.scoreAndNum(startTime,stopTime);
        List<AccessAndNumDuring> accessAndNumDurings = joinUpService.selectAccess(startTime,stopTime);
        DetailViewList detailViewList = new DetailViewList();
        detailViewList.setConversationCount(conversationCount);
        detailViewList.setClientNum(clientNum);
        detailViewList.setAvgScore(avgScore);
        detailViewList.setScoreAndNums(scoreAndNums);
        detailViewList.setAccessAndNumDurings(accessAndNumDurings);
        show.setData(detailViewList);
        return show;
    }
    
    @RequestMapping("rankInfo")
    @ResponseBody
    public Show rankInfo(@RequestParam(value = "startTime",defaultValue = "0") long startTime,
                    @RequestParam(value = "stopTime",defaultValue = "0") long stopTime){
        Show show = new Show();
        List<ServiceAndRankInfo> serviceAndRankInfos = new ArrayList<ServiceAndRankInfo>();
        List<CustomerService> customerServices = customerServiceService.selectAllCustomerService();
        for (int i = 0 ; i < customerServices.size();i++){
            ServiceAndRankInfo serviceAndRankInfo = new ServiceAndRankInfo();
            List<ScoreAndRank>scoreAndRanks = conversationService.selectScoreAndRank(startTime,stopTime,customerServices.get(i).getServiceId());
            List<ConNumAndRank> conNumAndRanks = conversationService.selectConNumAndRank(startTime,stopTime,customerServices.get(i).getServiceId());
            List<TimeAndRank> timeAndRanks = workTimeService.selectTimeAndRank(startTime,stopTime,customerServices.get(i).getServiceId());
            serviceAndRankInfo.setScoreAndRanks(scoreAndRanks);
            serviceAndRankInfo.setConNumAndRanks(conNumAndRanks);
            serviceAndRankInfo.setTimeAndRanks(timeAndRanks);
            serviceAndRankInfo.setCustomerService(customerServices.get(i));
            serviceAndRankInfo.setServiceGroup(serviceGroupService.selectGroupByGroupId(customerServices.get(i).getGroupId()));
            serviceAndRankInfos.add(serviceAndRankInfo);
        }
        show.setData(serviceAndRankInfos);
        return show;
    }
}
