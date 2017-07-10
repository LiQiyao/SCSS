package org.obsidian.scss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Lee on 2017/7/8.
 */
@Controller
@RequestMapping(value = "/")
public class TestController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String ws(){
        return "ws";
    }
}
