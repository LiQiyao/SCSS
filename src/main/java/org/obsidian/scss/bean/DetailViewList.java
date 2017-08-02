package org.obsidian.scss.bean;

import org.obsidian.scss.entity.AccessAndNumDuring;

import java.util.List;

/**
 * Created by hp on 2017/8/1.
 */
public class DetailViewList {
    long conversationCount;
    int clientNum;
    double avgScore;
    List<ScoreAndNum> scoreAndNums;
    List<AccessAndNumDuring> accessAndNumDurings;

    public long getConversationCount() {
        return conversationCount;
    }

    public void setConversationCount(long conversationCount) {
        this.conversationCount = conversationCount;
    }

    public int getClientNum() {
        return clientNum;
    }

    public void setClientNum(int clientNum) {
        this.clientNum = clientNum;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    public List<ScoreAndNum> getScoreAndNums() {
        return scoreAndNums;
    }

    public void setScoreAndNums(List<ScoreAndNum> scoreAndNums) {
        this.scoreAndNums = scoreAndNums;
    }

    public List<AccessAndNumDuring> getAccessAndNumDurings() {
        return accessAndNumDurings;
    }

    public void setAccessAndNumDurings(List<AccessAndNumDuring> accessAndNumDurings) {
        this.accessAndNumDurings = accessAndNumDurings;
    }
}
