package com.ds.iwish.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TemplateController {

    public static final String VIEW_NAME__ADD_TEMPLATE = "add-template";


    @RequestMapping(value = "/workshop/add-template", method = RequestMethod.GET)
    public ModelAndView getAddTemplate() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(VIEW_NAME__ADD_TEMPLATE);
        return modelAndView;
    }
}
