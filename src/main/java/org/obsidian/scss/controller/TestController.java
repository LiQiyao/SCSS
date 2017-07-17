package org.obsidian.scss.controller;

import org.obsidian.scss.service.GroupQueue;
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
    private GroupQueue groupQueue;

    @RequestMapping(value = "/clientWS", method = RequestMethod.GET)
    public String ws(){
        System.out.println(groupQueue);
        return "ws";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/serviceWS", method = RequestMethod.GET)
    public String xws(){
        System.out.println(groupQueue);
        return "serviceWS";
    }
}
