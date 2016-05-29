package com.ds.iwish.service;

import com.ds.iwish.bean.Layout;
import com.ds.iwish.bean.Wishlist;
import com.ds.iwish.bean.Profile;
import com.ds.iwish.bean.Template;
import com.ds.iwish.data.repository.LayoutRepository;
import com.ds.iwish.data.repository.WishlistRepository;
import com.ds.iwish.helper.ProfileHelper;
import com.ds.iwish.manager.WishlistManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class WishlistService {

    private WishlistManager wishlistManager;
    private WishlistRepository wishlistRepository;
    private LayoutRepository layoutRepository;


    public String createWishlist(Wishlist newWishlist) {
        String result = getWishlistManager().validateWishlistForUpdate(newWishlist);
        if (result != null) {
            return result;
        }
        newWishlist.setPriority(Integer.parseInt(newWishlist.getPriorityRaw()));
        Wishlist wishlist = getWishlistRepository().createWishlist(newWishlist);
        newWishlist.setWishlistId(wishlist.getWishlistId());
        return null;
    }

    public List<Wishlist> getWishlists(){
        Profile profile = ProfileHelper.getProfileFromSession();
        List<Wishlist> wishlists = getWishlistRepository().getWishlistsByUserId(profile.getId());
        return wishlists == null ? Collections.emptyList() : wishlists;
    }

    public Wishlist getWishlist(long wishlistId){
        return getWishlistRepository().getWishlistById(wishlistId);
    }

    public String updateWishlist(Wishlist wishlist) {
        String result = getWishlistManager().validateWishlistForUpdate(wishlist);
        if (result != null) {
            return result;
        }
        wishlist.setPriority(Integer.parseInt(wishlist.getPriorityRaw()));
        getWishlistRepository().updateWishlist(wishlist);
        return null;
    }

    public void deleteWishlist(long wishlistId) {
        getWishlistRepository().deleteWishlist(wishlistId);
    }

    public List<Layout> getLayouts(){
        Profile profile = ProfileHelper.getProfileFromSession();
        return  getLayoutRepository().getLayoutsByUserId(profile.getId());
    }


    public WishlistManager getWishlistManager() {
        return wishlistManager;
    }

    @Autowired
    public void setWishlistManager(WishlistManager wishlistManager) {
        this.wishlistManager = wishlistManager;
    }

    public WishlistRepository getWishlistRepository() {
        return wishlistRepository;
    }

    @Autowired
    public void setWishlistRepository(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public LayoutRepository getLayoutRepository() {
        return layoutRepository;
    }

    @Autowired
    public void setLayoutRepository(LayoutRepository layoutRepository) {
        this.layoutRepository = layoutRepository;
    }
}
