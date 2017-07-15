package org.obsidian.scss.bean;

/**
 * Created by Lee on 2017/7/15.
 */
public class transferReq {

    private int transferType;

    private int targetId;

    public transferReq(int transferType, int targetId) {
        this.transferType = transferType;
        this.targetId = targetId;
    }

    public transferReq() {
    }

    @Override
    public String toString() {
        return "transferReq{" +
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
