package org.obsidian.scss.bean;

/**
 * Created by Lee on 2017/7/20.
 */
public class ServiceStatus {

    private int conversationCount;

    private int queuePeopleCount;

    public ServiceStatus(int conversationCount, int queuePeopleCount) {
        this.conversationCount = conversationCount;
        this.queuePeopleCount = queuePeopleCount;
    }

    public ServiceStatus() {
    }

    @Override
    public String toString() {
        return "ServiceStatus{" +
                "conversationCount=" + conversationCount +
                ", queuePeopleCount=" + queuePeopleCount +
                '}';
    }

    public int getConversationCount() {
        return conversationCount;
    }

    public void setConversationCount(int conversationCount) {
        this.conversationCount = conversationCount;
    }

    public int getQueuePeopleCount() {
        return queuePeopleCount;
    }

    public void setQueuePeopleCount(int queuePeopleCount) {
        this.queuePeopleCount = queuePeopleCount;
    }
}
