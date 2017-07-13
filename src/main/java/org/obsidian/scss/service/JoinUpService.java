package org.obsidian.scss.service;

import org.obsidian.scss.entity.JoinUp;

import java.util.List;

/**
 * Created by Lee on 2017/7/13.
 */
public interface JoinUpService {

    JoinUp hasJoinedUp(int accessId, String account);

    int addJoinUp(int accessId, int clientId, long time, String account);

    List<JoinUp> getByClientId(int clientId);
}
