package org.obsidian.scss.service;

import org.obsidian.scss.bean.GroupAndPersonNum;
import org.obsidian.scss.entity.ServiceGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
public interface ServiceGroupService {

    int insertGroup(String name);

    int deleteGroup(int groupId);

    int updateGroup(int groupId,String name);

    ServiceGroup selectGroupByGroupId(int groupId);

    ServiceGroup selectGroupByName(String name);

    List<ServiceGroup> selectAllGroup();
    
    List<GroupAndPersonNum> selectGroupPersonNum(); 
}
