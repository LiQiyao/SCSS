package org.obsidian.scss.service.resolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.*;
import org.obsidian.scss.conversation.ServiceWS;
import org.obsidian.scss.conversation.WebSocket;
import org.obsidian.scss.dao.ChatLogMapper;
import org.obsidian.scss.entity.*;
import org.obsidian.scss.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.websocket.Session;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * Created by Lee on 2017/7/12.
 */
@Service
public class ClientChatResolver implements ContentResolver {

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
        if ("转接到人工客服".equals(clientChat.getContent())){
            this.transfer(webSocket, clientChat);
            return;
        }
        if (contentType == 0){//如果该消息是文字消息
            System.out.println("!!3");

            chatLogService.addWithConversationId(clientChat.getConversationId(),clientChat.getClientId(),webSocket.getServiceId(),0,clientChat.getContent(), new Date().getTime(),1);
            if (webSocket.getServiceId() == 0){//如果该消息是发送给机器人的
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
        conversationService.endConversation(clientChat.getConversationId(),new Date().getTime(), null);
        System.out.println("8");
        CustomerService target = conversationService.getLastChatServiceId(clientChat.getClientId());
        System.out.println("9");
        //找之前聊过天的客服
        WebSocket targetWS = null;
        for (int i = 0; i < ServiceWS.wsVector.size(); i++){
            if (ServiceWS.wsVector.get(i).getServiceId() == target.getServiceId()){
                targetWS = ServiceWS.wsVector.get(i);
            }
        }
        if (targetWS != null){//如果存在之前聊过天的客服，则接入到该客服
            Message<ServiceChat> message =
                    new Message<ServiceChat>(new ServiceChat(clientChat.getConversationId(),clientChat.getClientId(),0, target.getAutoMessage(),new Date().getTime()));
            try {
                System.out.println("11");
                webSocket.setServiceId(target.getServiceId());
                webSocket.getSession().getBasicRemote().sendText(gson.toJson(message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            List<ChatLog> chatLogList = chatLogService.getClientSayByConversationId(clientChat.getConversationId());
            String content = "";
            for (ChatLog c : chatLogList){
                content += c.getContent();
            }
            int serviceGroupId = groupWordService.getServiceGroupIdByContent(content);
            if (serviceGroupId != 0){
                groupQueue.joinQueueBygroupId(serviceGroupId, clientChat.getClientId());
            } else {
                groupQueue.joinLeastClientQueue(clientChat.getClientId());
            }
        }
    }
}
