package org.obsidian.scss.controller;

import org.obsidian.scss.dao.ServiceGroupMapper;
import org.obsidian.scss.entity.CustomerService;
import org.obsidian.scss.entity.ServiceGroup;
import org.obsidian.scss.service.CustomerServiceService;
import org.obsidian.scss.service.GroupQueue;
import org.obsidian.scss.service.OnlineService;
import org.obsidian.scss.service.ServiceGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Lee on 2017/7/24.
 */
@Controller
@RequestMapping(value = "/api")
public class OnlineGroupController {

    @Autowired
    private ServiceGroupService serviceGroupService;

    @Autowired
    private OnlineService onlineService;

    @Autowired
    private CustomerServiceService customerServiceService;

    @RequestMapping(value = "/onlineGroup", method = RequestMethod.GET)
    @ResponseBody
    public List<ServiceGroup> onlineGroup(){
        List<ServiceGroup> list = serviceGroupService.selectAllGroup();
        for (String employeeId : onlineService.getMap().values()){
            CustomerService customerService = customerServiceService.selectCustomerServiceByEmployeeId(employeeId);
            for (ServiceGroup serviceGroup : list){
                if (serviceGroup.getGroupId().equals(customerService.getGroupId())){
                    serviceGroup.getOnlineMembers().add(customerService);
                }
            }
        }
        return list;
    }
}
