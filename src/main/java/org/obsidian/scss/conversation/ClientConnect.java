package org.obsidian.scss.conversation;

/**
 * Created by Lee on 2017/7/8.
 */

import com.google.gson.Gson;
import org.obsidian.scss.bean.ClientConnectReq;
import org.obsidian.scss.bean.ConnectStatus;
import org.obsidian.scss.bean.Message;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Vector;

@ServerEndpoint("/ClientConnect")
public class ClientConnect  {

    private Session session;

    public static Vector<ClientConnect> clientConnects = new Vector<ClientConnect>();

    @OnOpen
    public void onOpen(Session session){
        System.out.println("!!!open");
        this.session = session;
        clientConnects.add(this);
    }

    @OnMessage
    public void onMessage(String msgString){
        System.out.println("!!!1");
        Gson gson = new Gson();
        Message<ClientConnectReq> message1 = gson.fromJson(msgString, Message.class);
        System.out.println(message1.getType() + "!!" + message1.getContent().getTime() + "!!!2");
        ClientConnectReq content = message1.getContent();
        System.out.println("time:" + content.getTime());
        String s = gson.toJson(new Message<ConnectStatus>(new ConnectStatus(1)));
        try {
            System.out.println("!!!3");
            this.session.getBasicRemote().sendText(s);
            System.out.println("!!!4");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(){
        System.out.println("!!!close");
        clientConnects.remove(this);
    }

    @OnError
    public void onError(Throwable t){
        System.out.println(t + "!!!error");
        clientConnects.remove(this);
    }

}
