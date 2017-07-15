package org.obsidian.scss.conversation;

import com.google.gson.Gson;
import org.obsidian.scss.service.resolver.ResolverFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Vector;

/**
 * Created by Administrator on 2017/7/15.
 */
@ServerEndpoint(value = "/ClientDetailWS",configurator = SpringConfigurator.class)
public class ClientDetailWS implements WebSocket{

    @Autowired
    private ResolverFactory resolverFactory;

    private Session session;

    private int clientId;

    private int serviceId;

    public static Vector<WebSocket> wsVector = new Vector<WebSocket>();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        wsVector.add(this);
    }

    @OnMessage
    public void onMessage(String msgString){
        Gson gson = new Gson();
//        try{
//            this.session.getBasicRemote().sendText(gson.toJson());
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
    }

    @OnError
    public void onError(Throwable t){
        wsVector.remove(this);
    }

    @OnClose
    public void onClose(){
        wsVector.remove(this);
    }

    public Session getSession() {
        return session;
    }

    public Vector<WebSocket> getWsVector() {
        return wsVector;
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
}
