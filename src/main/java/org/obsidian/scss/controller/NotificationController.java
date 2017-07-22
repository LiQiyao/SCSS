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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/**
 * Created by hp on 2017/7/19.
 */
@Controller
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
    public String notification(Model model){
        List<NotificationAndTypeAndObjectType>  notificationAndTypeAndObjectTypes = new ArrayList<NotificationAndTypeAndObjectType>();
        List<Notification> notifications = notificationService.selectAllNotification();
        for (int i = 0 ; i < notifications.size(); i++){
            NotificationAndTypeAndObjectType notificationAndTypeAndObjectType = new NotificationAndTypeAndObjectType();
            Notification notification = notifications.get(i);
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
        model.addAttribute("notification",notificationAndTypeAndObjectTypes);
        return "notification";
    }
    
    @RequestMapping("addNotification")
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
    public Show deleteNotification(@RequestParam("getJson") String getJson){
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
    
}
