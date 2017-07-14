package org.obsidian.scss.entity;

/**
 * Created by hp on 2017/7/14.
 */
public class DayAndTime {
    public long time;//时间戳
    public int times;//出现次数

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
