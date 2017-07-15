package org.obsidian.scss.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.GroupWord;
import org.obsidian.scss.entity.GroupWordExample;

public interface GroupWordMapper {
    long countByExample(GroupWordExample example);

    int deleteByExample(GroupWordExample example);

    int insert(GroupWord record);

    int insertSelective(GroupWord record);

    List<GroupWord> selectByExample(GroupWordExample example);

    int updateByExampleSelective(@Param("record") GroupWord record, @Param("example") GroupWordExample example);

    int updateByExample(@Param("record") GroupWord record, @Param("example") GroupWordExample example);

    List<GroupWord> selectAll();
}