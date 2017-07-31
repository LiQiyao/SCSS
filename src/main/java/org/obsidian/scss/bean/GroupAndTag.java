package org.obsidian.scss.bean;

import org.obsidian.scss.entity.GroupWord;
import org.obsidian.scss.entity.ServiceGroup;
import  java.util.*;
/**
 * Created by hp on 2017/7/30.
 */
public class GroupAndTag {
    public ServiceGroup serviceGroup;
    public List<GroupWord> groupWords;

    public ServiceGroup getServiceGroup() {
        return serviceGroup;
    }

    public void setServiceGroup(ServiceGroup serviceGroup) {
        this.serviceGroup = serviceGroup;
    }

    public List<GroupWord> getGroupWords() {
        return groupWords;
    }

    public void setGroupWords(List<GroupWord> groupWords) {
        this.groupWords = groupWords;
    }
}
