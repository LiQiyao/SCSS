package org.obsidian.scss.controller;

import org.obsidian.scss.bean.InitBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.io.*;

/**
 * Created by Lee on 2017/7/7.
 */
@Controller
public class InitController implements ApplicationListener<ContextRefreshedEvent> {

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("服务器启动！======开始读取配置文件");
        InitBean initBean = new InitBean(1L, 2L, "耀眼科技有限公司");
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:\\init.txt"));
            objectOutputStream.writeObject(initBean);
            objectOutputStream.close();
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D:\\init.txt"));
            try {
                InitBean initBean2 = (InitBean) objectInputStream.readObject();
                System.out.println(initBean2);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
