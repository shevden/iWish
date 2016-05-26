package com.ds.iwish.interceptor;

import com.ds.iwish.bean.Profile;
import com.ds.iwish.helper.ProfileHelper;
import com.ds.iwish.service.ProfileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static com.ds.iwish.service.ProfileService.COOKIE__PROFILE_ID;


public class AccessInterceptor extends HandlerInterceptorAdapter {


    public static final Logger LOG = LogManager.getLogger(AccessInterceptor.class);


    private ProfileService profileService;

    private List<String> unrecognizedOnlyPages;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Profile profile = ProfileHelper.getProfileFromSession();
        if (profile != null) {
            return processRecognizedProfile(request, response);
        } else {
            return processUnrecognizedProfile(request, response);
        }
    }

    private boolean processRecognizedProfile(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (goingToUnrecognizedOnlyPage(request)) {
            response.sendRedirect("/view-category");
            return false;
        }
        return true;
    }

    private boolean goingToUnrecognizedOnlyPage(HttpServletRequest request) {
        String uri = request.getRequestURI();
        for (String unrecognizedOnlyPage : getUnrecognizedOnlyPages()) {
            if (uri.contains(unrecognizedOnlyPage)) {
                return true;
            }
        }
        return false;
    }

    private boolean processUnrecognizedProfile(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (isRecognizable(request, response)) {
            return processRecognizedProfile(request, response);
        }
        if (goingToUnrecognizedOnlyPage(request)) {
            return true;
        }
        response.sendRedirect("/user/login");
        return false;
    }

    private boolean isRecognizable(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (COOKIE__PROFILE_ID.equals(cookie.getName())) {
                    return tryToLoginByProfileId(cookie);
                }
            }
        }
        return false;
    }

    private boolean tryToLoginByProfileId(Cookie cookie)
            throws IOException {
        try {
            String profileId = cookie.getValue();
            if (profileId != null) {
                Profile profile = new Profile();
                profile.setId(Long.valueOf(profileId));
                return getProfileService().saveProfileInSession(profile);
            }
        } catch (NumberFormatException e) {
            LOG.error("Wrong profileId in Cookie.", e);
        }
        return false;
    }


    public ProfileService getProfileService() {
        return profileService;
    }

    @Autowired
    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    public List<String> getUnrecognizedOnlyPages() {
        return unrecognizedOnlyPages;
    }

    public void setUnrecognizedOnlyPages(List<String> unrecognizedOnlyPages) {
        this.unrecognizedOnlyPages = unrecognizedOnlyPages;
    }
}
