package org.obsidian.scss.service.Impl;

import org.obsidian.scss.dao.ServiceGroupMapper;
import org.obsidian.scss.entity.ServiceGroup;
import org.obsidian.scss.entity.ServiceGroupExample;
import org.obsidian.scss.service.ServiceGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
@Service
public class ServiceGroupServiceImpl implements ServiceGroupService {

    @Autowired
    private ServiceGroupMapper serviceGroupMapper;

    /**
     * 新增客服组
     * @param name
     * @return
     */
    @Transactional
    public int insertGroup(String name) {
        ServiceGroup serviceGroup = new ServiceGroup();
        serviceGroup.setName(name);
        int insertSum = serviceGroupMapper.insertSelective(serviceGroup);
        return insertSum;
    }

    @Transactional
    public int deleteGroup(int groupId) {
        int deleteSum = serviceGroupMapper.deleteServiceGroup(groupId);
        return deleteSum;
    }

    @Transactional
    public int updateGroup(int groupId, String name) {
        ServiceGroup serviceGroup = new ServiceGroup();
        serviceGroup.setName(name);
        ServiceGroupExample example = new ServiceGroupExample();
        example.or().andGroupIdEqualTo(groupId);
        int updateSum = serviceGroupMapper.updateByExampleSelective(serviceGroup,example);
        return updateSum;
    }

    /**
     * 按客服组编号查询客服组
     * @param groupId
     * @return
     */
    @Transactional
    public ServiceGroup selectGroupByGroupId(int groupId) {
        ServiceGroupExample example = new ServiceGroupExample();
        example.or().andGroupIdEqualTo(groupId);
        List<ServiceGroup> list = serviceGroupMapper.selectByExample(example);
        if(list.size() == 0 || list == null){
            return null;
        }
        return list.get(0);
    }

    /**
     * 按客服组名查询客服组
     * @param name
     * @return
     */
    @Transactional
    public ServiceGroup selectGroupByName(String name){
        ServiceGroupExample example = new ServiceGroupExample();
        example.or().andNameEqualTo(name);
        List<ServiceGroup> list = serviceGroupMapper.selectByExample(example);
        if(list.size() == 0 || list == null){
            return null;
        }
        return list.get(0);
    }


    /**
     * 查询所有客服组
     * @return
     */
    @Transactional
    public List<ServiceGroup> selectAllGroup() {
        ServiceGroupExample example = new ServiceGroupExample();
        List<ServiceGroup> list = serviceGroupMapper.selectByExample(example);
        if(list.size() == 0 || list == null){
            return null;
        }
        return list;
    }
}
