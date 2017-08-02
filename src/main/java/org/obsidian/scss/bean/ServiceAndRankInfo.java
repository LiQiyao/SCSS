package org.obsidian.scss.bean;

import org.obsidian.scss.entity.*;

import java.util.*;
/**
 * Created by hp on 2017/8/1.
 */
public class ServiceAndRankInfo {
    public CustomerService customerService;
    public ServiceGroup serviceGroup;
    public List<ConNumAndRank> conNumAndRanks;
    public List<ScoreAndRank>scoreAndRanks;
    public List<TimeAndRank> timeAndRanks;

    public ServiceGroup getServiceGroup() {
        return serviceGroup;
    }

    public void setServiceGroup(ServiceGroup serviceGroup) {
        this.serviceGroup = serviceGroup;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public List<ConNumAndRank> getConNumAndRanks() {
        return conNumAndRanks;
    }

    public void setConNumAndRanks(List<ConNumAndRank> conNumAndRanks) {
        this.conNumAndRanks = conNumAndRanks;
    }

    public List<ScoreAndRank> getScoreAndRanks() {
        return scoreAndRanks;
    }

    public void setScoreAndRanks(List<ScoreAndRank> scoreAndRanks) {
        this.scoreAndRanks = scoreAndRanks;
    }

    public List<TimeAndRank> getTimeAndRanks() {
        return timeAndRanks;
    }

    public void setTimeAndRanks(List<TimeAndRank> timeAndRanks) {
        this.timeAndRanks = timeAndRanks;
    }
}
