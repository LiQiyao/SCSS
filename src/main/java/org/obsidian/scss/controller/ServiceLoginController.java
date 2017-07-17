package org.obsidian.scss.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;

/**
 * Created by Lee on 2017/7/17.
 */
@RequestMapping(value = "/")
public class ServiceLoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(){
        return "";
    }
}
