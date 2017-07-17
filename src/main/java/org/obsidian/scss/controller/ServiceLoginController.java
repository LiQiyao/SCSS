package org.obsidian.scss.controller;

import org.obsidian.scss.entity.CustomerService;
import org.obsidian.scss.service.CustomerServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Lee on 2017/7/17.
 */
@RequestMapping(value = "/")
public class ServiceLoginController {

    @Autowired
    private CustomerServiceService customerServiceService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest req){
        String employeeId = req.getParameter("employeeId");
        String password = req.getParameter("password");
        if (employeeId != null && password != null){
            CustomerService customerService = customerServiceService.selectByEIdAndPwd(employeeId,password);
            if (customerService != null){
                
            }
        }
        return "";
    }
}
