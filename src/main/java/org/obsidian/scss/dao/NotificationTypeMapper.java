package org.obsidian.scss.dao;

import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.NotificationType;
import org.obsidian.scss.entity.NotificationTypeExample;

import java.util.List;

public interface NotificationTypeMapper {
    int updateNotificationType(@Param("ntId")int ntId,@Param("name")String name);

    long countByExample(NotificationTypeExample example);

    int deleteByExample(NotificationTypeExample example);

    int insert(NotificationType record);

    int insertSelective(NotificationType record);

    List<NotificationType> selectByExample(NotificationTypeExample example);

    int updateByExampleSelective(@Param("record") NotificationType record, @Param("example") NotificationTypeExample example);

    int updateByExample(@Param("record") NotificationType record, @Param("example") NotificationTypeExample example);
}