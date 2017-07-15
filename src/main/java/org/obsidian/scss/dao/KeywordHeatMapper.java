package org.obsidian.scss.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.KeywordHeat;
import org.obsidian.scss.entity.KeywordHeatExample;

public interface KeywordHeatMapper {
    long countByExample(KeywordHeatExample example);

    int deleteByExample(KeywordHeatExample example);

    int insert(KeywordHeat record);

    int insertSelective(KeywordHeat record);

    List<KeywordHeat> selectByExample(KeywordHeatExample example);

    int updateByExampleSelective(@Param("record") KeywordHeat record, @Param("example") KeywordHeatExample example);

    int updateByExample(@Param("record") KeywordHeat record, @Param("example") KeywordHeatExample example);
}