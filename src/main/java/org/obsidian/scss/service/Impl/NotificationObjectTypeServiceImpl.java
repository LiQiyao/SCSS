package org.obsidian.scss.service.Impl;

import org.obsidian.scss.dao.NotificationObjectTypeMapper;
import org.obsidian.scss.entity.NotificationObjectType;
import org.obsidian.scss.entity.NotificationObjectTypeExample;
import org.obsidian.scss.service.NotificationObjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */
@Service
public class NotificationObjectTypeServiceImpl implements NotificationObjectTypeService {

    @Autowired
    private NotificationObjectTypeMapper notificationObjectTypeMapper;

    /**
     * 新增通知对象类型
     * @param name
     * @return
     */
    @Transactional
    public int insertNotificationObjectType(String name) {
        NotificationObjectType notificationObjectType = new NotificationObjectType();
        notificationObjectType.setName(name);
        return notificationObjectTypeMapper.insertSelective(notificationObjectType);
    }

    /**
     * 删除通知对象类型
     * @param notId
     * @return
     */
    @Transactional
    public int deleteNotificationObjectType(int notId) {
        NotificationObjectTypeExample example = new NotificationObjectTypeExample();
        example.or().andNotIdEqualTo(notId);
        return notificationObjectTypeMapper.deleteByExample(example);
    }

    /**
     * 更新通知对象类型
     * @param notId
     * @param name
     * @return
     */
    @Transactional
    public int updateNotificationObjectType(int notId, String name) {
        return notificationObjectTypeMapper.updateNotificationObjectType(notId,name);
    }

    /**
     * 查询通知对象类型
     * @param notId
     * @return
     */
    @Transactional
    public NotificationObjectType selectNotificationObjectType(int notId) {
        NotificationObjectTypeExample example = new NotificationObjectTypeExample();
        example.or().andNotIdEqualTo(notId);
        List<NotificationObjectType> list = notificationObjectTypeMapper.selectByExample(example);
        if(list.size() == 0 || list == null){
            return null;
        }
        return list.get(0);
    }
}
