package com.ds.iwish.controller;

import com.ds.iwish.bean.Giftlist;
import com.ds.iwish.bean.Profile;
import com.ds.iwish.controller.common.SupportController;
import com.ds.iwish.helper.ProfileHelper;
import com.ds.iwish.service.FriendsService;
import com.ds.iwish.service.GiftlistService;
import com.ds.iwish.service.ProfileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class FriendsController extends SupportController {

    public static final String VIEW_NAME__FRIENDS = "friends";


    private FriendsService friendsService;
    private ProfileService profileService;
    private GiftlistService giftlistService;


    @RequestMapping(value = "/user/friends", method = RequestMethod.GET)
    public ModelAndView getFriends() {
        ModelAndView modelAndView = new ModelAndView();
        populatePendingAction(modelAndView);
        populateFriends(modelAndView);
        modelAndView.setViewName(VIEW_NAME__FRIENDS);
        return modelAndView;
    }

    @RequestMapping(value = "/user/friends/{profileId}", method = RequestMethod.GET)
    public ModelAndView getFriend(@PathVariable("profileId") long profileId) {
        ModelAndView modelAndView = new ModelAndView();
        populatePendingAction(modelAndView);
        populateCurrentFriend(modelAndView, profileId);
        populateFriends(modelAndView);
        modelAndView.setViewName(VIEW_NAME__FRIENDS);
        return modelAndView;
    }

    @RequestMapping(value = "/user/friends/{profileId}/{giftlistId}", method = RequestMethod.GET)
    public ModelAndView getFriendGiftlist(@PathVariable("profileId") long profileId,
                                          @PathVariable("giftlistId") long giftlistId) {
        ModelAndView modelAndView = new ModelAndView();
        populatePendingAction(modelAndView);
        populateCurrentFriend(modelAndView, profileId);
        populateCurrentGiftlist(modelAndView, giftlistId);
        populateContent(modelAndView);
        populateFriends(modelAndView);
        modelAndView.setViewName(VIEW_NAME__FRIENDS);
        return modelAndView;
    }


    private void populatePendingAction(ModelAndView modelAndView) {
        List<Profile> pendingAction = getFriendsService().getPendingAction();
        if(!pendingAction.isEmpty()) {
            modelAndView.addObject("pendingAction", pendingAction);
        }
    }

    private void populateCurrentFriend(ModelAndView modelAndView, long friendId) {
        Profile profile = getProfileService().getProfile(friendId);
        modelAndView.addObject("currentFriend", profile);
        List<Giftlist> giftlists = getGiftlistService().getGiftlists(profile.getId());
        modelAndView.addObject("giftlists", giftlists);
    }

    private void populateCurrentGiftlist(ModelAndView modelAndView, long giftlistId) {
        Giftlist giftlist = getGiftlistService().getGiftlist(giftlistId);
        modelAndView.addObject("currentGiftlist", giftlist);
    }

    private void populateFriends(ModelAndView modelAndView) {
        List<Profile> friends = getFriendsService().getFriends();
        if(!friends.isEmpty()) {
            modelAndView.addObject("friends", friends);
        }
    }

    @RequestMapping(value = "/user/friends/search", method = RequestMethod.POST)
    public ModelAndView postKeywordSearchResults(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String keyword = request.getParameter("keyword");
        if(StringUtils.isEmpty(keyword)) {
            modelAndView.addObject("emptyResult", true);
        }
        List<Profile> notFriends = getFriendsService().getNotFriendsByKeyword(keyword);
        if (notFriends.contains(ProfileHelper.getProfileFromSession())) {
            notFriends.remove(ProfileHelper.getProfileFromSession());
        }
        if(notFriends.isEmpty()) {
            modelAndView.addObject("emptyResult", true);
        } else {
            modelAndView.addObject("searchResults", notFriends);
        }
        populatePendingAction(modelAndView);
        populateFriends(modelAndView);
        modelAndView.setViewName(VIEW_NAME__FRIENDS);
        return modelAndView;
    }

    @RequestMapping(value = "/user/friends/add/{profileId}", method = RequestMethod.GET)
    public ModelAndView getAddFriend(@PathVariable("profileId") long profileId) {
        ModelAndView modelAndView = new ModelAndView();
        getFriendsService().addFriend(profileId);
        modelAndView.setViewName("redirect:/user/friends");
        return modelAndView;
    }

    @RequestMapping(value = "/user/friends/approve/{profileId}", method = RequestMethod.GET)
    public ModelAndView getApprove(@PathVariable("profileId") long profileId) {
        ModelAndView modelAndView = new ModelAndView();
        getFriendsService().approveFriend(profileId);
        modelAndView.setViewName("redirect:/user/friends");
        return modelAndView;
    }

    @RequestMapping(value = "/user/friends/reject/{profileId}", method = RequestMethod.GET)
    public ModelAndView getReject(@PathVariable("profileId") long profileId) {
        ModelAndView modelAndView = new ModelAndView();
        getFriendsService().rejectFriend(profileId);
        modelAndView.setViewName("redirect:/user/friends");
        return modelAndView;
    }

    @RequestMapping(value = "/user/friends/rm/{profileId}", method = RequestMethod.GET)
    public ModelAndView getRM(@PathVariable("profileId") long profileId) {
        ModelAndView modelAndView = new ModelAndView();
        getFriendsService().removeFriend(profileId);
        modelAndView.setViewName("redirect:/user/friends");
        return modelAndView;
    }


    public FriendsService getFriendsService() {
        return friendsService;
    }

    @Autowired
    public void setFriendsService(FriendsService friendsService) {
        this.friendsService = friendsService;
    }

    public ProfileService getProfileService() {
        return profileService;
    }

    @Autowired
    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    public GiftlistService getGiftlistService() {
        return giftlistService;
    }

    @Autowired
    public void setGiftlistService(GiftlistService giftlistService) {
        this.giftlistService = giftlistService;
    }
}
