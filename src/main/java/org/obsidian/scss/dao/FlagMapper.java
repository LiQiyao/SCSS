package org.obsidian.scss.dao;

import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.Flag;
import org.obsidian.scss.entity.FlagExample;

import java.util.List;

public interface FlagMapper {
    List<Flag> selectAllFlag(int clientId);

    int deleteFlag(int flagId);

    int updateFlag(Flag flag);

    Object selectFlagId(String name);

    String selectName(int flagId);

    long countByExample(FlagExample example);

    int deleteByExample(FlagExample example);

    int insert(Flag record);

    int insertSelective(Flag record);

    List<Flag> selectByExample(FlagExample example);

    int updateByExampleSelective(@Param("record") Flag record, @Param("example") FlagExample example);

    int updateByExample(@Param("record") Flag record, @Param("example") FlagExample example);
}