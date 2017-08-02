package org.obsidian.scss.entity;

/**
 * Created by hp on 2017/8/1.
 */
public class TimeAndRank {
    public int serviceId;
    public long tottime;
    public int timerank;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public long getTottime() {
        return tottime;
    }

    public void setTottime(long tottime) {
        this.tottime = tottime;
    }

    public int getTimerank() {
        return timerank;
    }

    public void setTimerank(int timerank) {
        this.timerank = timerank;
    }
}
