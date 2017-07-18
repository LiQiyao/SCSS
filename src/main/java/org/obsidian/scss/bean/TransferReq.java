package org.obsidian.scss.bean;

/**
 * Created by Lee on 2017/7/15.
 */
public class TransferReq {

    private int transferType;

    private int targetId;

    private int clientId;

    private int conversationId;

    public TransferReq() {
    }

    @Override
    public String toString() {
        return "TransferReq{" +
                "transferType=" + transferType +
                ", targetId=" + targetId +
                ", clientId=" + clientId +
                ", conversationId=" + conversationId +
                '}';
    }

    public TransferReq(int transferType, int targetId, int clientId, int conversationId) {
        this.transferType = transferType;
        this.targetId = targetId;
        this.clientId = clientId;
        this.conversationId = conversationId;
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

    public int getTransferType() {
        return transferType;
    }

    public void setTransferType(int transferType) {
        this.transferType = transferType;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }
}
