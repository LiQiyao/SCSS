package org.obsidian.scss.controller;

import java.util.ArrayList;
import java.util.List;

import org.obsidian.scss.bean.Show;
import org.obsidian.scss.entity.CustomerService;
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
    public Show searchGroupPerson(@RequestBody int groupId){
        List<CustomerService> member = customerServiceService.selectCustomerServiceByGroup(groupId);//调用CW
        Show show = new Show();
        show.setData(member);
        show.setStatus(member.size());
        show.setMessage("该部门共有:" + member.size() + "人");
        return show;
    }

    /**
     * Create By Cjn
     * 用于批量离职客服人员  尚未完成
     * @param customId
     * @return
     */
    @RequestMapping(value = "deleteServerPerson")
    @ResponseBody
    public Show deletePerson(@RequestBody List<Integer> customId){
//        Show show = new Show();
//        List<Integer> deleteFailed = new ArrayList<Integer>();
//        for (int i = 0 ; i < customId.size() ; i++){
//            int deltRes = customerServiceService.deleteCustomerService(customId.get(i));
//        }
        return null;
    }

    /**
     * Create By cjn
     * 批量添加客服人员
     * @param customerServices
     * @return show
     */
    @RequestMapping(value="addServerPerson")
    @ResponseBody
    public Show addServerPerson(@RequestBody List<CustomerService> customerServices){
        int res = 0 ;
        for (int i=0 ; i < customerServices.size(); i++){
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
    
}
