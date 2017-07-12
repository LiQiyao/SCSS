package org.obsidian.scss.service.resolver;

import org.obsidian.scss.bean.ClientChat;
import org.springframework.stereotype.Service;

/**
 * Created by Lee on 2017/7/12.
 */
@Service
public class ClientChatResolver implements ContentResolver {
    public String resolve(Object object) {
        System.out.println(object + "!!");
        return object.toString();
    }
}
