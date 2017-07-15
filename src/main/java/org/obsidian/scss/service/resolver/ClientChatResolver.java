package org.obsidian.scss.service.resolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.ClientChat;
import org.obsidian.scss.bean.KnowledgeList;
import org.obsidian.scss.bean.Message;
import org.obsidian.scss.bean.RobotChat;
import org.obsidian.scss.conversation.WebSocket;
import org.obsidian.scss.dao.ChatLogMapper;
import org.obsidian.scss.entity.Client;
import org.obsidian.scss.service.ChatLogService;
import org.obsidian.scss.service.ClientService;
import org.obsidian.scss.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by Lee on 2017/7/12.
 */
@Service
public class ClientChatResolver implements ContentResolver {

    @Autowired
    private ChatLogService chatLogService;


    @Autowired
    private KnowledgeService knowledgeService;

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
        if (contentType == 0){
            System.out.println("!!3");
            chatLogService.add(clientChat.getClientId(),webSocket.getServiceId(),0,clientChat.getContent(), new Date().getTime(),1);
            if (webSocket.getServiceId() == 0){
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
                //TODO 添加聊天记录到数据库,根据编号发送给Vector中的某个人

            }

        }
    }
}
