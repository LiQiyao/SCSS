package org.obsidian.scss.dao;

import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.NotificationObjectType;
import org.obsidian.scss.entity.NotificationObjectTypeExample;

import java.util.List;

public interface NotificationObjectTypeMapper {
    int updateNotificationObjectType(@Param("notId") int notId,@Param("name") String name);

    long countByExample(NotificationObjectTypeExample example);

    int deleteByExample(NotificationObjectTypeExample example);

    int insert(NotificationObjectType record);

    int insertSelective(NotificationObjectType record);

    List<NotificationObjectType> selectByExample(NotificationObjectTypeExample example);

    int updateByExampleSelective(@Param("record") NotificationObjectType record, @Param("example") NotificationObjectTypeExample example);

    int updateByExample(@Param("record") NotificationObjectType record, @Param("example") NotificationObjectTypeExample example);
}