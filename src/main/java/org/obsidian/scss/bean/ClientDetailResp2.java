package org.obsidian.scss.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
public class ClientDetailResp2 extends ClientDetailResp{

    private int conversationId;

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public ClientDetailResp2(){
    }

    public ClientDetailResp2(int clientId, String clientName, int sex, Long phoneNum, String email, String wx, String qq, String weibo, String address, List<String> tagList, List<String> unusedTagList, int conversationId) {
        super(clientId, clientName, sex, phoneNum, email, wx, qq, weibo, address, tagList, unusedTagList);
        this.conversationId = conversationId;
    }
}
