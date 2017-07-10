package org.obsidian.scss.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.NotificationObjectType;
import org.obsidian.scss.entity.NotificationObjectTypeExample;

public interface NotificationObjectTypeMapper {
    long countByExample(NotificationObjectTypeExample example);

    int deleteByExample(NotificationObjectTypeExample example);

    int insert(NotificationObjectType record);

    int insertSelective(NotificationObjectType record);

    List<NotificationObjectType> selectByExample(NotificationObjectTypeExample example);

    int updateByExampleSelective(@Param("record") NotificationObjectType record, @Param("example") NotificationObjectTypeExample example);

    int updateByExample(@Param("record") NotificationObjectType record, @Param("example") NotificationObjectTypeExample example);
}