package com.ysla.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ysla.api.module.IHelloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author konghang
 */
@Controller
public class IndexController {

    @Reference(version="1.0.0")
    private IHelloService helloService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @ResponseBody
    public String index1(){
        helloService.say("welcome to dubbo!");
        return "index-post";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String login(){
        return "login";
    }

}
