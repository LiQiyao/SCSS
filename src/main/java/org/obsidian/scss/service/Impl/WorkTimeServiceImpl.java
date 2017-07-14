package org.obsidian.scss.service.Impl;

import org.obsidian.scss.dao.WorkTimeMapper;
import org.obsidian.scss.entity.WorkTime;
import org.obsidian.scss.entity.WorkTimeExample;
import org.obsidian.scss.service.WorkTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
@Service
public class WorkTimeServiceImpl implements WorkTimeService {

    @Autowired
    private WorkTimeMapper workTimeMapper;

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

    /**
     * create By cjn
     *查询当前在线客服总人数 
     * @return
     */
    @Transactional
    public int OnlineServer() {
        return  workTimeMapper.selectOnlineServerNum();
    }
    
}
