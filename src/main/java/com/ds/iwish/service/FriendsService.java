package com.ds.iwish.service;

import com.ds.iwish.bean.Profile;
import com.ds.iwish.data.repository.UserRepository;
import com.ds.iwish.helper.ProfileHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FriendsService {

    private UserRepository userRepository;

    public List<Profile> getNotFriendsByKeyword(String keyword) {
        List<Profile> notFriendsByKeyword = getUserRepository().getUsersByKeyword(keyword);
        return notFriendsByKeyword == null ? Collections.emptyList() : notFriendsByKeyword;
    }

    public void addFriend(long profileId) {
        long currentProfileId = ProfileHelper.getProfileFromSession().getId();
        getUserRepository().addFriend(currentProfileId, profileId);
    }

    public List<Profile> getPendingAction(){
        Profile profile = ProfileHelper.getProfileFromSession();
        return getUserRepository().getUnapprovedFriends(profile.getId());
    }

    public void approveFriend(long profileId) {
        long currentProfileId = ProfileHelper.getProfileFromSession().getId();
        getUserRepository().approveFriend(currentProfileId, profileId);
    }

    public void rejectFriend(long profileId) {
        long currentProfileId = ProfileHelper.getProfileFromSession().getId();
        getUserRepository().rejectFriend(currentProfileId, profileId);
    }

    public List<Profile> getFriends() {
        Profile profile = ProfileHelper.getProfileFromSession();
        return getUserRepository().getFriends(profile.getId());
    }

    public void removeFriend(long profileId){
        long currentProfileId = ProfileHelper.getProfileFromSession().getId();
        getUserRepository().removeFriend(currentProfileId, profileId);
    }


    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
