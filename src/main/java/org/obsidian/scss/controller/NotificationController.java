package org.obsidian.scss.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.IdList;
import org.obsidian.scss.bean.NotificationAndTypeAndObjectType;
import org.obsidian.scss.bean.Show;
import org.obsidian.scss.entity.*;
import org.obsidian.scss.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
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

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;
/**
 * Created by hp on 2017/7/19.
 */
@Controller
@RequestMapping(value = "/")
public class NotificationController {
    @Autowired
    private NotificationObjectTypeService notificationObjectTypeService;
    @Autowired
    private NotificationTypeService notificationTypeService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private CustomerServiceService customerServiceService;
    @Autowired
    private ServiceGroupService serviceGroupService;
    
    
    @RequestMapping("notification")
    @ResponseBody
    public Show notification(){
        Show show = new Show();
        List<NotificationAndTypeAndObjectType>  notificationAndTypeAndObjectTypes = new ArrayList<NotificationAndTypeAndObjectType>();
        List<org.obsidian.scss.entity.Notification> notifications = notificationService.selectAllNotification();
        if (notifications!=null){
            for (int i = 0 ; i < notifications.size(); i++){
                NotificationAndTypeAndObjectType notificationAndTypeAndObjectType = new NotificationAndTypeAndObjectType();
                org.obsidian.scss.entity.Notification notification = notifications.get(i);
                notificationAndTypeAndObjectType.setNotification(notification);
                NotificationType  notificationType = notificationTypeService.selectNotificationType(notification.getNotId());
                notificationAndTypeAndObjectType.setNotificationType(notificationType);
                NotificationObjectType notificationObjectType = notificationObjectTypeService.selectNotificationObjectType(notification.getNotId());
                if (notificationObjectType.getName().equals("客服个人")){
                    notificationAndTypeAndObjectType.setType("客服个人");
                    CustomerService customerService = customerServiceService.selectCustomerServiceByServiceId(notification.getObjectId());
                    notificationAndTypeAndObjectType.setGetMessageObject(customerService);
                }else if(notificationObjectType.getName().equals("客服组")){
                    notificationAndTypeAndObjectType.setType("客服组");
                    notificationAndTypeAndObjectType.setGetMessageObject(serviceGroupService.selectGroupByGroupId(notification.getObjectId()));
                }else{
                    notificationAndTypeAndObjectType.setType("全体客服组");
                    notificationAndTypeAndObjectType.setGetMessageObject(new String("全体客服组"));
                }
                notificationAndTypeAndObjectTypes.add(notificationAndTypeAndObjectType);
            }
        }else{
            show.setStatus(0);
            show.setMessage("没有历史通知");
        }
        
        
        show.setData(notificationAndTypeAndObjectTypes);
        return show;
    }
    
    @RequestMapping("addNotification")
    @ResponseBody
    public Show addNotification(@RequestParam("content") String content,@RequestParam("ntId") int ntId,@RequestParam("objectId")int objectId,
                                @RequestParam("notId") int notId){
        Show show = new Show();
        int res =notificationService.insertNotificationService(ntId,notId,objectId,content);
        if (res == 0){
            show.setStatus(0);
            show.setMessage("添加失败");
        }else{
            show.setMessage("添加成功");
        }
        return  show;
    }
    
