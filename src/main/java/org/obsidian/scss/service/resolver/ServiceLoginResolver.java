package org.obsidian.scss.service.resolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.Message;
import org.obsidian.scss.bean.ServiceInfo;
import org.obsidian.scss.bean.ServiceLogin;
import org.obsidian.scss.conversation.WebSocket;
import org.obsidian.scss.entity.CustomerService;
import org.obsidian.scss.service.CustomerServiceService;
import org.obsidian.scss.service.OnlineService;
import org.obsidian.scss.service.ServiceGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.io.IOException;
import java.lang.reflect.Type;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Lee on 2017/7/17.
 */
@Service
public class ServiceLoginResolver implements ContentResolver {

    @Autowired
    private OnlineService onlineService;

    @Autowired
    private CustomerServiceService customerServiceService;

    @Autowired
    private ServiceGroupService serviceGroupService;

    public void resolve(String msgJson, WebSocket webSocket) {
        Gson gson = new Gson();
        Session session = webSocket.getSession();
        Type objectType = new TypeToken<Message<ServiceLogin>>(){}.getType();
        Message<ServiceLogin> message = gson.fromJson(msgJson, objectType);
        ServiceLogin serviceLogin = message.getContent();
        String token = serviceLogin.getToken();
        String employeeId = onlineService.getEmployeeId(token);
        CustomerService customerService = customerServiceService.selectCustomerServiceByEmployeeId(employeeId);
        webSocket.setServiceId(customerService.getServiceId());
        String groupName = serviceGroupService.selectGroupByGroupId(customerService.getGroupId()).getName();
        ServiceInfo serviceInfo = new ServiceInfo(customerService.getServiceId(),customerService.getName(),customerService.getNickname(), groupName ,customerService.getEmployeeId(),customerService.getAutoMessage());
        Message<ServiceInfo> res = new Message<ServiceInfo>(serviceInfo);
        try {
            session.getBasicRemote().sendText(gson.toJson(res));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
