package org.obsidian.scss.service;

import org.obsidian.scss.entity.ChatLog;

import java.util.List;

/**
 * Created by Lee on 2017/7/13.
 */
public interface ChatLogService {

    int add(int senderId, int receiverId, int contentType, String content, Long time, int fromClient);

    List<ChatLog> getByClientId(int clientId);

    List<ChatLog> getByConversationId(int conversationId);
}
