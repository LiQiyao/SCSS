package org.obsidian.scss.service.resolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.ClientChat;
import org.obsidian.scss.bean.Message;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

/**
 * Created by Lee on 2017/7/12.
 */
@Service
public class ResolverFactory implements ApplicationContextAware{

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public String doAction(String msgJson){
        System.out.println("doAction");
        Gson gson = new Gson();
        Message message = gson.fromJson(msgJson, Message.class);
        String type = message.getType() + "Resolver";
        System.out.println(type);
        return ((ContentResolver)applicationContext.getBean(type)).resolve(msgJson);
    }
}
