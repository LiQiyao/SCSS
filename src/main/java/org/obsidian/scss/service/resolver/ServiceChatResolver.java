package org.obsidian.scss.service.resolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.ClientChat;
import org.obsidian.scss.bean.Message;
import org.obsidian.scss.bean.ServiceChat;
import org.obsidian.scss.conversation.ClientWS;
import org.obsidian.scss.conversation.WebSocket;
import org.obsidian.scss.service.ChatLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.websocket.Session;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by Lee on 2017/7/15.
 */
@Service
public class ServiceChatResolver implements ContentResolver{

    @Autowired
    private ChatLogService chatLogService;

    private Gson gson = new Gson();

    @Transactional
    public void resolve(String msgJson, WebSocket webSocket) {
        System.out.println("serviceChatResolver 1");
        Session session = webSocket.getSession();
        System.out.println("serviceChatResolver 1");
        Type objectType = new TypeToken<Message<ServiceChat>>(){}.getType();
        Message<ServiceChat> message = gson.fromJson(msgJson, objectType);
        message.getContent().setTime(new Date().getTime());
        message.getContent().setServiceId(webSocket.getServiceId());
        ServiceChat serviceChat = message.getContent();
        System.out.println(message.getContent());
        System.out.println("serviceChatResolver 3");
        int contentType = serviceChat.getContentType();
        if (contentType == 0){
            chatLogService.addWithConversationId
                    (serviceChat.getConversationId(),webSocket.getServiceId(),serviceChat.getClientId(),contentType,serviceChat.getContent(),new Date().getTime(),0);
            try {
                System.out.println("serviceChatResolver 4");
                session.getBasicRemote().sendText(gson.toJson(message));
                for(WebSocket ws : ClientWS.wsVector){
                    if (ws.getClientId() == serviceChat.getClientId()){
                        System.out.println("serviceChatResolver 5" + ws.getClientId());
                        ws.getSession().getBasicRemote().sendText(gson.toJson(message));
                        System.out.println("serviceChatResolver 6");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
