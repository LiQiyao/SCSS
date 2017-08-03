package org.obsidian.scss.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.Show;
import org.obsidian.scss.bean.NameList;
import org.obsidian.scss.entity.GroupWord;
import org.obsidian.scss.entity.ServiceGroup;
import org.obsidian.scss.service.CustomerServiceService;
import org.obsidian.scss.service.GroupWordService;
import org.obsidian.scss.service.ServiceGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hp on 2017/7/15.
 */
@Controller
public class ServerGroupController {
    @Autowired
    ServiceGroupService serviceGroupService;
    @Autowired
    GroupWordService groupWordService;
    @Autowired
    CustomerServiceService customerServiceService;
    
    /**
     * 添加组
     * @param 
     * @return
     */
    @RequestMapping("addGroup")
    @ResponseBody
    public Show addGroup(@RequestParam("groupName") String groupName,@RequestParam("groupTag") String groupTag){
        int re = 0;
        if(serviceGroupService.selectGroupByName(groupName) == null) {
            re = serviceGroupService.insertGroup(groupName);
        }
        
        Show show = new Show();
        if (re != 0){
            String [] arr = groupTag.split("\\s+");
            GroupWord groupWord = new GroupWord();
            groupWord.setGroupId(serviceGroupService.selectGroupByName(groupName).getGroupId());
            for(String ss : arr){
                groupWord.setWord(ss);
                groupWordService.selectTagIsExit(groupWord.getGroupId(),groupWord.getWord());
                groupWordService.insertGroupWord(groupWord);
            }
            show.setStatus(1);
        }else{
            show.setStatus(0);
            show.setMessage("插入失败");
        }
        return show;
    }
    /**
     * 添加组标签
     */
    @RequestMapping("addGroupTag")
    @ResponseBody
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
    @ResponseBody
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
    /**
     * 删除组
     */
    @RequestMapping("deleteGroupById")
    @ResponseBody
    public Show deleteGroup(@RequestParam("groupId") int groupId){
        Show show = new Show();
        int size = customerServiceService.selectCustomerServiceByGroup(groupId).size();
        int res = 0;
        if (size == 0 ){
            res = customerServiceService.deleteCustomerService(groupId);
        }
        if (res == 0){
            show.setStatus(0);
            show.setMessage("删除失败");
        }
        return show;
    }
    /**
     * 更新组
     */
    @RequestMapping("updateGroup")
    @ResponseBody
    public Show updateGroup(@RequestParam("group") String json){
        Show show = new Show();
        Gson gson = new Gson();
        ServiceGroup serviceGroup = gson.fromJson(json,new TypeToken<ServiceGroup>(){}.getType());
        int res = serviceGroupService.updateGroup(serviceGroup.getGroupId(),serviceGroup.getName());
        if (res == 0){
            show.setMessage("更新失败");
            show.setStatus(0);
        }
        return show;
    }
    
}
