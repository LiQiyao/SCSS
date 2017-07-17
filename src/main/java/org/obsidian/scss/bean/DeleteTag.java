package org.obsidian.scss.bean;

/**
 * Created by Administrator on 2017/7/17.
 */
public class DeleteTag {
    private int clientId;

    private String tag;

    public DeleteTag(int clientId, String tag) {
        this.clientId = clientId;
        this.tag = tag;
    }

    public DeleteTag() {
    }

    @Override
    public String toString() {
        return "DeleteTag{" +
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
