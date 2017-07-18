package org.obsidian.scss.bean;

/**
 * Created by Lee on 2017/7/18.
 */
public class PullReq {
    private int serviceId;

    public PullReq(int serviceId) {
        this.serviceId = serviceId;
    }

    public PullReq() {
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
}
