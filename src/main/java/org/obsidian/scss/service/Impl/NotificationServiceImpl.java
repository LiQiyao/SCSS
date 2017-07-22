package org.obsidian.scss.service.Impl;

import org.obsidian.scss.dao.NotificationMapper;
import org.obsidian.scss.entity.Notification;
import org.obsidian.scss.entity.NotificationExample;
import org.obsidian.scss.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Transactional
    public int insertNotificationService(int ntId,int notId,int objectId, String content) {
        Notification notification = new Notification();
        notification.setNtId(ntId);
        notification.setNotId(notId);
        notification.setObjectId(objectId);
        notification.setContent(content);
        notification.setTime(new java.util.Date().getTime());
        return notificationMapper.insertSelective(notification);
    }

    @Transactional
    public List<Notification> selectAllNotification() {
        NotificationExample example = new NotificationExample();
        List<Notification> list = notificationMapper.selectByExample(example);
        if(list.size() == 0 || list == null){
            return null;
        }
        return list;
    }

    public int deleteById(int id) {
        NotificationExample example = new NotificationExample();
        NotificationExample.Criteria criteria = example.createCriteria();
        criteria.andNotificationIdEqualTo(id);
        return notificationMapper.deleteByExample(example);
    }
}
