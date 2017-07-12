package org.obsidian.scss.conversation;

import com.google.gson.Gson;
import org.obsidian.scss.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by Lee on 2017/7/12.
 */
@ServerEndpoint("/robotAns")
public class RobotAnsWS {

    private static Vector<RobotAnsWS> robotAnsVector = new Vector<RobotAnsWS>();

    private Session session;

    @Autowired
    private KnowledgeService knowledgeService;

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        robotAnsVector.add(this);
    }

    @OnMessage
    public void onMessage(String msg){
        System.out.println("收到消息" + msg);

        Gson gson = new Gson();
        try {
            this.session.getBasicRemote().sendText(gson.toJson(knowledgeService.getRobotAns(msg)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Throwable t){
        robotAnsVector.remove(this);
    }

    @OnClose
    public void onClose(){
        robotAnsVector.remove(this);
    }
}
