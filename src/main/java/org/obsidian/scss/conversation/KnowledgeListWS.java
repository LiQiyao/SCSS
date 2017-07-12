package org.obsidian.scss.conversation;

import com.google.gson.Gson;
import org.obsidian.scss.bean.KnowledgeList;
import org.obsidian.scss.service.Impl.KnowledgeServiceImpl;
import org.obsidian.scss.service.KnowledgeService;
import org.obsidian.scss.service.resolver.ResolverFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by Lee on 2017/7/12.
 */
@ServerEndpoint(value = "/knowledgeListWS", configurator = SpringConfigurator.class)
public class KnowledgeListWS {
    private static Vector<KnowledgeListWS> knowledgeListVector = new Vector<KnowledgeListWS>();

    private Session session;

    @Autowired
    private KnowledgeService knowledgeService;

    @Autowired
    private ResolverFactory resolverFactory;

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        knowledgeListVector.add(this);
    }

    @OnMessage
    public void onMessage(String msg){
        System.out.println("收到消息" + msg);
        System.out.println(resolverFactory.doAction(msg));
/*        Gson gson = new Gson();
        try {
            this.session.getBasicRemote().sendText(gson.toJson(knowledgeService.getKnowledgeByContent(msg)));

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @OnError
    public void onError(Throwable t){
        knowledgeListVector.remove(this);
    }

    @OnClose
    public void onClose(){
        knowledgeListVector.remove(this);
    }
}
