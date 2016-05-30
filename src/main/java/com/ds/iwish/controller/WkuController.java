package com.ds.iwish.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WkuController {

    @RequestMapping(value = "/catalog/add-wku", method = RequestMethod.GET)
    public ModelAndView getAddWku() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("add-wish");
        return modelAndView;
    }
}
