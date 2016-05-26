package com.ds.iwish.service;


import com.ds.iwish.bean.Profile;
import com.ds.iwish.data.repository.UserRepository;
import com.ds.iwish.helper.ProfileHelper;
import com.ds.iwish.helper.WebHelper;
import com.ds.iwish.manager.ProfileManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Contains Profile related functionality.
 *
 * @author
 */
@Service
public class ProfileService {

    public static final String S_ATTR__PROFILE = "profile";

    public static final String COOKIE__PROFILE_ID = "c_profile_id";

    public static final Logger LOG = LogManager.getLogger(ProfileService.class);


    private ProfileManager profileManager;
    private UserRepository userRepository;


    public Profile getProfileById(long pId) {
        LOG.info("Inside #getProfileById : (pId = {})", pId);
        Profile Profile = getUserRepository().getUserById(pId);
        LOG.info("Leaving #getProfileById : (Profile = {})", Profile);
        return Profile;
    }

    public List<Profile> getProfilesByName(String pName, int pPageSize, int pPageNum) {
        LOG.info("Inside #getProfilesByName : (pName = {}, pPageSize = {}, pPageNum = {})", pName, pPageSize, pPageNum);
        List<Profile> Profiles = getUserRepository().getUsersByName(pName, pPageSize, pPageNum);
        LOG.info("Leaving #getProfilesByName : (Profiles.size() = {})", Profiles);
        return Profiles;
    }

    public String createProfile(Profile profile) {
        String result = getProfileManager().validateProfileForRegistration(profile);
        if (result != null) {
            return result;
        }
        getUserRepository().createUser(profile);
        return null;
    }

    public String loginProfile(Profile profile) {
        String result = getProfileManager().validateProfileForLogin(profile);
        if (result != null) {
            return result;
        }
        saveProfileInSession(profile);
        processRememberMe(profile);
        return null;
    }

    private void processRememberMe(Profile profile) {
        if(profile.isRememberMe()) {
            Cookie сookie = new Cookie(COOKIE__PROFILE_ID,
                    String.valueOf(ProfileHelper.getProfileFromSession().getId()));
            сookie.setMaxAge(100500);
            WebHelper.getResponse().addCookie(сookie);
        }
    }

    public boolean saveProfileInSession(Profile profile) {
        Profile existingProfile;
        if (profile.getId() > 0) {
            existingProfile = getUserRepository().getUserById(profile.getId());
        } else {
            existingProfile = getUserRepository().getUserByEmail(profile.getEmail());
        }
        if(existingProfile == null) {
            return false;
        }

        HttpSession session = WebHelper.getSession();
        session.setAttribute(S_ATTR__PROFILE, existingProfile);
        return true;
    }

    public void logout() {
        Cookie [] cookies = WebHelper.getRequest().getCookies();
        for (Cookie cookie : cookies) {
            if (COOKIE__PROFILE_ID.equals(cookie.getName())) {
                cookie.setMaxAge(0);
                WebHelper.getResponse().addCookie(cookie);
                break;
            }
        }
        WebHelper.getSession().invalidate();
    }


    public ProfileManager getProfileManager() {
        return profileManager;
    }

    @Autowired
    public void setProfileManager(ProfileManager profileManager) {
        this.profileManager = profileManager;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
