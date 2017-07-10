package org.obsidian.scss.dao;

import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.CommonLanguage;
import org.obsidian.scss.entity.CommonLanguageExample;

import java.util.List;

public interface CommonLanguageMapper {
    long countByExample(CommonLanguageExample example);

    int deleteByExample(CommonLanguageExample example);

    int insert(CommonLanguage record);

    int insertSelective(CommonLanguage record);

    List<CommonLanguage> selectByExample(CommonLanguageExample example);

    int updateByExampleSelective(@Param("record") CommonLanguage record, @Param("example") CommonLanguageExample example);

    int updateByExample(@Param("record") CommonLanguage record, @Param("example") CommonLanguageExample example);

    int deleteCommonLanguage(int commonLanguageId);
    
    int addCommonLanguageFrequency(int commonLanguageId);

    int updateCommonLanguage(CommonLanguage commonLanguage);
}