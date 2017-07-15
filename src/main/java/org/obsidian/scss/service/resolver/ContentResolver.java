package org.obsidian.scss.service.resolver;

import org.obsidian.scss.bean.ClientChat;
import org.obsidian.scss.conversation.WebSocket;

import javax.websocket.Session;

/**
 * 处理从前端接收的消息
 * Created by Lee on 2017/7/12.
 */
public interface ContentResolver{
    void resolve(String msgJson, WebSocket webSocket);
}
