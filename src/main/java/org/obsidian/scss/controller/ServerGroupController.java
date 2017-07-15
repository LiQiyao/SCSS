package org.obsidian.scss.controller;

import java.util.List;
import org.obsidian.scss.bean.Show;
import org.obsidian.scss.entity.ServiceGroup;
import org.obsidian.scss.service.ServiceGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hp on 2017/7/15.
 */
@Controller
public class ServerGroupController {
    @Autowired
    ServiceGroupService serviceGroupService;
    
    @RequestMapping("addGroup")
    public Show addGroup(@RequestBody List<ServiceGroup> groups){
        int res = 0 ;
        for (int i=0 ; i < groups.size(); i++){
           int re = serviceGroupService.insertGroup(groups.get(i).getName());
            if (re ==1){
                res++;
            }
        }
        Show show = new Show();
        if (res == groups.size()){
            show.setStatus(1);
        }else{
            show.setStatus(0);
            show.setMessage("请求插入"+groups.size()+"条,"+"成功插入"+ res +"条。");
        }
        return show;
    }
    
}
