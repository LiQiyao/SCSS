package org.obsidian.scss.dao;

import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.bean.AvgScoreList;
import org.obsidian.scss.bean.ScoreAndNum;
import org.obsidian.scss.entity.*;

import java.util.List;

public interface ConversationMapper {
    List<AvgScoreList> selectAllAvgScore();

    int updateClientId(@Param("conversationId")int conversationId,@Param("clientId")int clientId);

    long countByExample(ConversationExample example);

    int deleteByExample(ConversationExample example);

    int insert(Conversation record);

    int insertSelective(Conversation record);

    List<Conversation> selectByExample(ConversationExample example);

    int updateByExampleSelective(@Param("record") Conversation record, @Param("example") ConversationExample example);

    int updateByExample(@Param("record") Conversation record, @Param("example") ConversationExample example);

    List<Conversation> selectByClientId(int clientId);

    List<Conversation> selectByServiceId(int serviceId);

    List<Conversation> selectByConversationId(int conversationId);

    int selectCountByServiceId(int serviceId);

    double selectAvgScoreByServiceId(int serviceId);

    int updateStopTime(@Param("conversationId") int conversationId,@Param("stopTime") long stopTime,@Param("score") int score);

    int updateStopTimeWithoutScore(@Param("conversationId") int conversationId,@Param("stopTime") long stopTime);

    int updateScore(@Param("conversationId") int conversationId ,@Param("score") int score);

    CustomerService selectLastChatServiceId(int clientId);

    Integer selectLastIdByClientId(int clientId);

    int updateServiceId(@Param("serviceId") int serviceId,@Param("conversationId") int conversationId);

    int selectNotFinishByServiceId(int serviceId);

    int updateAllStopTimeByServiceId(@Param("serviceId") int serviceId, @Param("stopTime") long stopTime);

    int selectTodayConversationCount(@Param("serviceId") int serviceId,@Param("dayStart") long dayStart , @Param("dayEnd") long dayEnd);
    /**
     * Create By Cjn
     */
    int selectConversationNotFinish();
    List<DayAndTime> selectRecentMonth(@Param("timestamp") Long timestamp);
    List<DayAndTime> selectRecentWeekend(@Param("timestamp") Long timestamp);
    List<DayAndTime> selectRecentHour(@Param("timestamp") Long timestamp);
    List<DayAndTime> selectRecentMinute(@Param("timestamp") Long timestamp);
    List<DayAndTime> selectRecentPeopleMonth(@Param("timestamp") Long timestamp,@Param("serviceId") int serviceId);
    List<DayAndTime> selectRecentPeopleWeekend(@Param("timestamp") Long timestamp,@Param("serviceId") int serviceId);
    List<DayAndTime> selectRecentPeopleHour(@Param("timestamp") Long timestamp,@Param("serviceId") int serviceId);
    double selectAvgScore(@Param("startTime") long startTime,@Param("stopTime") long stopTime);
    List<ScoreAndNum> selectScore(@Param("startTime") long startTime,@Param("stopTime") long stopTime);
    List<ScoreAndRank> selectScoreRank(@Param("startTime") long startTime,@Param("stopTime") long stopTime,@Param("serviceId") int serviceId);
    List<ConNumAndRank> selectConNumAndRank(@Param("startTime") long startTime,@Param("stopTime") long stopTime,@Param("serviceId") int serviceId);
    List<Client>selectClientChatList(@Param("serviceId") int serviceId);
}