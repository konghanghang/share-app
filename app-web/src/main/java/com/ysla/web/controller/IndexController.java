package com.ysla.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author konghang
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @ResponseBody
    public String index1(){
        return "index-post";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String login(){
        return "login";
    }

}
