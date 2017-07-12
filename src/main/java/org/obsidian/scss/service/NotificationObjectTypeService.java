package org.obsidian.scss.service;

import org.obsidian.scss.entity.NotificationObjectType;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */
public interface NotificationObjectTypeService {
    int insertNotificationObjectType(String name);

    int deleteNotificationObjectType(int notId);

    int updateNotificationObjectType(int notId,String name);

    NotificationObjectType selectNotificationObjectType(int notId);

    List<NotificationObjectType> selectAllNotificationObjectType();

}
