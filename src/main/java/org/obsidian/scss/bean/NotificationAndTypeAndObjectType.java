package org.obsidian.scss.bean;

import org.obsidian.scss.entity.Notification;
import org.obsidian.scss.entity.NotificationObjectType;
import org.obsidian.scss.entity.NotificationType;

/**
 * Created by hp on 2017/7/19.
 */
public class NotificationAndTypeAndObjectType {
    public String type;
    
    public Notification notification;
    
    public NotificationType notificationType;
    
    public NotificationObjectType notificationObjectType;
    
    public Object getMessageObject;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getGetMessageObject() {
        return getMessageObject;
    }

    public void setGetMessageObject(Object getMessageObject) {
        this.getMessageObject = getMessageObject;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public NotificationObjectType getNotificationObjectType() {
        return notificationObjectType;
    }

    public void setNotificationObjectType(NotificationObjectType notificationObjectType) {
        this.notificationObjectType = notificationObjectType;
    }
}
    
