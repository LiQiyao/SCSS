package org.obsidian.scss.service.resolver;

import com.google.gson.Gson;
import org.obsidian.scss.bean.ClientChat;
import org.obsidian.scss.bean.Message;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * Created by Lee on 2017/7/12.
 */
@Service
public class ResolverFactory implements ApplicationContextAware{

    private ApplicationContext applicationContext;

    private ContentResolver contentResolver;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public String doAction(String msg){
        Gson gson = new Gson();
        Message message = gson.fromJson(msg, Message.class);
        String type = message.getType() + "Resolver";
        Object object = message.getContent();
        System.out.println(object + "!!" + object.getClass());
        System.out.println("??");
        return ((ContentResolver)applicationContext.getBean(type)).resolve(message.getContent());
    }
}
