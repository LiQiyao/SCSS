package org.obsidian.scss.service.resolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.Message;
import org.obsidian.scss.bean.SearchClientByNameReq;
import org.obsidian.scss.bean.SearchClientByNameResp;
import org.obsidian.scss.conversation.WebSocket;
import org.obsidian.scss.entity.Client;
import org.obsidian.scss.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.websocket.Session;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */
@Service
public class SearchClientByNameReqResolver implements ContentResolver {

    @Autowired
    private ClientService clientService;

    @Transactional
    public void resolve(String msgJson, WebSocket webSocket) {
        Session session = webSocket.getSession();
        Gson gson = new Gson();
        Type objectType = new TypeToken<Message<SearchClientByNameReq>>(){}.getType();
        Message<SearchClientByNameReq> message = gson.fromJson(msgJson,objectType);
        SearchClientByNameReq searchClientByNameReq = message.getContent();
        String searchWord = searchClientByNameReq.getSearchWord();
        List<Client> list = clientService.selectAllByName(searchWord);
        SearchClientByNameResp searchClientByNameResp = new SearchClientByNameResp();
        if(list != null && list.size() > 0){
            searchClientByNameResp.setClientList(list);
        }
        Message<SearchClientByNameResp> res = new Message<SearchClientByNameResp>(searchClientByNameResp);
        try {
            session.getBasicRemote().sendText(gson.toJson(res));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
