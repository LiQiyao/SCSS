package org.obsidian.scss.controller;

import org.obsidian.scss.bean.Message;
import org.obsidian.scss.entity.CustomerService;
import org.obsidian.scss.service.CustomerServiceService;
import org.obsidian.scss.service.OnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.misc.BASE64Encoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Lee on 2017/7/17.
 */
@Controller
@RequestMapping(value ="/" )
public class ServiceLoginController {

    @Autowired
    private CustomerServiceService customerServiceService;

    @Autowired
    private OnlineService onlineService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest req, HttpServletResponse resp){
        String employeeId = req.getParameter("employeeId");
        String password = req.getParameter("password");
        if (employeeId != null && password != null){
            CustomerService customerService = customerServiceService.selectByEIdAndPwd(employeeId,password);
            if (customerService != null){
                onlineService.online(employeeId, resp);
                return "redirect:/SCSS/customerService.html";
            }
        }
        return null;
    }

}
