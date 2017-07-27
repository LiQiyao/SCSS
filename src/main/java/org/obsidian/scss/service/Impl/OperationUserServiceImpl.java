package org.obsidian.scss.service.Impl;

import org.obsidian.scss.dao.OperationUserMapper;
import org.obsidian.scss.entity.OperationUser;
import org.obsidian.scss.entity.OperationUserExample;
import org.obsidian.scss.service.OperationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */
@Service
public class OperationUserServiceImpl implements OperationUserService {

    @Autowired
    private OperationUserMapper operationUserMapper;

    /**
     * 新增运维人员
     * @param username
     * @param password
     * @return
     */
    @Transactional
    public int insertUser(String username, String password) {
        OperationUser operationUser = new OperationUser();
        operationUser.setUsername(username);
        operationUser.setPassword(password);
        int insertSum = operationUserMapper.insertSelective(operationUser);
        return insertSum;
    }

    /**
     * 删除运维人员
     * @param userId
     * @return
     */
    @Transactional
    public int deleteUser(int userId) {
        int deleteSum = operationUserMapper.deleteOperationUser(userId);
        return deleteSum;
    }

    /**
     * 更新运维人员
     * @param userId
     * @param password
     * @return
     */
    @Transactional
    public int updateUser(int userId, String password) {
        OperationUser operationUser = new OperationUser();
        operationUser.setUserId(userId);
        operationUser.setPassword(password);
        int updateSum = operationUserMapper.updateOperationUser(operationUser);
        return updateSum;
    }

    /**
     * 按用户名查找运维人员ID
     * @param username
     * @return
     */
    @Transactional
    public List<OperationUser> selectUserIdByUsername(String username) {
        List<OperationUser> list = operationUserMapper.selectUserIdByUsername("%" + username + "%");
        if(list.size() == 0 || list == null) {
            return null;
        }
        return list;
    }

    /**
     * 按用户ID查找用户名
     * @param userId
     * @return
     */
    @Transactional
    public String selectUsernameByUserId(int userId) {
        String username = operationUserMapper.selectUsernameByUserId(userId);
        if(username.length() <= 0 || username.equals("")){
            return null;
        }
        return username;
    }

    /**
     * 查询所有运维人员
     * @return
     */
    @Transactional
    public List<OperationUser> selectAllUser() {
        OperationUserExample example = new OperationUserExample();
        List<OperationUser> list = operationUserMapper.selectByExample(example);
        if(list.size() == 0 || list == null) {
            return null;
        }
        return list;
    }

    public OperationUser selectUser(int userId, String password) {
        OperationUserExample example = new OperationUserExample();
        OperationUserExample.Criteria criteria = example.createCriteria();
        criteria.andPasswordEqualTo(password);
        List<OperationUser> list = operationUserMapper.selectByExample(example);
        criteria.andUserIdEqualTo(userId);
        if(list.size() == 0 || list == null) {
            return null;
        }
        return list.get(0);
    }
}
