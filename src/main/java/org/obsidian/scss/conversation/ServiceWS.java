package org.obsidian.scss.conversation;

import com.google.gson.Gson;
import org.obsidian.scss.service.ConversationService;
import org.obsidian.scss.service.KnowledgeService;
import org.obsidian.scss.service.WorkTimeService;
import org.obsidian.scss.service.resolver.ResolverFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Lee on 2017/7/12.
 */
@ServerEndpoint(value="/ServiceWS", configurator = SpringConfigurator.class)
public class ServiceWS implements WebSocket {

    @Autowired
    private WorkTimeService workTimeService;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private ResolverFactory resolverFactory;

    public static Vector<WebSocket> wsVector = new Vector<WebSocket>();

    private Session session;

    private int clientId;

    private int serviceId = 1;

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        wsVector.add(this);
    }

    @OnMessage
    public void onMessage(String msgString){
        System.out.println("收到消息" + msgString);
        Gson gson = new Gson();
        resolverFactory.doAction(msgString, this);
    }

    @OnError
    public void onError(Throwable t){
        System.out.println("service" +t);
        workTimeService.offline(serviceId);
        conversationService.finishAllByServiceId(serviceId);
        wsVector.remove(this);
    }

    @OnClose
    public void onClose(){
        System.out.println("Service close");
        workTimeService.offline(serviceId);
        conversationService.finishAllByServiceId(serviceId);
        wsVector.remove(this);
    }

    public Vector<WebSocket> getWsVector() {
        return wsVector;
    }

    public Session getSession() {
        return session;
    }

    public int getClientId() {
        return clientId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
}
