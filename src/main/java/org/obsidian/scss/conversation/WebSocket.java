package org.obsidian.scss.conversation;

import javax.websocket.Session;
import java.util.Vector;

/**
 * Created by Lee on 2017/7/14.
 */
public interface WebSocket {

    Session getSession();

    Vector<WebSocket> getWsVector();

    int getClientId();

    void setClientId(int clientId);

    int getServiceId();

    void setServiceId(int serviceId);
}
