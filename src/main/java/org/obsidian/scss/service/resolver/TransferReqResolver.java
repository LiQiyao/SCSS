package org.obsidian.scss.service.resolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.Message;
import org.obsidian.scss.bean.TransferReq;
import org.obsidian.scss.conversation.WebSocket;
import org.obsidian.scss.service.GroupQueue;
import org.springframework.beans.factory.annotation.Autowired;

import javax.websocket.Session;
import java.lang.reflect.Type;

/**
 * Created by Lee on 2017/7/17.
 */
public class TransferReqResolver implements ContentResolver {

    private Gson gson = new Gson();

    @Autowired
    private GroupQueue groupQueue;

    public void resolve(String msgJson, WebSocket webSocket) {
        Session session = webSocket.getSession();
        Type objectType = new TypeToken<Message<TransferReq>>(){}.getType();
        Message<TransferReq> message = gson.fromJson(msgJson, objectType);
        TransferReq transferReq = message.getContent();
        int transferType = transferReq.getTransferType();
        int targetId = transferReq.getTargetId();
        if (transferType == 0){

        }
    }
}
