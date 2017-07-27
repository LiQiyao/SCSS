package org.obsidian.scss.service.resolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.*;
import org.obsidian.scss.conversation.ClientWS;
import org.obsidian.scss.conversation.ServiceWS;
import org.obsidian.scss.conversation.WebSocket;
import org.obsidian.scss.dao.ConversationMapper;
import org.obsidian.scss.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.websocket.Session;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by Lee on 2017/7/17.
 */
@Service
public class TransferReqResolver implements ContentResolver {

    private Gson gson = new Gson();

    @Autowired
    private GroupQueue groupQueue;

    @Autowired
    private CustomerServiceService customerServiceService;

    @Autowired
    private ChatLogService chatLogService;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ConversationMapper conversationMapper;

    @Transactional
    public void resolve(String msgJson, WebSocket webSocket) {
        Session session = webSocket.getSession();
        Type objectType = new TypeToken<Message<TransferReq>>(){}.getType();
        Message<TransferReq> message = gson.fromJson(msgJson, objectType);
        TransferReq transferReq = message.getContent();
        int transferType = transferReq.getTransferType();
        int targetId = transferReq.getTargetId();
        int clientId = transferReq.getClientId();
        int conversationId = transferReq.getConversationId();
        if (transferType == 0){//转接给全公司任意一个较为空闲的客服
            groupQueue.joinLeastClientQueue(clientId);
        } else if (transferType == 1){
            groupQueue.joinQueueByGroupId(targetId, clientId);
        } else {
            Message<ServiceChat> message1 =
                    new Message<ServiceChat>(new ServiceChat(conversationId, clientId,0, customerServiceService.selectCustomerServiceByServiceId(targetId).getAutoMessage(),new Date().getTime()));
            for (WebSocket ws : ServiceWS.wsVector){//找到目标客服并发送转接信号
                if (ws.getServiceId() == targetId){
                    TransferSignal transferSignal = new TransferSignal(conversationId,clientId, chatLogService.getByClientId(clientId));
                    try {
                        ws.getSession().getBasicRemote().sendText(gson.toJson(new Message<TransferSignal>(transferSignal)));
                        ws.getSession().getBasicRemote().sendText(gson.toJson(message1));//给客服发送自动回话
                        //更新客服状态
                        int conversationCount = conversationMapper.selectNotFinishByServiceId(targetId);
                        ServiceStatus serviceStatus = new ServiceStatus();
                        serviceStatus.setConversationCount(conversationCount + 1);
                        ws.getSession().getBasicRemote().sendText(gson.toJson(new Message<ServiceStatus>(serviceStatus)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            for (WebSocket ws : ClientWS.wsVector){//找到客户并发送自动回话语句,并重置会话所指向的客服
                if (ws.getClientId() == clientId){
                    ws.setServiceId(targetId);

                    conversationService.resetServiceId(targetId, conversationId);
                    chatLogService.addWithConversationId(conversationId,targetId,clientId,0,customerServiceService.selectCustomerServiceByServiceId(targetId).getAutoMessage(),new Date().getTime(),0);
                    try {
                        ws.getSession().getBasicRemote().sendText(gson.toJson(message1));//给客户发送自动回话语句
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            //将转接通知消息存入数据库
            notificationService.insertNotificationService(1,3,targetId,"编号为" +clientId +"的客户接入到会话中");


        }
    }
}
