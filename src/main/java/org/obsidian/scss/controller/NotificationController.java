package org.obsidian.scss.controller;

import com.google.gson.Gson;
import org.obsidian.scss.bean.Message;
import org.obsidian.scss.bean.Notification;
import org.obsidian.scss.conversation.ServiceWS;
import org.obsidian.scss.conversation.WebSocket;
import org.obsidian.scss.entity.CustomerService;
import org.obsidian.scss.entity.NotificationObjectType;
import org.obsidian.scss.entity.NotificationType;
import org.obsidian.scss.service.CustomerServiceService;
import org.obsidian.scss.service.NotificationObjectTypeService;
import org.obsidian.scss.service.NotificationService;
import org.obsidian.scss.service.NotificationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Lee on 2017/7/19.
 */
@RequestMapping(value = "/")
public class NotificationController {

    @Autowired
    private NotificationObjectTypeService notificationObjectTypeService;

    @Autowired
    private NotificationTypeService notificationTypeService;

    @Autowired
    private CustomerServiceService customerServiceService;

    @Autowired
    private NotificationService notificationService;

    @RequestMapping(value = "/sendNotification", method = RequestMethod.POST)
    public void sendNotification(HttpServletRequest req){
        Gson gson = new Gson();
        int ntId = Integer.parseInt(req.getParameter("ntId"));
        int notId = Integer.parseInt(req.getParameter("notId"));
        int objectId = Integer.parseInt(req.getParameter("objectId"));
        String content = req.getParameter("content");
        NotificationObjectType not = notificationObjectTypeService.selectNotificationObjectType(notId);
        NotificationType nt = notificationTypeService.selectNotificationType(ntId);
        Notification notification = new Notification(nt.getName(),content,new Date().getTime());
        if ("全体客服".equals(not.getName())){
            for (WebSocket ws : ServiceWS.wsVector){
                try {
                    ws.getSession().getBasicRemote().sendText(gson.toJson(new Message<Notification>(notification)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if ("客服组".equals(not.getName())){
            List<CustomerService> list = customerServiceService.selectCustomerServiceByGroup(objectId);
            for (WebSocket ws : ServiceWS.wsVector){
                for (CustomerService cs : list){
                    if (ws.getServiceId() == cs.getServiceId()){
                        try {
                            ws.getSession().getBasicRemote().sendText(gson.toJson(new Message<Notification>(notification)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else if ("客服个人".equals(not.getName())){
            for (WebSocket ws : ServiceWS.wsVector){
                if (ws.getServiceId() == objectId){
                    try {
                        ws.getSession().getBasicRemote().sendText(gson.toJson(new Message<Notification>(notification)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        //存储入数据库
        notificationService.insertNotificationService(ntId,notId,objectId,content);
    }
}
