package org.obsidian.scss.service.Impl;

import org.obsidian.scss.dao.CustomerServiceMapper;
import org.obsidian.scss.dao.WorkTimeMapper;
import org.obsidian.scss.entity.CustomerService;
import org.obsidian.scss.entity.TimeAndRank;
import org.obsidian.scss.entity.WorkTime;
import org.obsidian.scss.entity.WorkTimeExample;
import org.obsidian.scss.service.WorkTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
@Service
public class WorkTimeServiceImpl implements WorkTimeService {

    @Autowired
    private WorkTimeMapper workTimeMapper;

    @Autowired
    private CustomerServiceMapper customerServiceMapper;

    /**
     * 新增上线记录
     * @param serviceId
     * @return
     */
    @Transactional
    public int online(int serviceId) {
        WorkTime workTime = new WorkTime();
        workTime.setServiceId(serviceId);
        workTime.setStartTime(new java.util.Date().getTime());
        int insertSum = workTimeMapper.insertSelective(workTime);
        return insertSum;
    }

    /**
     * 下线时插入下线时间
     * @param serviceId
     * @return
     */
    @Transactional
    public int offline(int serviceId) {
        int updateSum = workTimeMapper.updateWorkTime(serviceId,new java.util.Date().getTime());
        return updateSum;
    }

    /**
     * 删除上班记录
     * @param workTimeId
     * @return
     */
    @Transactional
    public int deleteWorkTime(int workTimeId) {
        int deleteSum = workTimeMapper.deleteWorkTime(workTimeId);
        return deleteSum;
    }

    /**
     * 删除所有上班记录
     * @param serviceId
     * @return
     */
    @Transactional
    public int deleteAllWorkTimeList(int serviceId){
        int deleteSum = workTimeMapper.deleteAllWorkTimeList(serviceId);
        return deleteSum;
    }

    /**
     * 查询所有上班记录
     * @param serviceId
     * @return
     */
    @Transactional
    public List<WorkTime> selectAllWorkTimeList(int serviceId) {
        List<WorkTime> list;
        WorkTimeExample example = new WorkTimeExample();
        example.or().andServiceIdEqualTo(serviceId);
        list = workTimeMapper.selectByExample(example);
        if(list.size() == 0 || list == null){
            return null;
        }
        return list;
    }

    /**
     * 查询在线总时间
     * @param serviceId
     * @return
     */
    @Transactional
    public Long selectAllWorkTimeSum(int serviceId){
        Long allWorkTime = workTimeMapper.selectAllWorkTimeSum(serviceId);
        return allWorkTime;
    }

    @Transactional
    public long getTodayWorkTime(int serviceId) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        long dayStart = calendar.getTime().getTime();
        Calendar calendar1 = new GregorianCalendar();
        calendar1.set(Calendar.HOUR_OF_DAY, 23);
        calendar1.set(Calendar.MINUTE, 59);
        calendar1.set(Calendar.SECOND, 59);
        long dayEnd = calendar1.getTime().getTime();
        List<WorkTime> list = workTimeMapper.selectTodayWorkTime(serviceId, dayStart, dayEnd);
        long start_time;
        long end_time;
        long sum = 0;
        for (WorkTime workTime : list){
            start_time = workTime.getStartTime();
            end_time = workTime.getEndTime();
            if (start_time >= dayStart && end_time <= dayEnd){
                sum += end_time - start_time;
            } else if (start_time <= dayStart){
                sum += end_time - dayStart;
            } else if (end_time >= dayEnd){
                sum += dayEnd - start_time;
            }
        }
        return sum;
    }

    public int getTodayWorkTimeRank(int serviceId) {
        List<CustomerService> list = customerServiceMapper.selectAll();
        OnlineTime[] onlineTimes = new OnlineTime[list.size()];
        int tmp;
        for (int i = 0, size = list.size(); i < size; i++){
            onlineTimes[i].setServiceId((tmp = list.get(i).getServiceId()));
            onlineTimes[i].setTime(getTodayWorkTime(tmp));
        }
        Arrays.sort(onlineTimes);
        for (int i = 0; i < onlineTimes.length; i++){
            if (onlineTimes[i].getServiceId() == serviceId){
                return i + 1;
            }
        }
        return 0;
    }


    /**
     * create By cjn
     *查询当前在线客服总人数 
     * @return
     */
    @Transactional
    public int OnlineServer() {
        return  workTimeMapper.selectOnlineServerNum();
    }

    @Transactional
    public List<TimeAndRank> selectTimeAndRank(long startTime, long endTime, int serviceId) {
        return workTimeMapper.selectTimeAndRank(startTime,endTime,serviceId);
    }
}
class OnlineTime implements Comparable<OnlineTime>{
    private long time;

    private int serviceId;



    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int compareTo(OnlineTime o) {
        long res = o.getTime() - this.getTime();
        if (res > 0)
            return 1;
        else if (res == 0)
            return 0;
        return -1;
    }
}