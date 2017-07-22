package org.obsidian.scss.bean;

import org.obsidian.scss.entity.ServiceGroup;
import org.obsidian.scss.service.ServiceGroupService;

/**
 * Created by hp on 2017/7/20.
 */
public class GroupAndPersonNum {
    public ServiceGroup serviceGroup;
    public int total;

    public ServiceGroup getServiceGroup() {
        return serviceGroup;
    }

    public void setServiceGroup(ServiceGroup serviceGroup) {
        this.serviceGroup = serviceGroup;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
