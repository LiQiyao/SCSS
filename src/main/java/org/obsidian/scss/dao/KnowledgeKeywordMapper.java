package org.obsidian.scss.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.KnowledgeKeyword;
import org.obsidian.scss.entity.KnowledgeKeywordExample;

public interface KnowledgeKeywordMapper {
    long countByExample(KnowledgeKeywordExample example);

    int deleteByExample(KnowledgeKeywordExample example);

    int insert(KnowledgeKeyword record);

    int insertSelective(KnowledgeKeyword record);

    List<KnowledgeKeyword> selectByExample(KnowledgeKeywordExample example);

    int updateByExampleSelective(@Param("record") KnowledgeKeyword record, @Param("example") KnowledgeKeywordExample example);

    int updateByExample(@Param("record") KnowledgeKeyword record, @Param("example") KnowledgeKeywordExample example);
}