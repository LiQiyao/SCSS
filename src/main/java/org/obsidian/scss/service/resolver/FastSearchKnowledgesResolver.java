package org.obsidian.scss.service.resolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.FastSearchKnowledges;
import org.obsidian.scss.bean.Message;
import org.obsidian.scss.bean.RecommandKnowledges;
import org.obsidian.scss.conversation.WebSocket;
import org.obsidian.scss.entity.Knowledge;
import org.obsidian.scss.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.websocket.Session;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */
@Service
public class FastSearchKnowledgesResolver implements ContentResolver {

    @Autowired
    private KnowledgeService knowledgeService;

    @Transactional
    public void resolve(String msgJson, WebSocket webSocket) {
        Session session = webSocket.getSession();
        Gson gson = new Gson();
        Type objectType = new TypeToken<Message<FastSearchKnowledges>>(){}.getType();
        Message<FastSearchKnowledges> message = gson.fromJson(msgJson,objectType);
        FastSearchKnowledges fastSearchKnowledges = message.getContent();
        int conversationId = fastSearchKnowledges.getConversationId();
        String searchSentence = fastSearchKnowledges.getSearchSentence();
        List<Knowledge> knowledgeList = knowledgeService.getKnowledgeByContent(searchSentence);
        RecommandKnowledges recommandKnowledges = new RecommandKnowledges(conversationId,knowledgeList);
        Message<RecommandKnowledges> res = new Message<RecommandKnowledges>(recommandKnowledges);
        try{
            session.getBasicRemote().sendText(gson.toJson(res));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
