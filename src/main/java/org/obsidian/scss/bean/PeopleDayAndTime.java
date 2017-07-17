package org.obsidian.scss.bean;

import org.obsidian.scss.entity.CustomerService;
import org.obsidian.scss.entity.DayAndTime;

import java.util.List;

/**
 * Created by hp on 2017/7/15.
 * 客服信息和客服工作绩效
 */
public class PeopleDayAndTime {
    public CustomerService customerService;
    public List<DayAndTime> monthInfo;
    public List<DayAndTime> weekInfo;
    public List<DayAndTime> dayInfo;

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public List<DayAndTime> getMonthInfo() {
        return monthInfo;
    }

    public void setMonthInfo(List<DayAndTime> monthInfo) {
        this.monthInfo = monthInfo;
    }

    public List<DayAndTime> getWeekInfo() {
        return weekInfo;
    }

    public void setWeekInfo(List<DayAndTime> weekInfo) {
        this.weekInfo = weekInfo;
    }

    public List<DayAndTime> getDayInfo() {
        return dayInfo;
    }

    public void setDayInfo(List<DayAndTime> dayInfo) {
        this.dayInfo = dayInfo;
    }
}
