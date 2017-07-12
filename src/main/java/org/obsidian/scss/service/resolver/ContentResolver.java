package org.obsidian.scss.service.resolver;

import org.obsidian.scss.bean.ClientChat;

/**
 * 处理从前端接收的消息
 * Created by Lee on 2017/7/12.
 */
public interface ContentResolver{
    String resolve(Object object);
}
