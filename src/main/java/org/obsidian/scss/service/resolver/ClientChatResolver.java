package org.obsidian.scss.service.resolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.*;
import org.obsidian.scss.conversation.ServiceWS;
import org.obsidian.scss.conversation.WebSocket;
import org.obsidian.scss.dao.ChatLogMapper;
import org.obsidian.scss.entity.Client;
import org.obsidian.scss.entity.Conversation;
import org.obsidian.scss.entity.Knowledge;
import org.obsidian.scss.service.ChatLogService;
import org.obsidian.scss.service.ClientService;
import org.obsidian.scss.service.ConversationService;
import org.obsidian.scss.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void resolve(String msgJson, WebSocket webSocket) {
        System.out.println("!!1" +msgJson + webSocket.getServiceId() +webSocket.getClientId());
        Session session = webSocket.getSession();
        System.out.println(chatLogService.getByClientId(1));
        Gson gson = new Gson();
        Type objectType = new TypeToken<Message<ClientChat>>(){}.getType();
        Message<ClientChat> message = gson.fromJson(msgJson, objectType);
        message.getContent().setTime(new Date().getTime());
        ClientChat clientChat = message.getContent();
        int contentType = clientChat.getContentType();
        System.out.println("!!2");
        if (contentType == 0){//如果该消息是文字消息
            System.out.println("!!3");
            if ("转接到人工客服".equals(clientChat.getContent())){
                this.transfer(webSocket, clientChat);
            }
            chatLogService.add(clientChat.getClientId(),webSocket.getServiceId(),0,clientChat.getContent(), new Date().getTime(),1);
            if (webSocket.getServiceId() == 0){//如果该消息是发送给机器人的
                System.out.println("!!4");
                Message<RobotChat> res = knowledgeService.getRobotChat(clientChat.getContent());
                chatLogService.add(webSocket.getServiceId(), clientChat.getClientId(),0,res.getContent().getAnswer(),new Date().getTime(),0);
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
        conversationService.endConversation(clientChat.getConversationId(),new Date().getTime(), null);
    }
}
