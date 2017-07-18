package org.obsidian.scss.service;


import org.obsidian.scss.entity.Conversation;
import org.obsidian.scss.entity.CustomerService;
import org.obsidian.scss.entity.DayAndTime;

import java.util.List;

/**
 * Created by Lee on 2017/7/13.
 */
public interface ConversationService {
    int updateClientId(int conversationId,int clientId);

    List<Conversation> getByClientId(int clientId);

    List<Conversation> getByServiceId(int serviceId);

    int getAvgScoreByServiceId(int serviceId);

    int startConversation(int clientId, int serviceId, long startTime);

    int endConversation(int conversationId,long stopTime, Integer score);

    int getLastIdByClientId(int clientId);

    CustomerService getLastChatServiceId(int clientId);

    int resetServiceId(int serviceId, int conversationId);

    /**
     * Create By cjn
     * @return
     */
    int selectConversationNotFinish();
    List<DayAndTime> selectRecentMonth();
    List<DayAndTime> selectRecentWeekend();
    List<DayAndTime> selectRecentHour();
    List<DayAndTime> selectRecentMinute();
    List<DayAndTime> selectRecentPeopleMonth(int serviceId);
    List<DayAndTime> selectRecentPeopleWeekend(int serviceId);
    List<DayAndTime> selectRecentPeopleHour(int serviceId);
}
