package org.obsidian.scss.bean;

/**
 * Created by Lee on 2017/7/8.
 */
public class ClientChat {

    private int clientId;

    private String chatMsg;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getChatMsg() {
        return chatMsg;
    }

    public void setChatMsg(String chatMsg) {
        this.chatMsg = chatMsg;
    }

    public ClientChat() {
    }

    public ClientChat(int clientId, String chatMsg) {
        this.clientId = clientId;
        this.chatMsg = chatMsg;
    }

    @Override
    public String toString() {
        return "ClientChat{" +
                "clientId=" + clientId +
                ", chatMsg='" + chatMsg + '\'' +
                '}';
    }
}
