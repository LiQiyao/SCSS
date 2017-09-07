package org.obsidian.scss.service.resolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.ClientLogin;
import org.obsidian.scss.bean.ConversationStart;
import org.obsidian.scss.bean.Message;
import org.obsidian.scss.bean.ServiceChat;
import org.obsidian.scss.conversation.WebSocket;
import org.obsidian.scss.entity.JoinUp;
import org.obsidian.scss.service.ChatLogService;
import org.obsidian.scss.service.ClientService;
import org.obsidian.scss.service.ConversationService;
import org.obsidian.scss.service.JoinUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.websocket.Session;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Lee on 2017/7/14.
 */
@Service
public class ClientLoginResolver implements ContentResolver {

    @Autowired
    private JoinUpService joinUpService;

    @Autowired
    private ChatLogService chatLogService;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private ClientService clientService;

    @Transactional
    public void resolve(String msgJson, WebSocket webSocket) {
        System.out.println("resolve");
        Session session = webSocket.getSession();
        ConversationStart conversationStart = new ConversationStart();
        Gson gson = new Gson();
        Type type = new  TypeToken<Message<ClientLogin>>(){}.getType();
        Message<ClientLogin> message = gson.fromJson(msgJson, type);
        ClientLogin clientLogin = message.getContent();
        System.out.println(clientLogin.getClass());
        List<JoinUp> joinUpList = null;
        String account = null;
        int clientId = 0;
        int accessId = clientLogin.getAccessId();
        if (clientLogin.getAccount() != null){
            joinUpList = joinUpService.hasJoinedUp(clientLogin.getAccessId(), clientLogin.getAccount());
            if (joinUpList != null){
                account = joinUpList.get(0).getAccount();
                clientId = joinUpList.get(0).getClientId();
                joinUpService.addJoinUp(accessId, clientId, new Date().getTime(),account);
                conversationStart.setChatLogList(chatLogService.getByClientId(clientId));
            }
        } else {
            account = getNewAccount(accessId);
            clientId = clientService.insertClient(null, null, null, null, 0);
            joinUpService.addJoinUp(accessId, clientId, new Date().getTime(),account);
        }
        conversationStart.setAccount(account);
        conversationStart.setClientId(clientId);
        conversationService.startConversation(clientId, 0, new Date().getTime());
        conversationStart.setConversationId(conversationService.getLastIdByClientId(clientId));
        Message<ConversationStart> ret = new Message<ConversationStart>(conversationStart);
        webSocket.setClientId(clientId);
        try {
            session.getBasicRemote().sendText(gson.toJson(ret));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ServiceChat serviceChat = new ServiceChat();
        serviceChat.setServiceId(0);
        serviceChat.setTime(new Date().getTime());
        serviceChat.setClientId(clientId);
        serviceChat.setContentType(0);
        serviceChat.setContent("您好，我是智能客服，请问有什么可以帮助您的吗？");
        try {
            System.out.println(serviceChat);
            session.getBasicRemote().sendText(gson.toJson(new Message<ServiceChat>(serviceChat)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getNewAccount(int accessId){
        String account = "";
        Random random = new Random();
        do {
            for (int i = 0; i < 10; i++){
                account += random.nextInt(10);
            }
            System.out.println("aaa" + (joinUpService.hasJoinedUp(accessId, account).size() == 0));
        } while (joinUpService.hasJoinedUp(accessId, account).size() != 0);
        return account;
    }
}
