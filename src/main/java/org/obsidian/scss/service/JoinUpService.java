package org.obsidian.scss.service;

import org.obsidian.scss.entity.AccessAndNumDuring;
import org.obsidian.scss.entity.JoinUp;

import java.util.List;

/**
 * Created by Lee on 2017/7/13.
 */
public interface JoinUpService {
    String selectAccountByAccessIdAndClientId(int accessId, int clientId);

    int selectByAccessIdAndClientId(int accessId,int clientId);

    int insertJoinUp(int accessId,int clientId,String account);

    int updateJoinUp(int accessId,int clientId,String account);

    int deleteJoinUp(int accessId,int clientId);

    Long getTodayClientCount();

    int updateAllJoinUp(int clientId,String qq,String wx,String weibo);

    List<JoinUp> hasJoinedUp(int accessId, String account);

    int addJoinUp(int accessId, int clientId, long time, String account);

    List<JoinUp> getByClientId(int clientId);

    JoinUp getLastByClientId(int clientId);
    
    int selectDuringClientNum(long startTime,long stopTime);
    
    List<AccessAndNumDuring> selectAccess(long startTime,long stopTime);
}
