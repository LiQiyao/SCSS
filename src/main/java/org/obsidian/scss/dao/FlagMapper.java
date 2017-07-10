package org.obsidian.scss.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.Flag;
import org.obsidian.scss.entity.FlagExample;

public interface FlagMapper {
    long countByExample(FlagExample example);

    int deleteByExample(FlagExample example);

    int insert(Flag record);

    int insertSelective(Flag record);

    List<Flag> selectByExample(FlagExample example);

    int updateByExampleSelective(@Param("record") Flag record, @Param("example") FlagExample example);

    int updateByExample(@Param("record") Flag record, @Param("example") FlagExample example);
}