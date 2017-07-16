package org.obsidian.scss.controller;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.Show;
import org.obsidian.scss.bean.nameList;
import org.obsidian.scss.entity.GroupWord;
import org.obsidian.scss.entity.ServiceGroup;
import org.obsidian.scss.service.GroupWordService;
import org.obsidian.scss.service.ServiceGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hp on 2017/7/15.
 */
@Controller
public class ServerGroupController {
    @Autowired
    ServiceGroupService serviceGroupService;
    @Autowired
    GroupWordService groupWordService;
    
    /**
     * 添加组
     * @param groups
     * @return
     */
    @RequestMapping("addGroup")
    public Show addGroup(@RequestParam("groups") String getJson){
        Gson gson = new Gson();
        List<nameList> groups = gson.fromJson(getJson,new TypeToken<List<nameList>>(){}.getType());
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
    /**
     * 添加组标签
     */
    @RequestMapping("addGroupTag")
    public Show addGroupTag(@RequestParam("groupwords") String getJson){
        Gson gson = new Gson();
        List<GroupWord> groupwords = gson.fromJson(getJson,new TypeToken<List<GroupWord>>(){}.getType());
        int res = 0 ;
        for (int i=0 ; i < groupwords.size(); i++){
            int re = groupWordService.insertGroupWord(groupwords.get(i));
            if (re ==1){
                res++;
            }
        }
        Show show = new Show();
        if (res == groupwords.size()){
            show.setStatus(1);
        }else{
            show.setStatus(0);
            show.setMessage("请求插入"+groupwords.size()+"条,"+"成功插入"+ res +"条。");
        }
        return show;
    }
    /**
     * 删除标签组
     */
    @RequestMapping("deleteGroupTag")
    public Show deleteGroupTag(@RequestParam("groupwords") String getJson){
        Gson gson = new Gson();
        GroupWord groupword = gson.fromJson(getJson,new TypeToken<GroupWord>(){}.getType());
        int res = groupWordService.deleteGroupWord(groupword);
        Show show = new Show();
        if (res == 0 ){
            show.setStatus(0);
            show.setMessage("删除失败!");
        }
        return show;
    }
}
