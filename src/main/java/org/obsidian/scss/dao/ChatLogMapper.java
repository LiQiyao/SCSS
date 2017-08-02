package org.obsidian.scss.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.ChatLog;
import org.obsidian.scss.entity.ChatLogAndSendRecInfo;
import org.obsidian.scss.entity.ChatLogExample;

public interface ChatLogMapper {
    long countByExample(ChatLogExample example);

    int deleteByExample(ChatLogExample example);

    int insert(ChatLog record);

    int insertSelective(ChatLog record);

    List<ChatLog> selectByExample(ChatLogExample example);

    int updateByExampleSelective(@Param("record") ChatLog record, @Param("example") ChatLogExample example);

    int updateByExample(@Param("record") ChatLog record, @Param("example") ChatLogExample example);

    List<ChatLog> selectByConversationId(int conversationId);

    List<ChatLog> selectByClientId(int clientId);

    List<ChatLog> selectClientSayByConversationId(int conversationId);
    
    List<ChatLogAndSendRecInfo> selectClientAndServerChatLog(@Param("clientId") int clientId,@Param("serviceId")int serviceId);
    
    List<ChatLogAndSendRecInfo> selectClientChatLog(@Param("clientId") int clientId);
}