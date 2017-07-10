package org.obsidian.scss.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.WorkTime;
import org.obsidian.scss.entity.WorkTimeExample;

public interface WorkTimeMapper {
    long countByExample(WorkTimeExample example);

    int deleteByExample(WorkTimeExample example);

    int insert(WorkTime record);

    int insertSelective(WorkTime record);

    List<WorkTime> selectByExample(WorkTimeExample example);

    int updateByExampleSelective(@Param("record") WorkTime record, @Param("example") WorkTimeExample example);

    int updateByExample(@Param("record") WorkTime record, @Param("example") WorkTimeExample example);
}