package org.obsidian.scss.service.resolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.ClientChat;
import org.obsidian.scss.bean.Message;
import org.obsidian.scss.dao.ChatLogMapper;
import org.obsidian.scss.entity.Client;
import org.obsidian.scss.service.ChatLogService;
import org.obsidian.scss.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

/**
 * Created by Lee on 2017/7/12.
 */
@Service
public class ClientChatResolver implements ContentResolver {

    @Autowired
    private ChatLogService chatLogService;

    @Autowired
    private ClientService clientService;

    public String resolve(String msgJson) {

        System.out.println(chatLogService.getByClientId(1));
        Gson gson = new Gson();
        Type objectType = new TypeToken<Message<ClientChat>>(){}.getType();
        Message<ClientChat> message = gson.fromJson(msgJson, objectType);
        ClientChat clientChat = message.getContent();
        return clientChat.toString();
    }
}
