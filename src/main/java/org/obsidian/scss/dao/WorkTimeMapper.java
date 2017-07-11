package org.obsidian.scss.dao;

import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.WorkTime;
import org.obsidian.scss.entity.WorkTimeExample;

import java.util.List;

public interface WorkTimeMapper {
    int deleteWorkTime(int workTimeId);

    int deleteAllWorkTimeList(int serviceId);

    int updateWorkTime(@Param("serviceId") int serviceId,@Param("endTime") Long endTime);

    long selectAllWorkTimeSum(int serviceId);

    long countByExample(WorkTimeExample example);

    int deleteByExample(WorkTimeExample example);

    int insert(WorkTime record);

    int insertSelective(WorkTime record);

    List<WorkTime> selectByExample(WorkTimeExample example);

    int updateByExampleSelective(@Param("record") WorkTime record, @Param("example") WorkTimeExample example);

    int updateByExample(@Param("record") WorkTime record, @Param("example") WorkTimeExample example);
}