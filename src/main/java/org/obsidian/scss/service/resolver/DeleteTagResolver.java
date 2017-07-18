package org.obsidian.scss.service.resolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.AddTag;
import org.obsidian.scss.bean.Message;
import org.obsidian.scss.conversation.WebSocket;
import org.obsidian.scss.service.ClientService;
import org.obsidian.scss.service.FlagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;

/**
 * Created by Administrator on 2017/7/17.
 */
@Service
public class DeleteTagResolver implements ContentResolver {

    @Autowired
    private ClientService clientService;

    @Autowired
    private FlagService flagService;

    @Transactional
    public void resolve(String msgJson, WebSocket webSocket) {
        Gson gson = new Gson();
        Type objectType = new TypeToken<Message<AddTag>>(){}.getType();
        Message<AddTag> message = gson.fromJson(msgJson,objectType);
        AddTag addTag = message.getContent();
        int clientId = addTag.getClientId();
        String tag = addTag.getTag();
        int flagId = flagService.selectFlagId(tag);
        if(flagId != 0){
            clientService.deleteFlag(clientId,flagId);
        }
    }
}
