package org.obsidian.scss.controller;

import org.obsidian.scss.service.InitBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Lee on 2017/7/8.
 */
@Controller
@RequestMapping(value = "/")
public class TestController {

    @Autowired
    private InitBean initBean;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String ws(){
        System.out.println(initBean);
        initBean.setStartTime(9L);
        return "ws";
    }

    @RequestMapping(value = "/x", method = RequestMethod.GET)
    public String xws(){
        System.out.println(initBean);
        return "ws";
    }
}
