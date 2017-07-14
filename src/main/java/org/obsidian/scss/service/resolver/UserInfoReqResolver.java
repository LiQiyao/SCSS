package org.obsidian.scss.service.resolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.ClientChat;
import org.obsidian.scss.bean.Message;
import org.obsidian.scss.bean.UserInfoReq;
import org.obsidian.scss.bean.UserInfoResp;
import org.obsidian.scss.conversation.ClientWS;
import org.obsidian.scss.conversation.WebSocket;
import org.obsidian.scss.entity.Client;
import org.obsidian.scss.entity.CustomerService;
import org.obsidian.scss.entity.JoinUp;
import org.obsidian.scss.service.ClientService;
import org.obsidian.scss.service.CustomerServiceService;
import org.obsidian.scss.service.JoinUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by Lee on 2017/7/14.
 */
@Service
public class UserInfoReqResolver implements ContentResolver {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CustomerServiceService customerServiceService;

    @Autowired
    private JoinUpService joinUpService;

    public void resolve(String msgJson, WebSocket webSocket) {
        Session session = webSocket.getSession();
        Gson gson = new Gson();
        Type objectType = new TypeToken<Message<UserInfoReq>>(){}.getType();
        Message<UserInfoReq> message = gson.fromJson(msgJson, objectType);
        UserInfoReq userInfoReq = message.getContent();
        int userType = userInfoReq.getUserType();
        int userId = userInfoReq.getUserId();
        String nickName;
        if (userInfoReq.getUserType() == 0){
            Client client = clientService.selectClientByClientId(userId);
            if (client.getName() == null){
                JoinUp joinUp = joinUpService.getLastByClientId(userId);
                nickName = joinUp.getAccess().getName() +  "用户" + joinUp.getAccount();
            } else {
                nickName = client.getName();
            }
        } else {
            CustomerService customerService = customerServiceService.selectCustomerServiceByServiceId(userId);
            nickName = customerService.getName();
        }
        UserInfoResp userInfoResp = new UserInfoResp(userType, nickName, userId);
        Message<UserInfoResp> res = new Message<UserInfoResp>(userInfoResp);
        try {
            session.getBasicRemote().sendText(gson.toJson(res));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
