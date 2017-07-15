package org.obsidian.scss.service.resolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.ClientDetailReq;
import org.obsidian.scss.bean.ClientDetailResp;
import org.obsidian.scss.bean.Message;
import org.obsidian.scss.conversation.WebSocket;
import org.obsidian.scss.entity.Client;
import org.obsidian.scss.entity.Flag;
import org.obsidian.scss.entity.JoinUp;
import org.obsidian.scss.service.ClientService;
import org.obsidian.scss.service.JoinUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/15.
 */
@Service
public class ClientDetailReqResolver {

    @Autowired
    private ClientService clientService;

    @Autowired
    private JoinUpService joinUpService;

    public void resolve(String msgJson, WebSocket webSocket){
        Session session = webSocket.getSession();
        Gson gson = new Gson();
        Type objectType = new TypeToken<Message<ClientDetailReq>>(){}.getType();
        Message<ClientDetailReq> message = gson.fromJson(msgJson,objectType);
        ClientDetailReq clientDetailReq = message.getContent();
        int clientId = clientDetailReq.getClientId();
        Client client = clientService.selectClientByClientId(clientId);
        String clientName = client.getName();
        int sex = client.getSex();
        Long phoneNum = client.getTelephone();
        String email = client.getEmail();
        String address = client.getAddress();
        String wx = "";
        String qq = "";
        String weibo = "";
        String taobao = "";
        String alipay = "";
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
            if(joinUpList.get(i).getAccess().getName().equals("淘宝")){
                taobao = joinUpList.get(i).getAccount();
            }
            if(joinUpList.get(i).getAccess().getName().equals("支付宝")){
                alipay = joinUpList.get(i).getAccount();
            }
        }
        List<Flag> flagList = clientService.selectAllFlag(clientId);
        List<String> tagList = new ArrayList<String>();
        for(int i=0;i<flagList.size();i++){
            tagList.add(flagList.get(i).getName());
        }
        List<Flag> unusedFlagList = clientService.selectAllUnusedFlag(clientId);
        List<String> unusedTagList = new ArrayList<String>();
        for(int i=0;i<unusedFlagList.size();i++){
            unusedTagList.add(unusedFlagList.get(i).getName());
        }
        ClientDetailResp clientDetailResp = new ClientDetailResp(clientId,clientName,sex,phoneNum,email,wx,qq,weibo,taobao,alipay,address,tagList,unusedTagList);
        Message<ClientDetailResp> res = new Message<ClientDetailResp>(clientDetailResp);
        try{
            session.getBasicRemote().sendText(gson.toJson(res));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
