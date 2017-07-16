package org.obsidian.scss.service.Impl;

import org.obsidian.scss.dao.JoinUpMapper;
import org.obsidian.scss.entity.JoinUp;
import org.obsidian.scss.service.JoinUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Lee on 2017/7/13.
 */
@Service
public class JoinUpServiceImpl implements JoinUpService {

    @Autowired
    private JoinUpMapper joinUpMapper;

    @Transactional
    public int updateJoinUp(int clientId,String qq, String wx, String weibo, String taobao, String alipay) {
        return joinUpMapper.updateJoinUp(clientId,qq,wx,weibo,taobao,alipay);
    }

    /**
     *根据接入方式和接入账号判断该用户之前是否接入过
     */
    public JoinUp hasJoinedUp(int accessId, String account) {
        return joinUpMapper.selectByRecord(accessId, account);
    }

    /**
     * 对于新接入的用户添加接入记录
     */
    public int addJoinUp(int accessId,int clientId, long time, String account) {
        return joinUpMapper.insertSelective(new JoinUp(accessId, clientId, time, account));
    }

    /**
     *根据用户编号查找所有的接入记录
     */
    public List<JoinUp> getByClientId(int clientId) {
        return joinUpMapper.selectByClientId(clientId);
    }

    public JoinUp getLastByClientId(int clientId) {
        return joinUpMapper.selectLastJoinByClientId(clientId);
    }

}
