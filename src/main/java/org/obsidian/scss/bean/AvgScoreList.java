package org.obsidian.scss.bean;

/**
 * Created by Administrator on 2017/7/26.
 */
public class AvgScoreList {

    private int serviceId;

    private double avgScore;

    public AvgScoreList() {
    }

    public AvgScoreList(int serviceId, double avgScore) {
        this.serviceId = serviceId;
        this.avgScore = avgScore;
    }

    @Override
    public String toString() {
        return "AvgScoreList{" +
                "serviceId=" + serviceId +
                ", avgScore=" + avgScore +
                '}';
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }
}
