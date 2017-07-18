package org.obsidian.scss.service.resolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.ConversationEndReq;
import org.obsidian.scss.bean.ConversationEndSignal;
import org.obsidian.scss.bean.Message;
import org.obsidian.scss.bean.TransferReq;
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

    private Gson gson;

    @Autowired
    private ConversationMapper conversationMapper;

    @Transactional
    public void resolve(String msgJson, WebSocket webSocket) {
        Session session = webSocket.getSession();
        Type objectType = new TypeToken<Message<ConversationEndReq>>(){}.getType();
        Message<ConversationEndReq> message = gson.fromJson(msgJson, objectType);
        ConversationEndReq conversationEndReq = message.getContent();
        int conversationId = conversationEndReq.getConversationId();
        conversationMapper.updateStopTimeWithoutScore(conversationId,new Date().getTime());
        try {
            session.getBasicRemote().sendText(gson.toJson(new Message<ConversationEndSignal>(new ConversationEndSignal(1))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
