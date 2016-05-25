package com.ds.iwish.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewCategory {

    public static final String VIEW_NAME__VIEW_CATEGORY = "view-category";


    @RequestMapping(value = "/view-category", method = RequestMethod.GET)
    public ModelAndView getUserData() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(VIEW_NAME__VIEW_CATEGORY);
        return modelAndView;
    }
}
