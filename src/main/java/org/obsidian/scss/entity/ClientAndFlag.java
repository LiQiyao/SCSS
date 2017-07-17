package org.obsidian.scss.entity;

import java.util.List;

/**
 * Created by hp on 2017/7/17.
 */
public class ClientAndFlag {
    Client client;
    List<Flag> flags;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Flag> getFlags() {
        return flags;
    }

    public void setFlags(List<Flag> flags) {
        this.flags = flags;
    }
}
