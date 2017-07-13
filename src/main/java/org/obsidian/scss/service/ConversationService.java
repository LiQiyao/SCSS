package org.obsidian.scss.service;


import org.obsidian.scss.entity.Conversation;

import java.util.List;

/**
 * Created by Lee on 2017/7/13.
 */
public interface ConversationService {

    List<Conversation> getByClientId(int clientId);

    List<Conversation> getByServiceId(int serviceId);

    int getAvgScoreByServiceId(int serviceId);

    int startConversation(int clientId, int serviceId, long startTime);

    int endConversation(long stopTime, int score);
}
