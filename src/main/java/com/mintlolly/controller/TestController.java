package com.mintlolly.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * Create by jag on 2018/2/27
 */
@RestController
@RequestMapping("/hello")
public class TestController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView sayHello() {
        return new ModelAndView("index");
    }
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public ModelAndView courseClickCountStat() {
        return new ModelAndView("everyhourdata");
    }
}
