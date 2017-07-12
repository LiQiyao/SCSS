package org.obsidian.scss.service.resolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.ClientChat;
import org.obsidian.scss.bean.Message;
import org.obsidian.scss.entity.Client;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

/**
 * Created by Lee on 2017/7/12.
 */
@Service
public class ClientChatResolver implements ContentResolver {

    public String resolve(String msgJson) {
        System.out.println("!!");
        Gson gson = new Gson();
        Type objectType = new TypeToken<Message<ClientChat>>(){}.getType();
        Message<ClientChat> message = gson.fromJson(msgJson, objectType);
        ClientChat clientChat = message.getContent();
        return clientChat.toString();
    }
}
