package org.obsidian.scss.service.resolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.ChangeClientConversationRelationship;
import org.obsidian.scss.bean.ClientDetailResp;
import org.obsidian.scss.bean.ClientDetailResp2;
import org.obsidian.scss.bean.Message;
import org.obsidian.scss.conversation.WebSocket;
import org.obsidian.scss.entity.Client;
import org.obsidian.scss.entity.Flag;
import org.obsidian.scss.entity.JoinUp;
import org.obsidian.scss.service.ClientService;
import org.obsidian.scss.service.ConversationService;
import org.obsidian.scss.service.JoinUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.websocket.Session;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */
@Service
public class ChangeClientConversationRelationshipResolver implements ContentResolver {

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private JoinUpService joinUpService;

    @Transactional
    public void resolve(String msgJson, WebSocket webSocket) {
        Session session = webSocket.getSession();
        Gson gson = new Gson();
        Type objectType = new TypeToken<Message<ChangeClientConversationRelationship>>(){}.getType();
        Message<ChangeClientConversationRelationship> message = gson.fromJson(msgJson,objectType);
        ChangeClientConversationRelationship changeClientConversationRelationship = message.getContent();
        int conversationId = changeClientConversationRelationship.getConversationId();
        int clientId = changeClientConversationRelationship.getClientId();
        int nowClientId = conversationService.selectClientIdByConversationId(conversationId);
        System.out.println(conversationId + " " + clientId + " " + nowClientId);
        Client client = clientService.selectClientByClientId(clientId);
        Client nowClient = clientService.selectClientByClientId(nowClientId);

        String clientName = client.getName();
        int sex = client.getSex();
        Long phoneNum = client.getTelephone();
        String email = client.getEmail();
        String address = client.getAddress();
        if(nowClient.getName() != null && !nowClient.getName().equals(clientName)){
            clientName = nowClient.getName();
        }
        if(!nowClient.getSex().equals(sex)){
            sex = nowClient.getSex();
        }
        if(nowClient.getTelephone() != null && !nowClient.getTelephone().equals(phoneNum)){
            phoneNum = nowClient.getTelephone();
        }
        if(nowClient.getEmail() != null && !nowClient.getEmail().equals(email)){
            email = nowClient.getEmail();
        }
        if(nowClient.getAddress() != null && !nowClient.getAddress().equals(address)){
            address = nowClient.getAddress();
        }
        clientService.updateClient(nowClientId,clientName,address,email,phoneNum,sex);
        String wx = "";
        String qq = "";
        String weibo = "";
        System.out.println(client.toString());
        List<JoinUp> joinUpList = joinUpService.getByClientId(clientId);
        for(int i=0;i<joinUpList.size();i++){
            if(joinUpList.get(i).getAccess().getName().equals("微信")){
                wx = joinUpList.get(i).getAccount();
            }
            if(joinUpList.get(i).getAccess().getName().equals("QQ")){
                qq = joinUpList.get(i).getAccount();
            }
            if(joinUpList.get(i).getAccess().getName().equals("微博")){
                weibo = joinUpList.get(i).getAccount();
            }
        }
        List<Flag> flagList = clientService.selectAllFlag(clientId);
        List<String> tagList = new ArrayList<String>();
        if(flagList != null && flagList.size() > 0){
            for(int i=0;i<flagList.size();i++){
                tagList.add(flagList.get(i).getName());
            }
        }
        List<Flag> unusedFlagList = clientService.selectAllUnusedFlag(clientId);
        List<String> unusedTagList = new ArrayList<String>();
        if(unusedFlagList != null && unusedFlagList.size() > 0){
            for(int i=0;i<unusedFlagList.size();i++){
                unusedTagList.add(unusedFlagList.get(i).getName());
            }
        }
        ClientDetailResp2 clientDetailResp2 = new ClientDetailResp2(nowClientId,clientName,sex,phoneNum,email,wx,qq,weibo,address,tagList,unusedTagList,conversationId);
        Message<ClientDetailResp> res = new Message<ClientDetailResp>(clientDetailResp2);
        try{
            session.getBasicRemote().sendText(gson.toJson(res));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
