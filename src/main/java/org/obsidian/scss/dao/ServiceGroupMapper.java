package org.obsidian.scss.dao;

import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.ServiceGroup;
import org.obsidian.scss.entity.ServiceGroupExample;

import java.util.List;

public interface ServiceGroupMapper {
    long countByExample(ServiceGroupExample example);

    int deleteByExample(ServiceGroupExample example);

    int insert(ServiceGroup record);

    int insertSelective(ServiceGroup record);

    List<ServiceGroup> selectByExample(ServiceGroupExample example);

    int updateByExampleSelective(@Param("record") ServiceGroup record, @Param("example") ServiceGroupExample example);

    int updateByExample(@Param("record") ServiceGroup record, @Param("example") ServiceGroupExample example);

    int deleteServiceGroup(int groupId);
}