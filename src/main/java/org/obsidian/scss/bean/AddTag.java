package org.obsidian.scss.bean;

/**
 * Created by Administrator on 2017/7/17.
 */
public class AddTag {

    private int clientId;

    private String tag;

    public AddTag(int clientId, String tag) {
        this.clientId = clientId;
        this.tag = tag;
    }

    public AddTag() {
    }

    @Override
    public String toString() {
        return "AddTag{" +
                "clientId=" + clientId +
                ", tag='" + tag + '\'' +
                '}';
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
