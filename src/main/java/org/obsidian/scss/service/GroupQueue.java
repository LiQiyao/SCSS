package org.obsidian.scss.service;

import org.obsidian.scss.entity.Client;
import org.obsidian.scss.entity.ServiceGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Lee on 2017/7/8.
 */
@Service
public class GroupQueue implements Serializable {


    private ServiceGroupService serviceGroupService;

    private Map<Integer, PriorityBlockingQueue<Client>> groupQueueMap = new HashMap<Integer, PriorityBlockingQueue<Client>>();

    @Autowired
    public GroupQueue(ServiceGroupService serviceGroupService){
        this.serviceGroupService = serviceGroupService;
        List<ServiceGroup> list = serviceGroupService.selectAllGroup();
        for (ServiceGroup serviceGroup : list){
            groupQueueMap.put(serviceGroup.getGroupId(), new PriorityBlockingQueue<Client>());
        }
    }

    //根据客服组id加入客户到队列
    public int joinQueueBygroupId(int groupId, int clientId){
        PriorityBlockingQueue<Client> queue = groupQueueMap.get(groupId);
        queue.add(new Client(clientId));
        return queue.size();
    }

    //返回加入组的Id
    public int joinLeastClientQueue(int clientId){
        int leastClient = 999999999;
        int groupId = 0;
        for (Integer i : groupQueueMap.keySet()){
            if (groupQueueMap.get(i).size() < leastClient){
                leastClient = groupQueueMap.get(i).size();
                groupId = i;
            }
        }
        groupQueueMap.get(groupId).add(new Client(clientId));
        return groupId;
    }

    //从某个客服组队列中取出一个客户
    public Client getClientFromQueue(int groupId){
        return groupQueueMap.get(groupId).poll();
    }
}
