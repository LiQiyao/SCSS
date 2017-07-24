package org.obsidian.scss.conversation;

/**
 * Created by Lee on 2017/7/8.
 */

import org.apache.catalina.websocket.WebSocketServlet;
import org.obsidian.scss.dao.ConversationMapper;
import org.obsidian.scss.service.ConversationService;
import org.obsidian.scss.service.resolver.ResolverFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;

@ServerEndpoint(value = "/ClientWS", configurator = SpringConfigurator.class)
public class ClientWS implements WebSocket{

    @Autowired
    private ResolverFactory resolverFactory;

    @Autowired
    private ConversationService conversationService;

    private Session session;

    public static Vector<WebSocket> wsVector = new Vector<WebSocket>();

    private int clientId;

    private int serviceId = 0;

    @OnOpen
    public void onOpen(Session session){
        System.out.println("!!!open" +session);
        this.session = session;
        wsVector.add(this);
    }

    @OnMessage
    public void onMessage(String msgString) throws Exception {
        System.out.println("收到消息：" + msgString);
        System.out.println(session);
        System.out.println(resolverFactory + "!!");
        resolverFactory.doAction(msgString, this);
    }

    @OnClose
    public void onClose(){
        System.out.println("!!!close");
        int conversationId = conversationService.getLastIdByClientId(clientId);
        conversationService.endConversation(conversationId, new Date().getTime(), null);
        wsVector.remove(this);
    }

    @OnError
    public void onError(Throwable t){
        System.out.println(t.getCause() + "!!!error");
        int conversationId = conversationService.getLastIdByClientId(clientId);
        conversationService.endConversation(conversationId, new Date().getTime(), null);
        wsVector.remove(this);
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public ResolverFactory getResolverFactory() {
        return resolverFactory;
    }

    public Session getSession() {
        return session;
    }

    public Vector<WebSocket> getWsVector(){
        return wsVector;
    }
}
