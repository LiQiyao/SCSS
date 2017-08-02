package org.obsidian.scss.entity;

/**
 * Created by hp on 2017/8/1.
 */
public class ChatLogAndSendRecInfo {
    private Integer chatLogId;

    private Integer conversationId;

    private Integer senderId;
    
    private Integer receiverId;

    private Integer contentType;

    private String content;

    private Long time;

    private Integer fromClient;
    
    private String senderName;
    
    private  String receiverName;

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
        this.content = content;
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

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
}
