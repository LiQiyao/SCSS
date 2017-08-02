package org.obsidian.scss.service;


import org.obsidian.scss.bean.ScoreAndNum;
import org.obsidian.scss.entity.*;

import java.util.List;

/**
 * Created by Lee on 2017/7/13.
 */
public interface ConversationService {
    int getAvgScoreRankByServiceId(int serviceId);

    int updateClientId(int conversationId,int clientId);

    List<Conversation> getByClientId(int clientId);

    List<Conversation> getByServiceId(int serviceId);

    double getAvgScoreByServiceId(int serviceId);

    int startConversation(int clientId, int serviceId, long startTime);

    int endConversation(int conversationId,long stopTime, Integer score);

    int getLastIdByClientId(int clientId);

    CustomerService getLastChatServiceId(int clientId);

    int resetServiceId(int serviceId, int conversationId);

    int finishAllByServiceId(int serviceId);

    int getTodayConversationCount(int serviceId);

    int getTodayConversationCountRank(int serviceId);
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
    long selectDuringConverSationNum(long startTime,long stopTime);
    double avgGrades(long startTime,long stopTime);
    List<ScoreAndNum> scoreAndNum(long startTime,long stopTime);
    List<ScoreAndRank> selectScoreAndRank(long startTime, long stopTime, int serviceId);
    List<ConNumAndRank> selectConNumAndRank(long startTime, long stopTime, int serviceId);
    List<Client> selectClientChatList(int serviceId);
}
