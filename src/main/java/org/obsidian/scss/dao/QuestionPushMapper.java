package org.obsidian.scss.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.QuestionPush;
import org.obsidian.scss.entity.QuestionPushExample;

public interface QuestionPushMapper {
    long countByExample(QuestionPushExample example);

    int deleteByExample(QuestionPushExample example);

    int insert(QuestionPush record);

    int insertSelective(QuestionPush record);

    List<QuestionPush> selectByExample(QuestionPushExample example);

    int updateByExampleSelective(@Param("record") QuestionPush record, @Param("example") QuestionPushExample example);

    int updateByExample(@Param("record") QuestionPush record, @Param("example") QuestionPushExample example);
}