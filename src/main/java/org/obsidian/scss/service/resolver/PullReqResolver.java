package org.obsidian.scss.service.resolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.*;
import org.obsidian.scss.conversation.ClientWS;
import org.obsidian.scss.conversation.WebSocket;
import org.obsidian.scss.entity.Client;
import org.obsidian.scss.entity.CustomerService;
import org.obsidian.scss.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.websocket.Session;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by Lee on 2017/7/18.
 */
@Service
public class PullReqResolver implements ContentResolver{

    private Gson gson;

    @Autowired
    private ChatLogService chatLogService;

    @Autowired
    private CustomerServiceService customerServiceService;

    @Autowired
    private GroupQueue groupQueue;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private NotificationService notificationService;

    @Transactional
    public void resolve(String msgJson, WebSocket webSocket) {
        Session session = webSocket.getSession();
        Type objectType = new TypeToken<Message<PullReq>>(){}.getType();
        Message<PullReq> message = gson.fromJson(msgJson, objectType);
        PullReq pullReq = message.getContent();
        int serviceId = pullReq.getServiceId();
        CustomerService customerService = customerServiceService.selectCustomerServiceByServiceId(serviceId);
        int groupId = customerService.getGroupId();
        Client client = groupQueue.getClientFromQueue(groupId);
        int clientId = client.getClientId();
        int conversationId = conversationService.getLastIdByClientId(clientId);
        TransferSignal transferSignal = new TransferSignal(conversationId,client.getClientId(),chatLogService.getByClientId(clientId));
        try {
            session.getBasicRemote().sendText(gson.toJson(new Message<TransferSignal>(transferSignal)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        chatLogService.addWithConversationId(conversationId,serviceId,clientId,0, customerService.getAutoMessage(),new Date().getTime(),0);
        ServiceChat serviceChat = new ServiceChat(conversationId,clientId,0,customerService.getAutoMessage(),new Date().getTime());
        for (WebSocket ws : ClientWS.wsVector){
            try {
                ws.getSession().getBasicRemote().sendText(gson.toJson(new Message<ServiceChat>(serviceChat)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //将转接通知消息存入数据库
        notificationService.insertNotificationService(1,3,serviceId,"编号为" +clientId +"的客户接入到会话中");
    }
}
