package org.obsidian.scss.service.Impl;

import org.obsidian.scss.dao.ConversationMapper;
import org.obsidian.scss.entity.Conversation;
import org.obsidian.scss.entity.CustomerService;
import org.obsidian.scss.entity.DayAndTime;
import org.obsidian.scss.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Lee on 2017/7/13.
 */
@Service
public class ConversationServiceImpl implements ConversationService {

    @Autowired
    private ConversationMapper conversationMapper;

    @Transactional
    public int updateClientId(int conversationId, int clientId) {
        return conversationMapper.updateClientId(conversationId,clientId);
    }

    @Transactional
    public List<Conversation> getByClientId(int clientId) {
        return conversationMapper.selectByClientId(clientId);
    }

    @Transactional
    public List<Conversation> getByServiceId(int serviceId) {
        return conversationMapper.selectByServiceId(serviceId);
    }

    @Transactional
    public int getAvgScoreByServiceId(int serviceId) {
        return conversationMapper.selectAvgScoreByServiceId(serviceId);
    }

    @Transactional
    public int startConversation(int clientId, int serviceId, long startTime) {
        return conversationMapper.insertSelective(new Conversation(clientId,serviceId,startTime));
    }

    @Transactional
    public int endConversation(int conversationId, long stopTime, Integer score) {
        if (score == null){
            return conversationMapper.updateStopTimeWithoutScore(conversationId,stopTime);
        }
        return conversationMapper.updateStopTime(conversationId, stopTime, score);
    }


    @Transactional
    public int getLastIdByClientId(int clientId) {
        return conversationMapper.selectLastIdByClientId(clientId);
    }

    public CustomerService getLastChatServiceId(int clientId) {
        return conversationMapper.selectLastChatServiceId(clientId);
    }

    public int resetServiceId(int serviceId, int conversationId) {
        return conversationMapper.updateServiceId(serviceId, conversationId);
    }

    public int finishAllByServiceId(int serviceId) {
        return conversationMapper.updateAllStopTimeByServiceId(serviceId, new Date().getTime());
    }

    /**
     * create By cjn
     * @return
     */
    @Transactional
    public int selectConversationNotFinish() {
        return conversationMapper.selectConversationNotFinish();
    }
    
    @Transactional
    public List<DayAndTime> selectRecentMonth() {
//        System.out.println(System.currentTimeMillis());
        return conversationMapper.selectRecentMonth(System.currentTimeMillis());
    }

    public List<DayAndTime> selectRecentWeekend() {
//        System.out.println(System.currentTimeMillis());
        return conversationMapper.selectRecentWeekend(System.currentTimeMillis());
    }
    @Transactional
    public List<DayAndTime> selectRecentHour() {
        return conversationMapper.selectRecentHour(System.currentTimeMillis());
    }
    @Transactional
    public List<DayAndTime> selectRecentMinute() {
        System.out.println(System.currentTimeMillis());
        return conversationMapper.selectRecentMinute(System.currentTimeMillis());
    }
    @Transactional
    public List<DayAndTime> selectRecentPeopleMonth(int serviceId) {
        return conversationMapper.selectRecentPeopleMonth(System.currentTimeMillis(),serviceId);
    }
    @Transactional
    public List<DayAndTime> selectRecentPeopleWeekend(int serviceId) {
       return conversationMapper.selectRecentPeopleWeekend(System.currentTimeMillis(),serviceId);
    }
    @Transactional
    public List<DayAndTime> selectRecentPeopleHour(int serviceId) {
        return conversationMapper.selectRecentPeopleHour(System.currentTimeMillis(),serviceId);
    }
}
