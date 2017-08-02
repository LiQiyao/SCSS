package org.obsidian.scss.service;

import org.obsidian.scss.entity.TimeAndRank;
import org.obsidian.scss.entity.WorkTime;

import java.sql.Time;
import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
public interface WorkTimeService {
    int online(int serviceId);

    int offline(int serviceId);

    int deleteWorkTime(int workTimeId);

    int deleteAllWorkTimeList(int serviceId);

    List<WorkTime> selectAllWorkTimeList(int serviceId);

    Long selectAllWorkTimeSum(int serviceId);

    long getTodayWorkTime(int serviceId);

    int getTodayWorkTimeRank(int serviceId);

    /***
     * Create By CJN
     */
    
    int OnlineServer();
    List<TimeAndRank> selectTimeAndRank(long startTime,long endTime,int serviceId);
    
}
