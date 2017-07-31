package org.obsidian.scss.bean;

/**
 * Created by Lee on 2017/7/15.
 */
public class ServiceChat {

    private int conversationId;

    private int clientId;

    private int contentType;

    private String content;

    private long time;

    private int serviceId;

/*    public ServiceChat(int conversationId, int clientId, int contentType, String content, long time) {
        this.conversationId = conversationId;
        this.clientId = clientId;
        this.contentType = contentType;
        this.content = content;
        this.time = time;
    }*/

    public ServiceChat(int conversationId, int clientId, int contentType, String content, long time, int serviceId) {
        this.conversationId = conversationId;
        this.clientId = clientId;
        this.contentType = contentType;
        this.content = content;
        this.time = time;
        this.serviceId = serviceId;
    }

    public ServiceChat() {
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String toString() {
        return "ServiceChat{" +
                "conversationId=" + conversationId +
                ", clientId=" + clientId +
                ", contentType=" + contentType +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", serviceId=" + serviceId +
                '}';
    }
}
