package org.obsidian.scss.service.resolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.*;
import org.obsidian.scss.conversation.ServiceWS;
import org.obsidian.scss.conversation.WebSocket;
import org.obsidian.scss.dao.ConversationMapper;
import org.obsidian.scss.entity.ChatLog;
import org.obsidian.scss.entity.CustomerService;
import org.obsidian.scss.entity.Flag;
import org.obsidian.scss.entity.Knowledge;
import org.obsidian.scss.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.websocket.Session;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Lee on 2017/7/12.
 */
@Service
public class ClientChatResolver implements ContentResolver {

    @Autowired
    private ClientService clientService;

    @Autowired
    private FlagService flagService;

    @Autowired
    private ChatLogService chatLogService;

    @Autowired
    private KnowledgeService knowledgeService;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private GroupWordService groupWordService;

    @Autowired
    private GroupQueue groupQueue;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ConversationMapper conversationMapper;

    private Gson gson = new Gson();

    @Transactional
    public void resolve(String msgJson, WebSocket webSocket) {
        System.out.println("!!1" +msgJson + webSocket.getServiceId() +webSocket.getClientId());
        Session session = webSocket.getSession();
        System.out.println(chatLogService.getByClientId(1));
        Type objectType = new TypeToken<Message<ClientChat>>(){}.getType();
        Message<ClientChat> message = gson.fromJson(msgJson, objectType);
        message.getContent().setTime(new Date().getTime());
        ClientChat clientChat = message.getContent();
        int contentType = clientChat.getContentType();
        System.out.println("!!2");

        if (contentType == 0){//如果该消息是文字消息
            System.out.println("!!3");
            chatLogService.addWithConversationId(clientChat.getConversationId(),clientChat.getClientId(),webSocket.getServiceId(),0,clientChat.getContent(), new Date().getTime(),1);
            if (webSocket.getServiceId() == 0){
                //如果该消息是发送给机器人的
                if ("转接到人工客服".equals(clientChat.getContent())){
                    this.transfer(webSocket, clientChat);
                    return;
                }
                //机器人直接给客户打标签
                this.takeFlags(webSocket,clientChat.getContent());

                System.out.println("!!4");
                Message<RobotChat> res = knowledgeService.getRobotChat(clientChat.getContent());
                chatLogService.addWithConversationId(clientChat.getConversationId(),webSocket.getServiceId(), clientChat.getClientId(),0,res.getContent().getAnswer(),new Date().getTime(),0);
                try {
                    System.out.println("!!5");
                    session.getBasicRemote().sendText(gson.toJson(message));
                    session.getBasicRemote().sendText(gson.toJson(res));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                //如果该消息是发送给客服人员的

                //给客服人员推荐标签
                this.recommandTags(webSocket,clientChat.getConversationId(),clientChat.getContent());

                List<Knowledge> knowledgeList = knowledgeService.getKnowledgeByContent(clientChat.getContent());
                Message<RecommandKnowledges> res = new Message<RecommandKnowledges>(new RecommandKnowledges(clientChat.getConversationId(),knowledgeList));
                try {
                    session.getBasicRemote().sendText(gson.toJson(message));
                    for (WebSocket sws : ServiceWS.wsVector){
                        if (sws.getServiceId() == webSocket.getServiceId()){
                            sws.getSession().getBasicRemote().sendText(gson.toJson(message));
                            sws.getSession().getBasicRemote().sendText(gson.toJson(res));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void transfer(WebSocket webSocket, ClientChat clientChat){
        System.out.println("7");
        System.out.println("8");
        CustomerService target = conversationService.getLastChatServiceId(clientChat.getClientId());
        System.out.println("找到客服：" + target);
        System.out.println("9");
        //找之前聊过天的客服
        WebSocket targetWS = null;
        if (target != null){
            for (int i = 0; i < ServiceWS.wsVector.size(); i++){
                System.out.println("cnt1");
                if (ServiceWS.wsVector.get(i).getServiceId() == target.getServiceId()){
                    targetWS = ServiceWS.wsVector.get(i);
                }
            }
        }
        if (targetWS != null){//如果存在之前聊过天的客服，则接入到该客服
            Message<ServiceChat> message =
                    new Message<ServiceChat>(new ServiceChat(clientChat.getConversationId(),clientChat.getClientId(),0, target.getAutoMessage(),new Date().getTime()));
            try {
                System.out.println("11");
                webSocket.setServiceId(target.getServiceId());
                //将自动发送的消息添加入聊天记录并且存入数据库
                chatLogService.addWithConversationId(clientChat.getConversationId(),target.getServiceId(),clientChat.getClientId(),0, target.getAutoMessage(),new Date().getTime(),0);
                webSocket.getSession().getBasicRemote().sendText(gson.toJson(message));
                conversationService.resetServiceId(target.getServiceId(), clientChat.getConversationId());
                TransferSignal transferSignal = new TransferSignal(clientChat.getConversationId(),clientChat.getClientId(),chatLogService.getByClientId(clientChat.getClientId()));
                Message<TransferSignal> res = new Message<TransferSignal>(transferSignal);
                targetWS.getSession().getBasicRemote().sendText(gson.toJson(res));
                //将转接通知消息存入数据库
                notificationService.insertNotificationService(1,3,target.getServiceId(),"编号为" +clientChat.getClientId() +"的客户接入到会话中");
                //更新客服状态
                ServiceStatus serviceStatus = new ServiceStatus();
                serviceStatus.setConversationCount(conversationMapper.selectNotFinishByServiceId(target.getServiceId()));
                targetWS.getSession().getBasicRemote().sendText(gson.toJson(new Message<ServiceStatus>(serviceStatus)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            List<ChatLog> chatLogList = chatLogService.getClientSayByConversationId(clientChat.getConversationId());
            String content = "";
            for (ChatLog c : chatLogList){
                content += c.getContent();
            }
            System.out.println("!!!!!!!!!");
            int serviceGroupId = groupWordService.getServiceGroupIdByContent(content);
            System.out.println(serviceGroupId);
            if (serviceGroupId != 0){
                groupQueue.joinQueueByGroupId(serviceGroupId, clientChat.getClientId());
            } else {System.out.println(clientChat);
                groupQueue.joinLeastClientQueue(clientChat.getClientId());
            }
        }
    }

    private void takeFlags(WebSocket webSocket,String content){
        List<Flag> list = flagService.getFlagByContent(content);
        if(list != null && list.size() > 0){
            for(int i=0;i<list.size();i++){
                clientService.addFlag(webSocket.getClientId(),list.get(i).getName());
            }
        }
    }

    private void recommandTags(WebSocket webSocket,int conversationId,String content){
        List<Flag> list = flagService.getFlagByContent(content);
        List<String> tagList = new ArrayList<String>();
        if(list != null && list.size() > 0){
            for(int i=0;i<list.size();i++){
                tagList.add(list.get(i).getName());
            }
        }
        RecommandTags recommandTags = new RecommandTags(conversationId,tagList);
        Message<RecommandTags> res = new Message<RecommandTags>(recommandTags);
        try {
            for (WebSocket sws : ServiceWS.wsVector){
                if (sws.getServiceId() == webSocket.getServiceId()){
                    sws.getSession().getBasicRemote().sendText(gson.toJson(res));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
