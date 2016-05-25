package com.ds.iwish.manager;

import com.ds.iwish.bean.Profile;
import com.ds.iwish.data.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Service
public class ProfileManager {

    private UserRepository userRepository;


    public String validateProfileForRegistration(Profile profile) {
        if(isNewProfileFieldMissing(profile)) {
            return "Please, fulfil blank fields.";
        }
        if(isEmailInvalid(profile)) {
            return "Sorry, this email is invalid.";
        }
        if(isPasswordMismatch(profile)) {
            return "Sorry, passwords do not match.";
        }
        if(isEmailUsed(profile)) {
            return "Sorry, this email is already used.";
        }
        return null;
    }

    private boolean isNewProfileFieldMissing(Profile profile) {
        return StringUtils.isEmpty(profile.getEmail())
                || StringUtils.isEmpty(profile.getPassword())
                || StringUtils.isEmpty(profile.getPasswordClone())
                || StringUtils.isEmpty(profile.getFirstName())
                || StringUtils.isEmpty(profile.getLastName());
    }

    private boolean isPasswordMismatch(Profile profile){
        return !profile.getPassword().equals(profile.getPasswordClone());
    }

    private boolean isEmailUsed(Profile profile) {
        return getUserRepository().getUserByEmail(profile.getEmail()) != null;
    }

    private boolean isEmailInvalid(Profile profile) {
        boolean result = false;
        try {
            InternetAddress email = new InternetAddress(profile.getEmail());
            email.validate();
        } catch (AddressException ex) {
            result = true;
        }
        return result;
    }

    public void generatePasswordHash(Profile profile) {
        profile.setPasswordHash(String.valueOf(profile.getPassword().hashCode()));
    }

    public String validateProfileForLogin(Profile profile) {
        if(isExistingProfileFieldMissing(profile)) {
            return "Please, fulfil blank fields.";
        }
        if(areCredentialsInvalid(profile)) {
            return "Sorry, credentials are invalid.";
        }
        return null;
    }

    private boolean isExistingProfileFieldMissing(Profile profile) {
        return StringUtils.isEmpty(profile.getEmail())
                || StringUtils.isEmpty(profile.getPassword());
    }

    private boolean areCredentialsInvalid(Profile profile) {
        Profile loadedProfile = getUserRepository().getUserByEmail(profile.getEmail());
        if (loadedProfile == null) {
            return true;
        }
        generatePasswordHash(profile);
        return !profile.getPasswordHash().equals(loadedProfile.getPasswordHash());
    }


    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
