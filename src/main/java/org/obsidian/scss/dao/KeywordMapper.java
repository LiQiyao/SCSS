package org.obsidian.scss.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.Keyword;
import org.obsidian.scss.entity.KeywordExample;

public interface KeywordMapper {
    long countByExample(KeywordExample example);

    int deleteByExample(KeywordExample example);

    int insert(Keyword record);

    int insertSelective(Keyword record);

    List<Keyword> selectByExample(KeywordExample example);

    int updateByExampleSelective(@Param("record") Keyword record, @Param("example") KeywordExample example);

    int updateByExample(@Param("record") Keyword record, @Param("example") KeywordExample example);

    int selectLastId();

    Keyword selectByValue(String value);

    List<Keyword> selectAll();

    int deleteById(int keywordId);

    Keyword selectById(int keywordId);
}