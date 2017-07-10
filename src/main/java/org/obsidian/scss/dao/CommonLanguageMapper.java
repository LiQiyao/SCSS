package org.obsidian.scss.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.CommonLanguage;
import org.obsidian.scss.entity.CommonLanguageExample;

public interface CommonLanguageMapper {
    long countByExample(CommonLanguageExample example);

    int deleteByExample(CommonLanguageExample example);

    int insert(CommonLanguage record);

    int insertSelective(CommonLanguage record);

    List<CommonLanguage> selectByExample(CommonLanguageExample example);

    int updateByExampleSelective(@Param("record") CommonLanguage record, @Param("example") CommonLanguageExample example);

    int updateByExample(@Param("record") CommonLanguage record, @Param("example") CommonLanguageExample example);
}