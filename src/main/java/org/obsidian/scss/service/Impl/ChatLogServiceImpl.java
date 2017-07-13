package org.obsidian.scss.service.Impl;

import org.obsidian.scss.dao.ChatLogMapper;
import org.obsidian.scss.dao.ConversationMapper;
import org.obsidian.scss.entity.ChatLog;
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
        }
        return chatLogMapper.insertSelective(new ChatLog(conversationId,senderId,receiverId,contentType,content,time, fromClient));
    }

    /**
     *根据客户编号获取聊天记录
     */
    @Transactional
    public List<ChatLog> getByClientId(int conversationId) {
        return chatLogMapper.selectByConversationId(conversationId);
    }
}
