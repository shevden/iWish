package com.ds.iwish.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LayoutController {

    public static final String VIEW_NAME__ADD_LAYOUT = "add-layout";


    @RequestMapping(value = "/workshop/add-layout", method = RequestMethod.GET)
    public ModelAndView getAddLayout(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        //populateExistingTemplates(modelAndView);
        //setupSelectsTypes(request);
        modelAndView.setViewName(VIEW_NAME__ADD_LAYOUT);

        return modelAndView;
    }
}
