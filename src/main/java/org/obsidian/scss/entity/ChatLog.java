package org.obsidian.scss.entity;

public class ChatLog {
    private Integer chatLogId;

    private Integer conversationId;

    private Integer senderId;

    private Integer receiverId;

    private Integer contentType;

    private String content;

    private Long time;

    private Integer fromClient;

    public Integer getChatLogId() {
        return chatLogId;
    }

    public void setChatLogId(Integer chatLogId) {
        this.chatLogId = chatLogId;
    }

    public Integer getConversationId() {
        return conversationId;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getFromClient() {
        return fromClient;
    }

    public void setFromClient(Integer fromClient) {
        this.fromClient = fromClient;
    }

    public ChatLog(Integer conversationId, Integer senderId, Integer receiverId, Integer contentType, String content, Long time, Integer fromClient) {
        this.conversationId = conversationId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.contentType = contentType;
        this.content = content;
        this.time = time;
        this.fromClient = fromClient;
    }

    public ChatLog() {
    }

    @Override
    public String toString() {
        return "ChatLog{" +
                "chatLogId=" + chatLogId +
                ", conversationId=" + conversationId +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", contentType=" + contentType +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", fromClient=" + fromClient +
                '}';
    }
}