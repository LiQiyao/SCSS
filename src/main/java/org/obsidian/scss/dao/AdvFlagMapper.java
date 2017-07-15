package org.obsidian.scss.dao;

import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.AdvFlag;
import org.obsidian.scss.entity.AdvFlagExample;

import java.util.List;

public interface AdvFlagMapper {
    int countByExample(AdvFlagExample example);

    int deleteByExample(AdvFlagExample example);

    int insert(AdvFlag record);

    int insertSelective(AdvFlag record);

    List<AdvFlag> selectByExample(AdvFlagExample example);

    int updateByExampleSelective(@Param("record") AdvFlag record, @Param("example") AdvFlagExample example);

    int updateByExample(@Param("record") AdvFlag record, @Param("example") AdvFlagExample example);
}