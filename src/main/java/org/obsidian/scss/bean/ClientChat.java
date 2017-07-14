package org.obsidian.scss.bean;

/**
 * Created by Lee on 2017/7/8.
 */
public class ClientChat {

    private int conversationId;

    private int clientId;

    private int contentType;

    private String content;

    private long time;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
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

    public ClientChat(int conversationId, int clientId, int contentType, String content, long time) {
        this.conversationId = conversationId;
        this.clientId = clientId;
        this.contentType = contentType;
        this.content = content;
        this.time = time;
    }

    public ClientChat() {
    }

    @Override
    public String toString() {
        return "ClientChat{" +
                "conversationId=" + conversationId +
                ", clientId=" + clientId +
                ", contentType=" + contentType +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }
}
