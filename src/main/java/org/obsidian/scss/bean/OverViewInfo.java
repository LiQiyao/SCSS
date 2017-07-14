package org.obsidian.scss.bean;

import java.util.List;
import org.obsidian.scss.entity.DayAndTime;

/**
 * Created by hp on 2017/7/14.
 */
public class OverViewInfo {
    public int onlineServer;
    public int totalServer;
    public int onlineClient;
    public List<DayAndTime> monthInfo;
    public List<DayAndTime> weekInfo;
    public List<DayAndTime> dayInfo;
    public List<DayAndTime> minuteInfo;

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

    public List<DayAndTime> getMinuteInfo() {
        return minuteInfo;
    }

    public void setMinuteInfo(List<DayAndTime> minuteInfo) {
        this.minuteInfo = minuteInfo;
    }

    public int getOnlineServer() {
        return onlineServer;
    }

    public void setOnlineServer(int onlineServer) {
        this.onlineServer = onlineServer;
    }

    public int getTotalServer() {
        return totalServer;
    }

    public void setTotalServer(int totalServer) {
        this.totalServer = totalServer;
    }

    public int getOnlineClient() {
        return onlineClient;
    }

    public void setOnlineClient(int onlineClient) {
        this.onlineClient = onlineClient;
    }
}
