package org.obsidian.scss.service;

import org.obsidian.scss.entity.JoinUp;

import java.util.List;

/**
 * Created by Lee on 2017/7/13.
 */
public interface JoinUpService {
    int updateJoinUp(int clientId,String qq,String wx,String weibo,String taobao,String alipay);

    JoinUp hasJoinedUp(int accessId, String account);

    int addJoinUp(int accessId, int clientId, long time, String account);

    List<JoinUp> getByClientId(int clientId);

    JoinUp getLastByClientId(int clientId);
}
