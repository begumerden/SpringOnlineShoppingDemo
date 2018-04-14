package com.spring.demo.onlineshopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: b.erden
 * @date: 14.4.2018.
 */

@Controller
public class PageController {

    @RequestMapping(value = {"/","/home","/index"})
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("greeting","Welcome to Spring Web MVC!");
        return mv;
    }
}
