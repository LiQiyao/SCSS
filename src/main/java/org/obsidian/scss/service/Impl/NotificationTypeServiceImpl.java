package org.obsidian.scss.service.Impl;

import org.obsidian.scss.dao.NotificationTypeMapper;
import org.obsidian.scss.entity.NotificationType;
import org.obsidian.scss.entity.NotificationTypeExample;
import org.obsidian.scss.service.NotificationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */
@Service
public class NotificationTypeServiceImpl implements NotificationTypeService {

    @Autowired
    private NotificationTypeMapper notificationTypeMapper;

    /**
     * 新增通知类型
     * @param name
     * @return
     */
    @Transactional
    public int insertNotificationType(String name) {
        NotificationType notificationType = new NotificationType();
        notificationType.setName(name);
        return notificationTypeMapper.insertSelective(notificationType);
    }

    /**
     * 删除通知类型
     * @param ntId
     * @return
     */
    @Transactional
    public int deleteNotificationType(int ntId) {
        NotificationTypeExample example = new NotificationTypeExample();
        example.or().andNtIdEqualTo(ntId);
        return notificationTypeMapper.deleteByExample(example);
    }

    /**
     * 更新通知类型
     * @param ntId
     * @param name
     * @return
     */
    @Transactional
    public int updateNotificationType(int ntId, String name) {
        return notificationTypeMapper.updateNotificationType(ntId,name);
    }

    /**
     * 查询通知类型
     * @param ntId
     * @return
     */
    @Transactional
    public NotificationType selectNotificationType(int ntId) {
        NotificationTypeExample example = new NotificationTypeExample();
        example.or().andNtIdEqualTo(ntId);
        List<NotificationType> list = notificationTypeMapper.selectByExample(example);
        if(list.size() == 0 || list == null){
            return null;
        }
        return list.get(0);
    }
}
