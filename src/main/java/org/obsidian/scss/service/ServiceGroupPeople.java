package org.obsidian.scss.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lee on 2017/9/7.
 */
@Service
public class ServiceGroupPeople {

    private HashMap<Integer, List<Integer>> group = new HashMap<Integer, List<Integer>>();

    public ServiceGroupPeople(){
        for (int i = 1; i <= 10; i++){
            group.put(i, new ArrayList<Integer>());
        }
    }

    public void joinGroup(int groupId, int serviceId){
        List<Integer> list = group.get(groupId);
        list.add(serviceId);
    }

    public boolean groupIsEmpty(int groupId){
        if (group.get(groupId).size() == 0){
            return true;
        }
        return false;
    }

    public void quit(int serviceId){
        for (List<Integer> list : group.values()){
            for (int i : list){
                if (i == serviceId){
                    list.remove(new Integer(i));
                }
            }
        }
    }

    public int findNotEmptyGroup(){
        for (Integer i : group.keySet()){
            if (group.get(i).size() != 0){
                return i;
            }
        }
        return 2;
    }
}
