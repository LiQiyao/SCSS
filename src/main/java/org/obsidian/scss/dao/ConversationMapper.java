package org.obsidian.scss.dao;

import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.Conversation;
import org.obsidian.scss.entity.ConversationExample;
import org.obsidian.scss.entity.CustomerService;
import org.obsidian.scss.entity.DayAndTime;

import java.util.List;

public interface ConversationMapper {
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

    int selectAvgScoreByServiceId(int serviceId);

    int updateStopTime(@Param("conversationId") int conversationId,@Param("stopTime") long stopTime,@Param("score") int score);

    int updateStopTimeWithoutScore(@Param("conversationId") int conversationId,@Param("stopTime") long stopTime);

    int updateScore(@Param("conversationId") int conversationId ,@Param("score") int score);

    CustomerService selectLastChatServiceId(int clientId);

    int selectLastIdByClientId(int clientId);

    int updateServiceId(@Param("serviceId") int serviceId,@Param("conversationId") int conversationId);

    int selectNotFinishByServiceId(int serviceId);

    int updateAllStopTimeByServiceId(@Param("serviceId") int serviceId, @Param("stopTime") long stopTime);
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
    
}