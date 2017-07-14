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
 */
@Controller
public class customerServiceManagement {
    @Autowired
    ServiceGroupService serviceGroupService;
    @Autowired
    CustomerServiceService customerServiceService;
    
    @RequestMapping(value = "customerServiceManagement")
    public String management(Model model){
        model.addAttribute("allGroup",serviceGroupService.selectAllGroup());//调用cw
        return "customerServiceManagement";
    }
    
    @RequestMapping(value = "searchGroupPerson" ,method = RequestMethod.GET)
    @ResponseBody
    public Show searchGroupPerson(@RequestParam(name = "groupId",defaultValue = "0") int groupId){
        List<CustomerService> member = customerServiceService.selectCustomerServiceByGroup(groupId);//调用CW
        Show show = new Show();
        show.setData(member);
        show.setStatus(member.size());
        show.setMessage("该部门共有:" + member.size() + "人");
        return show;
    }
    
    @RequestMapping(value = "deletePerson")
    @ResponseBody
    public Show deletePerson(@RequestBody List<Integer> customId){
//        Show show = new Show();
//        List<Integer> deleteFailed = new ArrayList<Integer>();
//        for (int i = 0 ; i < customId.size() ; i++){
//            int deltRes = customerServiceService.deleteCustomerService(customId.get(i));
//        }
        return null;
    }
}
