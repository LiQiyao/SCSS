package org.obsidian.scss.service.Impl;

import org.obsidian.scss.entity.Conversation;
import org.obsidian.scss.service.ConversationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lee on 2017/7/13.
 */
@Service
public class ConversationServiceImpl implements ConversationService {
    public List<Conversation> getByClientId(int clientId) {
        return null;
    }

    public List<Conversation> getByServiceId(int serviceId) {
        return null;
    }

    public int getAvgScoreByServiceId(int serviceId) {
        return 0;
    }

    public int startConversation(int clientId, int serviceId, long startTime) {
        return 0;
    }

    public int endConversation(long stopTime, int score) {
        return 0;
    }
}
