package org.obsidian.scss.service.Impl;

import org.obsidian.scss.bean.AvgScoreList;
import org.obsidian.scss.bean.ScoreAndNum;
import org.obsidian.scss.dao.ConversationMapper;
import org.obsidian.scss.dao.CustomerServiceMapper;
import org.obsidian.scss.entity.*;
import org.obsidian.scss.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Lee on 2017/7/13.
 */
@Service
public class ConversationServiceImpl implements ConversationService {

    @Autowired
    private ConversationMapper conversationMapper;

    @Autowired
    private CustomerServiceMapper customerServiceMapper;

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
    public double getAvgScoreByServiceId(int serviceId) {
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
    public Integer getLastIdByClientId(int clientId) {
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
//获取今日会话数
    public int getTodayConversationCount(int serviceId) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        long dayStart = calendar.getTime().getTime();
        Calendar calendar1 = new GregorianCalendar();
        calendar1.set(Calendar.HOUR_OF_DAY, 23);
        calendar1.set(Calendar.MINUTE, 59);
        calendar1.set(Calendar.SECOND, 59);
        long dayEnd = calendar1.getTime().getTime();
        return conversationMapper.selectTodayConversationCount(serviceId, dayStart,dayEnd);
    }
//获取今日会话数排名
    public int getTodayConversationCountRank(int serviceId) {
        List<CustomerService> list = customerServiceMapper.selectAll();
        ConversationCount[] conversationCounts = new ConversationCount[list.size()];
        int tmp;
        for (int i = 0, size = list.size(); i < size; i++){
            conversationCounts[i].setServiceId((tmp = list.get(i).getServiceId()));
            conversationCounts[i].setCount(getTodayConversationCount(tmp));
        }
        Arrays.sort(conversationCounts);
        for (int i = 0; i < conversationCounts.length; i++){
            if (conversationCounts[i].getServiceId() == serviceId){
                return i + 1;
            }
        }
        return 0;
    }


    @Transactional
    public int selectClientIdByConversationId(int conversationId) {
        return conversationMapper.selectClientIdByConversationId(conversationId);
    }

    public int getAvgScoreRankByServiceId(int serviceId){
        List<AvgScoreList> list = conversationMapper.selectAllAvgScore();
        int rank = 0;
        for(int i=0;i<list.size();i++){
            if(list.get(i).getServiceId() == serviceId){
                rank = i + 1;
                for(int j=i;j>0;j--){
                    if(list.get(j).getAvgScore() == list.get(i).getAvgScore()){
                        rank = j + 1;
                    }
                }
            }
        }
        return rank;
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

    @Transactional
    public long selectDuringConverSationNum(long startTime,long stopTime) {
        ConversationExample example =  new ConversationExample();
        ConversationExample.Criteria criteria = example.createCriteria();
        if (startTime!=0)
            criteria.andStartTimeGreaterThanOrEqualTo(startTime);
        if (stopTime!=0)
            criteria.andStopTimeLessThanOrEqualTo(stopTime);
        return conversationMapper.selectByExample(example).size();
    }

    @Transactional
    public double avgGrades(long startTime, long stopTime) {
        double res = conversationMapper.selectAvgScore(startTime,stopTime);
        return res;
    }

    @Transactional
    public List<ScoreAndNum> scoreAndNum(long startTime, long stopTime) {
        return conversationMapper.selectScore(startTime,stopTime);
    }
    
    @Transactional
    public List<ScoreAndRank> selectScoreAndRank(long startTime, long stopTime, int serviceId) {
        return conversationMapper.selectScoreRank(startTime,stopTime,serviceId);
    }
    
    @Transactional
    public List<ConNumAndRank> selectConNumAndRank(long startTime, long stopTime, int serviceId) {
        return conversationMapper.selectConNumAndRank(startTime,stopTime,serviceId);
    }

    @Transactional
    public List<Client> selectClientChatList(int serviceId) {
        return conversationMapper.selectClientChatList(serviceId);
    }
}
class ConversationCount implements Comparable<ConversationCount>{

    private int serviceId;

    private int count;

    public int compareTo(ConversationCount o) {
        return o.count - this.count;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
}
