package org.obsidian.scss.conversation;

/**
 * Created by Lee on 2017/7/8.
 */

import org.obsidian.scss.service.resolver.ResolverFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Vector;

@ServerEndpoint(value = "/ClientWS", configurator = SpringConfigurator.class)
public class ClientWS {

    @Autowired
    private ResolverFactory resolverFactory;

    private Session session;

    public static Vector<ClientWS> clientWSVector = new Vector<ClientWS>();

    private int clientId;

    private int serviceId = 0;

    @OnOpen
    public void onOpen(Session session){
        System.out.println("!!!open" +session);
        this.session = session;
        clientWSVector.add(this);
    }

    @OnMessage
    public void onMessage(String msgString){
        System.out.println("收到消息：" + msgString);
        System.out.println(session);
        System.out.println(session.getBasicRemote());
        System.out.println(resolverFactory + "!!");
        try {
            this.session.getBasicRemote().sendText(resolverFactory.doAction(msgString));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(){
        System.out.println("!!!close");
        clientWSVector.remove(this);
    }

    @OnError
    public void onError(Throwable t){
        System.out.println(t.getCause() + "!!!error");
        clientWSVector.remove(this);
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
