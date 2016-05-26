package com.ds.iwish.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FriendsController {

    public static final String VIEW_NAME__FRIENDS = "friends";


    @RequestMapping(value = "/user/friends", method = RequestMethod.GET)
    public ModelAndView getFriends() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(VIEW_NAME__FRIENDS);
        return modelAndView;
    }
}