    @RequestMapping("deleteNotification")
    @ResponseBody
    public Show deleteNotification(@RequestParam("deleteList") String getJson){
        Gson gson = new Gson();
        List<IdList> idLists = gson.fromJson(getJson,new TypeToken<List<IdList>>(){}.getType());
        Show show = new Show();
        int fail  = 0 ;
        for (int i = 0 ; i < idLists.size() ; i++){
            int res = notificationService.deleteById(idLists.get(i).getId());
            if (res ==0){
                fail++;
            }
        }
        if (fail !=0){
            show.setStatus(0);
            show.setMessage("删除失败:"+(idLists.size()-fail)+"条");
        }
        return  null;
    }
    @RequestMapping(value = "/sendNotification", method = RequestMethod.POST)
    public void sendNotification(HttpServletRequest req) {
        Gson gson = new Gson();
        int ntId = Integer.parseInt(req.getParameter("ntId"));
        int notId = Integer.parseInt(req.getParameter("notId"));
        int objectId = Integer.parseInt(req.getParameter("objectId"));
        String content = req.getParameter("content");
        NotificationObjectType not = notificationObjectTypeService.selectNotificationObjectType(notId);
        NotificationType nt = notificationTypeService.selectNotificationType(ntId);
        Notification notification = new Notification(nt.getName(), content, new Date().getTime());
        if ("全体客服".equals(not.getName())) {
            for (WebSocket ws : ServiceWS.wsVector) {
                try {
                    ws.getSession().getBasicRemote().sendText(gson.toJson(new Message<Notification>(notification)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if ("客服组".equals(not.getName())) {
            List<CustomerService> list = customerServiceService.selectCustomerServiceByGroup(objectId);
            for (WebSocket ws : ServiceWS.wsVector) {
                for (CustomerService cs : list) {
                    if (ws.getServiceId() == cs.getServiceId()) {
                        try {
                            ws.getSession().getBasicRemote().sendText(gson.toJson(new Message<Notification>(notification)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else if ("客服个人".equals(not.getName())) {
            for (WebSocket ws : ServiceWS.wsVector) {
                if (ws.getServiceId() == objectId) {
                    try {
                        ws.getSession().getBasicRemote().sendText(gson.toJson(new Message<Notification>(notification)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        //存储入数据库
        notificationService.insertNotificationService(ntId, notId, objectId, content);
    }
    
    @RequestMapping("searchNot")
    @ResponseBody
    public Show searchGroup(@RequestParam(value = "ntId",defaultValue = "0") int ntId,@RequestParam(value = "notId",defaultValue = "0") int notId){
        Show show = new Show();
        List<NotificationAndTypeAndObjectType>  notificationAndTypeAndObjectTypes = new ArrayList<NotificationAndTypeAndObjectType>();
        List<org.obsidian.scss.entity.Notification> notifications = notificationService.searchByType(ntId,notId);
        if (notifications!=null){
            for (int i = 0 ; i < notifications.size(); i++){
                NotificationAndTypeAndObjectType notificationAndTypeAndObjectType = new NotificationAndTypeAndObjectType();
                org.obsidian.scss.entity.Notification notification = notifications.get(i);
                notificationAndTypeAndObjectType.setNotification(notification);
                NotificationType  notificationType = notificationTypeService.selectNotificationType(notification.getNotId());
                notificationAndTypeAndObjectType.setNotificationType(notificationType);
                NotificationObjectType notificationObjectType = notificationObjectTypeService.selectNotificationObjectType(notification.getNotId());
                if (notificationObjectType.getName().equals("客服个人")){
                    notificationAndTypeAndObjectType.setType("客服个人");
                    CustomerService customerService = customerServiceService.selectCustomerServiceByServiceId(notification.getObjectId());
                    notificationAndTypeAndObjectType.setGetMessageObject(customerService);
                }else if(notificationObjectType.getName().equals("客服组")){
                    notificationAndTypeAndObjectType.setType("客服组");
                    notificationAndTypeAndObjectType.setGetMessageObject(serviceGroupService.selectGroupByGroupId(notification.getObjectId()));
                }else{
                    notificationAndTypeAndObjectType.setType("全体客服组");
                    notificationAndTypeAndObjectType.setGetMessageObject(new String("全体客服组"));
                }
                notificationAndTypeAndObjectTypes.add(notificationAndTypeAndObjectType);
            }
        }else{
            show.setStatus(0);
            show.setMessage("没有历史通知");
        }


        show.setData(notificationAndTypeAndObjectTypes);
        return show;
    }
}
