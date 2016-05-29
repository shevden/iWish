package com.ds.iwish.data.repository;

import com.ds.iwish.bean.Profile;
import com.ds.iwish.data.dao.UserDAO;
import com.ds.iwish.helper.ProfileHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRepository {

    public static final Logger LOG = LogManager.getLogger(UserRepository.class.getName());


    private UserDAO userDAO;


    public Profile getUserById(long pId){
        return getUserDAO().getUserById(pId);
    }

    public Profile getUserByEmail(String pEmail){
        LOG.info("Inside #getUserByEmail : (pEmail = {})", pEmail);
        Profile Profile = getUserDAO().getUserByEmail(pEmail);
        LOG.info("Leaving #getUserByEmail : (Profile = {})", Profile);
        return Profile;
    }

    public List<Profile> getUsersByKeyword(String keyword){
        String [] names = keyword.split(" ");
        String firstName = names[0];
        String lastName;
        if(names.length < 2) {
            lastName = names[0];
        } else {
            lastName = names[1];
        }
        long userId = ProfileHelper.getProfileFromSession().getId();
        return getUserDAO().getUsersByName(firstName, lastName, userId);
    }

    public void addFriend(long userId, long otherUserId) {
        getUserDAO().createFriendship(userId, otherUserId);
    }

    public List<Profile> getUnapprovedFriends(long profileId) {
        return getUserDAO().getUnapprovedFriends(profileId);
    }

    public void approveFriend(long userId, long otherUserId) {
        getUserDAO().updateFriendship(userId, otherUserId, true);
    }

    public void rejectFriend(long userId, long otherUserId) {
        getUserDAO().updateFriendship(userId, otherUserId, false);
    }

    public List<Profile> getFriends(long userId) {
        return getUserDAO().getFriendUsers(userId);
    }

    public Profile createUser(Profile pProfile){
        LOG.info("Inside #validateProfileForRegistration : (pProfile = {})", pProfile);
        Profile Profile = getUserDAO().createUser(pProfile);
        LOG.info("Leaving #validateProfileForRegistration : (Profile = {})", Profile);
        return Profile;
    }

    public boolean removeFriend(long profileId, long otherProfileId){
        return getUserDAO().deleteFriendship(profileId, otherProfileId);
    }


    public UserDAO getUserDAO() {
        return userDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
