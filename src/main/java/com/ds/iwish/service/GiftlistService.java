package com.ds.iwish.service;

import com.ds.iwish.bean.Layout;
import com.ds.iwish.bean.Profile;
import com.ds.iwish.bean.Giftlist;
import com.ds.iwish.data.repository.LayoutRepository;
import com.ds.iwish.data.repository.GiftlistRepository;
import com.ds.iwish.data.repository.UserRepository;
import com.ds.iwish.helper.ProfileHelper;
import com.ds.iwish.manager.WishlistManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GiftlistService {

    private WishlistManager giftlistManager;
    private GiftlistRepository giftlistRepository;
    private LayoutRepository layoutRepository;
    private UserRepository userRepository;


    public String createGiftlist(Giftlist newGiftlist) {
        String result = getGiftlistManager().validateWishlistForUpdate(newGiftlist);
        if (result != null) {
            return result;
        }
        newGiftlist.setPriority(Integer.parseInt(newGiftlist.getPriorityRaw()));
        Giftlist giftlist = getGiftlistRepository().createGiftlist(newGiftlist);
        newGiftlist.setWishlistId(giftlist.getWishlistId());
        return null;
    }

    public List<Giftlist> getGiftlists(long profileId){
        List<Giftlist> giftlists = getGiftlistRepository().getGiftlistsByUserId(profileId);
        return giftlists == null ? Collections.emptyList() : giftlists;
    }

    public List<Giftlist> getGiftlists(){
        Profile profile = ProfileHelper.getProfileFromSession();
        List<Giftlist> giftlists = getGiftlistRepository().getGiftlistsByUserId(profile.getId());
        return giftlists == null ? Collections.emptyList() : giftlists;
    }

    public Giftlist getGiftlist(long giftlistId){
        return getGiftlistRepository().getGiftlistById(giftlistId);
    }

    public String updateGiftlist(Giftlist giftlist) {
        String result = getGiftlistManager().validateWishlistForUpdate(giftlist);
        if (result != null) {
            return result;
        }
        giftlist.setPriority(Integer.parseInt(giftlist.getPriorityRaw()));
        getGiftlistRepository().updateGiftlist(giftlist);
        return null;
    }

    public void deleteGiftlist(long giftlistId) {
        getGiftlistRepository().deleteGiftlist(giftlistId);
    }

    public List<Layout> getLayouts(){
        Profile profile = ProfileHelper.getProfileFromSession();
        return  getLayoutRepository().getLayoutsByUserId(profile.getId());
    }

    public List<Profile> getFriends() {
        Profile profile = ProfileHelper.getProfileFromSession();
        return getUserRepository().getFriends(profile.getId());
    }


    public WishlistManager getGiftlistManager() {
        return giftlistManager;
    }

    @Autowired
    public void setGiftlistManager(WishlistManager giftlistManager) {
        this.giftlistManager = giftlistManager;
    }

    public GiftlistRepository getGiftlistRepository() {
        return giftlistRepository;
    }

    @Autowired
    public void setGiftlistRepository(GiftlistRepository giftlistRepository) {
        this.giftlistRepository = giftlistRepository;
    }

    public LayoutRepository getLayoutRepository() {
        return layoutRepository;
    }

    @Autowired
    public void setLayoutRepository(LayoutRepository layoutRepository) {
        this.layoutRepository = layoutRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
