package org.obsidian.scss.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.IdList;
import org.obsidian.scss.bean.PeopleDayAndTime;
import org.obsidian.scss.bean.Show;
import org.obsidian.scss.entity.CustomerService;
import org.obsidian.scss.service.ConversationService;
import org.obsidian.scss.service.CustomerServiceService;
import org.obsidian.scss.service.ServiceGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Cjn on 2017/7/14.
 * 客服人员管理控制类
 */
@Controller
public class CustomerServiceManagementController {
    @Autowired
    ServiceGroupService serviceGroupService;
    @Autowired
    CustomerServiceService customerServiceService;
    @Autowired
    ConversationService conversationService;
    
    /**
     * Create By Cjn
     * 获取公司中所有的部门
     * @param model
     * @return
     */
    @RequestMapping(value = "customerServiceManagement")
    public String management(Model model){
        model.addAttribute("allGroup",serviceGroupService.selectAllGroup());//调用cw
        return "customerServiceManagement";
    }

    /**
     * Create By Cjn
     * 获取某个部门中所有的成员
     * @param groupId
     * @return
     */
    @RequestMapping(value = "searchGroupPerson" ,method = RequestMethod.GET)
    @ResponseBody
    public Show searchGroupPerson(@RequestParam("groupId") int groupId){
        List<CustomerService> member = customerServiceService.selectCustomerServiceByGroup(groupId);//调用CW
        Show show = new Show();
        show.setData(member);
        show.setStatus(member.size());
        show.setMessage("该部门共有:" + member.size() + "人");
        return show;
    }

    /**
     * Create By Cjn
     * 用于批量离职客服人员 
     * @param customId
     * @return
     */
    @RequestMapping(value = "deleteServerPerson")
    @ResponseBody
    public Show deletePerson(@RequestParam("deleteGroup") String  getJson){
        System.out.println(getJson);
        Gson gson = new Gson();
        List<IdList> customId = gson.fromJson(getJson,new TypeToken<List<IdList>>(){}.getType());
        Show show = new Show();
        int fail  = 0 ;
        for (int i = 0 ; i < customId.size() ; i++){
            System.out.println("!!!"+customId.get(i).getId());
          int res = customerServiceService.updateCustomDimission(customId.get(i).getId());
            if (res ==0){
                fail++;
            }
        }
        if (fail !=0 ){
         show.setStatus(0);
         show.setMessage("删除失败:"+(customId.size()-fail)+"条");
        }
        return show;
    }

    /**
     * Create By cjn
     * 批量添加客服人员
     * @param getJson json 字符串
     * @return show
     */
    @RequestMapping(value="addServerPerson")
    @ResponseBody
    public Show addServerPerson(@RequestParam("personList") String getJson){
        Gson gson = new Gson();
        List<CustomerService> customerServices = gson.fromJson(getJson,new TypeToken<List<CustomerService>>(){}.getType());
        int res = 0 ;
        for (int i=0 ; i < customerServices.size(); i++){
            System.out.println(customerServices.get(i));
            int re= customerServiceService.insertCustomerService(customerServices.get(i).getName(),customerServices.get(i).getGroupId(),customerServices.get(i).getNickname(), 
                    customerServices.get(i).getEmployeeId(),customerServices.get(i).getAutoMessage());
            if (re ==1){
                res++;
            }
        }
        Show show = new Show();
        if (res == customerServices.size()){
            show.setStatus(1);
        }else{
            show.setStatus(0);
            show.setMessage("请求插入"+customerServices.size()+"条,"+"成功插入"+ res +"条。");
        }
        return show;
    }

    /**
     * 查询客服和客服所对应的绩效
     * @param name
     * @return
     */
    @RequestMapping(value = "searchPerson")
    @ResponseBody
    public Show searchPersonAndPerformance(@RequestParam("name") String name){
        Show show = new Show();
        List<CustomerService> customerService = customerServiceService.selectBySearchName(name);
        if (customerService.size() == 0){
            show.setStatus(0);
            show.setMessage("无此客服人员");
        }else{
            List<PeopleDayAndTime> res =new ArrayList<PeopleDayAndTime>();
            for (int i=0;i<customerService.size();i++){
                PeopleDayAndTime peopleDayAndTime = new PeopleDayAndTime();
                CustomerService cs = customerService.get(i);
                peopleDayAndTime.setCustomerService(cs);
                peopleDayAndTime.setMonthInfo(conversationService.selectRecentPeopleMonth(cs.getServiceId()));
                peopleDayAndTime.setWeekInfo(conversationService.selectRecentPeopleWeekend(cs.getServiceId()));
                peopleDayAndTime.setDayInfo(conversationService.selectRecentPeopleHour(cs.getServiceId()));
                res.add(peopleDayAndTime);
            }
            show.setData(res);
        }
        return show;
    }
    
}
