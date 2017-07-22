package org.obsidian.scss.service;

import com.google.gson.Gson;
import org.obsidian.scss.bean.Message;
import org.obsidian.scss.bean.ServiceChat;
import org.obsidian.scss.bean.ServiceStatus;
import org.obsidian.scss.conversation.ServiceWS;
import org.obsidian.scss.conversation.WebSocket;
import org.obsidian.scss.dao.ConversationMapper;
import org.obsidian.scss.entity.Client;
import org.obsidian.scss.entity.CustomerService;
import org.obsidian.scss.entity.ServiceGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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

    private Gson gson = new Gson();

    private ServiceGroupService serviceGroupService;

    @Autowired
    private CustomerServiceService customerServiceService;

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
    @Transactional
    public int joinQueueByGroupId(int groupId, int clientId){
        PriorityBlockingQueue<Client> queue = groupQueueMap.get(groupId);
        queue.add(new Client(clientId));
        this.updateServiceStatus(groupId,queue.size());
        return queue.size();
    }

    //返回加入组的Id
    @Transactional
    public int joinLeastClientQueue(int clientId){
        int leastClient = 999999999;
        int groupId = 0;
        System.out.println(groupQueueMap);
        for (Integer i : groupQueueMap.keySet()){
            System.out.println(groupQueueMap.get(i) + "!!");
            if (groupQueueMap.get(i).size() < leastClient){
                leastClient = groupQueueMap.get(i).size();
                groupId = i;
            }
        }
        groupQueueMap.get(groupId).add(new Client(clientId));
        this.updateServiceStatus(groupId, groupQueueMap.get(groupId).size());
        return groupId;
    }

    //从某个客服组队列中取出一个客户
    @Transactional
    public Client getClientFromQueue(int groupId){
        updateServiceStatus(groupId,groupQueueMap.get(groupId).size() - 1);
        return groupQueueMap.get(groupId).poll();
    }

    private void updateServiceStatus(int groupId, int queuePeopleCount){
        //更新客服状态
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setQueuePeopleCount(queuePeopleCount);
        for (WebSocket ws : ServiceWS.wsVector){
            int wsGroupId = customerServiceService.selectCustomerServiceByServiceId(ws.getServiceId()).getGroupId();
            if (wsGroupId == groupId){
                try {
                    ws.getSession().getBasicRemote().sendText(gson.toJson(new Message<ServiceStatus>(serviceStatus)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Map<Integer, PriorityBlockingQueue<Client>> getGroupQueueMap() {
        return groupQueueMap;
    }
}
