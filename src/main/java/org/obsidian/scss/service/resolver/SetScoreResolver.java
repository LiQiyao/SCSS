package org.obsidian.scss.service.resolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.ConversationEndReq;
import org.obsidian.scss.bean.ConversationEndSignal;
import org.obsidian.scss.bean.Message;
import org.obsidian.scss.bean.SetScore;
import org.obsidian.scss.conversation.WebSocket;
import org.obsidian.scss.dao.ConversationMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.websocket.Session;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by Lee on 2017/7/18.
 */
public class SetScoreResolver implements ContentResolver {

    private Gson gson;

    @Autowired
    private ConversationMapper conversationMapper;

    public void resolve(String msgJson, WebSocket webSocket) {
        Session session = webSocket.getSession();
        Type objectType = new TypeToken<Message<SetScore>>(){}.getType();
        Message<SetScore> message = gson.fromJson(msgJson, objectType);
        SetScore setScore = message.getContent();
        int score = setScore.getScore();
        int conversationId = setScore.getScore();
        conversationMapper.updateScore(conversationId,score);
    }
}
