package org.obsidian.scss.service;

import org.obsidian.scss.entity.NotificationType;

/**
 * Created by Administrator on 2017/7/11.
 */
public interface NotificationTypeService {
    int insertNotificationType(String name);

    int deleteNotificationType(int ntId);

    int updateNotificationType(int ntId,String name);

    NotificationType selectNotificationType(int ntId);
}
