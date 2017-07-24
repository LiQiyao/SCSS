package org.obsidian.scss.service.resolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.CommonLanguageClick;
import org.obsidian.scss.bean.Message;
import org.obsidian.scss.conversation.WebSocket;
import org.obsidian.scss.service.CommonLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;

/**
 * Created by Administrator on 2017/7/24.
 */
@Service
public class CommonLanguageClickResolver implements ContentResolver {

    @Autowired
    private CommonLanguageService commonLanguageService;

    @Transactional
    public void resolve(String msgJson, WebSocket webSocket) {
        Gson gson = new Gson();
        Type objectType = new TypeToken<Message<CommonLanguageClick>>(){}.getType();
        Message<CommonLanguageClick> message = gson.fromJson(msgJson,objectType);
        CommonLanguageClick commonLanguageClick = message.getContent();
        int commonLanguageId = commonLanguageClick.getCommonLanguageId();
        commonLanguageService.addCommonLanguageFrequency(commonLanguageId);
    }
}
