package org.obsidian.scss.service.resolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.*;
import org.obsidian.scss.conversation.ClientWS;
import org.obsidian.scss.conversation.WebSocket;
import org.obsidian.scss.dao.ConversationMapper;
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
public class ConversationEndReqResolver implements ContentResolver {

    private Gson gson = new Gson();

    @Autowired
    private ConversationMapper conversationMapper;

    @Transactional
    public void resolve(String msgJson, WebSocket webSocket) {
        System.out.println("1");
        Session session = webSocket.getSession();
        System.out.println("2" + conversationMapper);
        Type objectType = new TypeToken<Message<ConversationEndReq>>(){}.getType();
        System.out.println("3" + objectType);
        Message<ConversationEndReq> message = gson.fromJson(msgJson, objectType);
        System.out.println("4");
        ConversationEndReq conversationEndReq = message.getContent();
        int conversationId = conversationEndReq.getConversationId();
        int clientId = conversationEndReq.getClientId();
        conversationMapper.updateStopTimeWithoutScore(conversationId,new Date().getTime());
        ScoreReq scoreReq = new ScoreReq(conversationId);
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setConversationCount(conversationMapper.selectNotFinishByServiceId(webSocket.getServiceId()));
        try {
            session.getBasicRemote().sendText(gson.toJson(new Message<ConversationEndSignal>(new ConversationEndSignal(conversationId))));
            session.getBasicRemote().sendText(gson.toJson(new Message<ServiceStatus>(serviceStatus)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (WebSocket ws : ClientWS.wsVector){
            if (ws.getClientId() == clientId){
                try {
                    System.out.println("clientId:" +clientId);
                    ws.getSession().getBasicRemote().sendText(gson.toJson(new Message<ScoreReq>(scoreReq)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
