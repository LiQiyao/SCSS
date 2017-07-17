package org.obsidian.scss.bean;

/**
 * Created by Lee on 2017/7/15.
 */
public class TransferReq {

    private int transferType;

    private int targetId;

    public TransferReq(int transferType, int targetId) {
        this.transferType = transferType;
        this.targetId = targetId;
    }

    public TransferReq() {
    }

    @Override
    public String toString() {
        return "TransferReq{" +
                "transferType=" + transferType +
                ", targetId=" + targetId +
                '}';
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
