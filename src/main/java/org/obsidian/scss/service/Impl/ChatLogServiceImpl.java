package org.obsidian.scss.service.Impl;

import org.obsidian.scss.dao.ChatLogMapper;
import org.obsidian.scss.dao.ConversationMapper;
import org.obsidian.scss.entity.ChatLog;
import org.obsidian.scss.entity.ChatLogAndSendRecInfo;
import org.obsidian.scss.service.ChatLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Lee on 2017/7/13.
 */
@Service
public class ChatLogServiceImpl implements ChatLogService {

    @Autowired
    private ChatLogMapper chatLogMapper;

    @Autowired
    private ConversationMapper conversationMapper;

    /**
     *添加聊天记录
     */
    @Transactional
    public int add(int senderId, int receiverId, int contentType, String content, Long time, int fromClient) {
        int conversationId = 0;
        if (fromClient == 1){
            conversationId = conversationMapper.selectLastIdByClientId(senderId);
        } else {
            conversationId = conversationMapper.selectLastIdByClientId(receiverId);
        }
        return chatLogMapper.insertSelective(new ChatLog(conversationId,senderId,receiverId,contentType,content,time, fromClient));
    }

    @Transactional
    public int addWithConversationId(int conversationId, int senderId, int receiverId, int contentType, String content, Long time, int fromClient) {
        return chatLogMapper.insertSelective(new ChatLog(conversationId,senderId,receiverId,contentType,content,time, fromClient));
    }


    /**
     *根据客户编号获取聊天记录
     */
    @Transactional
    public List<ChatLog> getByClientId(int clientId) {
        return chatLogMapper.selectByClientId(clientId);
    }

    @Transactional
    public List<ChatLog> getByConversationId(int conversationId) {
        return chatLogMapper.selectByConversationId(conversationId);
    }

    public List<ChatLog> getClientSayByConversationId(int conversationId) {
        return chatLogMapper.selectClientSayByConversationId(conversationId);
    }

    @Transactional
    public List<ChatLogAndSendRecInfo> selectClientAndServerChatLog(int clientId, int serviceId) {
        return chatLogMapper.selectClientAndServerChatLog(clientId,serviceId);
    }

    @Transactional
    public List<ChatLogAndSendRecInfo> selectClientChatLog(int clientId) {
        return chatLogMapper.selectClientChatLog(clientId);
    }
}
