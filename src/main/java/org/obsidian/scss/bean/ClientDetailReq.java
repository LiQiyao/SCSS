package org.obsidian.scss.bean;

/**
 * Created by Administrator on 2017/7/15.
 */
public class ClientDetailReq {

    private int clientId;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public ClientDetailReq(int clientId) {
        this.clientId = clientId;
    }

    public ClientDetailReq() {
    }

    @Override
    public String toString() {
        return "ClientDetailReq{" +
                "clientId=" + clientId +
                '}';
    }
}
