package org.obsidian.scss.conversation;

import com.google.gson.Gson;
import org.obsidian.scss.service.KnowledgeService;
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

    }

    @OnError
    public void onError(Throwable t){
        wsVector.remove(this);
    }

    @OnClose
    public void onClose(){
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
