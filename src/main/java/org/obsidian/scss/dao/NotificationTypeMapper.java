package org.obsidian.scss.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.NotificationType;
import org.obsidian.scss.entity.NotificationTypeExample;

public interface NotificationTypeMapper {
    long countByExample(NotificationTypeExample example);

    int deleteByExample(NotificationTypeExample example);

    int insert(NotificationType record);

    int insertSelective(NotificationType record);

    List<NotificationType> selectByExample(NotificationTypeExample example);

    int updateByExampleSelective(@Param("record") NotificationType record, @Param("example") NotificationTypeExample example);

    int updateByExample(@Param("record") NotificationType record, @Param("example") NotificationTypeExample example);
}