package com.ds.iwish.controller;

import com.ds.iwish.bean.Profile;
import com.ds.iwish.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    public static final String VIEW_NAME__REGISTER = "register";
    public static final String VIEW_NAME__LOGIN = "login";

    public static final String PARAM__EMAIL = "email";
    public static final String PARAM__PASSWORD = "password";
    public static final String PARAM__PASSWORD_CLONE = "password_clone";
    public static final String PARAM__FIRST_NAME = "first_name";
    public static final String PARAM__LAST_NAME = "last_name";
    public static final String PARAM__REMEMBER_ME = "remember";

    public static final String ATTR__ERROR_MESSAGE = "errorMessage";


    private ProfileService profileService;


    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public ModelAndView getLogin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(VIEW_NAME__LOGIN);
        return modelAndView;
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public ModelAndView getRegister() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(VIEW_NAME__REGISTER);
        return modelAndView;
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ModelAndView postRegister(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        Profile newProfile = new Profile();
        setupNewProfile(newProfile, request);
        String errorMessage = getProfileService().createProfile(newProfile);
        if(errorMessage == null) {
            modelAndView.setViewName("redirect:/view-category");
        } else {
            modelAndView.addObject(ATTR__ERROR_MESSAGE, errorMessage);
            modelAndView.setViewName(VIEW_NAME__REGISTER);
        }

        return modelAndView;
    }

    private void setupNewProfile(Profile profile, HttpServletRequest request) {
        profile.setEmail(request.getParameter(PARAM__EMAIL));
        profile.setPassword(request.getParameter(PARAM__PASSWORD));
        profile.setPasswordClone(request.getParameter(PARAM__PASSWORD_CLONE));
        profile.setFirstName(request.getParameter(PARAM__FIRST_NAME));
        profile.setLastName(request.getParameter(PARAM__LAST_NAME));
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public ModelAndView postLogin(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        Profile newProfile = new Profile();
        setupExistingProfile(newProfile, request);
        String errorMessage = getProfileService().loginProfile(newProfile);
        if(errorMessage == null) {
            modelAndView.setViewName("redirect:/view-category");
        } else {
            modelAndView.addObject(ATTR__ERROR_MESSAGE, errorMessage);
            modelAndView.setViewName(VIEW_NAME__LOGIN);
        }

        return modelAndView;
    }

    private void setupExistingProfile(Profile profile, HttpServletRequest request) {
        profile.setEmail(request.getParameter(PARAM__EMAIL));
        profile.setPassword(request.getParameter(PARAM__PASSWORD));
        profile.setRememberMe(Boolean.valueOf(request.getParameter(PARAM__REMEMBER_ME)));
    }


    public ProfileService getProfileService() {
        return profileService;
    }

    @Autowired()
    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }
}
