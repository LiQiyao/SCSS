package org.obsidian.scss.bean;

/**
 * Created by Lee on 2017/7/26.
 */
public class ServiceOverview {

    private int conversationCount;

    private int conversationCountRank;

    private long onlineTime;

    private int onlineTimeRank;

    private double avgScore;

    private int avgScoreRank;

    private long clientCount;


    public ServiceOverview() {
    }

    public int getConversationCount() {
        return conversationCount;
    }

    public void setConversationCount(int conversationCount) {
        this.conversationCount = conversationCount;
    }

    public int getConversationCountRank() {
        return conversationCountRank;
    }

    public void setConversationCountRank(int conversationCountRank) {
        this.conversationCountRank = conversationCountRank;
    }

    public long getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(long onlineTime) {
        this.onlineTime = onlineTime;
    }

    public int getOnlineTimeRank() {
        return onlineTimeRank;
    }

    public void setOnlineTimeRank(int onlineTimeRank) {
        this.onlineTimeRank = onlineTimeRank;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    public int getAvgScoreRank() {
        return avgScoreRank;
    }

    public void setAvgScoreRank(int avgScoreRank) {
        this.avgScoreRank = avgScoreRank;
    }

    public long getClientCount() {
        return clientCount;
    }

    public void setClientCount(long clientCount) {
        this.clientCount = clientCount;
    }

}
