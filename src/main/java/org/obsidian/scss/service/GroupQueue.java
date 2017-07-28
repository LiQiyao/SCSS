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
import java.util.*;

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

    private Map<Integer, Queue<Client>> groupQueueMap = new HashMap<Integer, Queue<Client>>();

    @Autowired
    public GroupQueue(ServiceGroupService serviceGroupService){
        this.serviceGroupService = serviceGroupService;
        List<ServiceGroup> list = serviceGroupService.selectAllGroup();
        for (ServiceGroup serviceGroup : list){
            groupQueueMap.put(serviceGroup.getGroupId(), new LinkedList<Client>());
        }
    }

    //根据客服组id加入客户到队列
    @Transactional
    public int joinQueueByGroupId(int groupId, int clientId){
        Queue<Client> queue = groupQueueMap.get(groupId);
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
        System.out.println("加入客服组队列" + groupId + groupQueueMap.get(groupId));
        Queue<Client> queue =  groupQueueMap.get(groupId);
        System.out.println("队列" + queue.poll());
        queue.offer(new Client(clientId));
        System.out.println("客服队列人数" + groupQueueMap.get(groupId).size());
        this.updateServiceStatus(groupId, groupQueueMap.get(groupId).size());
        System.out.println("更新客服状态完毕");
        return groupId;
    }

    //从某个客服组队列中取出一个客户
    @Transactional
    public Client getClientFromQueue(int groupId){
        if (groupQueueMap.get(groupId).size() == 0){
            return null;
        }
        updateServiceStatus(groupId,groupQueueMap.get(groupId).size() - 1);
        return groupQueueMap.get(groupId).poll();
    }

    private void updateServiceStatus(int groupId, int queuePeopleCount){
        //更新客服状态
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setQueuePeopleCount(queuePeopleCount);
        System.out.println(groupId + " " + queuePeopleCount);
        for (WebSocket ws : ServiceWS.wsVector){
            int wsGroupId = customerServiceService.selectCustomerServiceByServiceId(ws.getServiceId()).getGroupId();
            if (wsGroupId == groupId){
                System.out.println("wsGroupId" + wsGroupId);
                try {
                    ws.getSession().getBasicRemote().sendText(gson.toJson(new Message<ServiceStatus>(serviceStatus)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Transactional
    public void removeByClient(int clientId){

        for (Integer i : groupQueueMap.keySet()){
            LinkedList<Client> linkedList = (LinkedList<Client>) groupQueueMap.get(i);
            for (Client c : linkedList){
                if (c.getClientId() == clientId){
                    linkedList.remove(c);
                    for (WebSocket ws : ServiceWS.wsVector){
                        int wsGroupId = customerServiceService.selectCustomerServiceByServiceId(ws.getServiceId()).getGroupId();
                        if (wsGroupId == i){
                            System.out.println("wsGroupId" + wsGroupId);
                            try {
                                ServiceStatus serviceStatus = new ServiceStatus();
                                serviceStatus.setQueuePeopleCount(linkedList.size());
                                ws.getSession().getBasicRemote().sendText(gson.toJson(new Message<ServiceStatus>(serviceStatus)));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public Map<Integer, Queue<Client>> getGroupQueueMap() {
        return groupQueueMap;
    }
}
