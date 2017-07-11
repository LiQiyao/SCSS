package org.obsidian.scss.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.Knowledge;
import org.obsidian.scss.entity.KnowledgeExample;

public interface KnowledgeMapper {
    long countByExample(KnowledgeExample example);

    int deleteByExample(KnowledgeExample example);

    int insert(Knowledge record);

    int insertSelective(Knowledge record);

    List<Knowledge> selectByExample(KnowledgeExample example);

    int updateByExampleSelective(@Param("record") Knowledge record, @Param("example") KnowledgeExample example);

    int updateByExample(@Param("record") Knowledge record, @Param("example") KnowledgeExample example);

    int selectLastId();

    List<Knowledge> selectByKeywordId(int keywordId);

    int deleteById(int knowledgeId);

    Knowledge selectByKnowledgeId(int knowledgeId);
}